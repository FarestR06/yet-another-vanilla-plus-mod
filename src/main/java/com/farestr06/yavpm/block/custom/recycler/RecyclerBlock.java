package com.farestr06.yavpm.block.custom.recycler;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.custom.entity.YavpmBlockEntities;
import com.farestr06.yavpm.block.custom.recycler.registry.RecyclingResult;
import com.farestr06.yavpm.block.custom.recycler.registry.RecyclingResultRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class RecyclerBlock extends DispenserBlock {
    public static final MapCodec<RecyclerBlock> CODEC = createCodec(RecyclerBlock::new);
    private static final DispenserBehavior BEHAVIOR = new RecycleDispenserBehavior();

    public RecyclerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends DispenserBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected DispenserBehavior getBehaviorForItem(World world, ItemStack stack) {
        return BEHAVIOR;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RecyclerBlockEntity(pos, state);
    }

    @Override
    protected void dispense(ServerWorld world, BlockState state, BlockPos pos) {
        DispenserBlockEntity blockEntity = world.getBlockEntity(pos, YavpmBlockEntities.RECYCLER).orElse(null);
        if (blockEntity == null) {
            YetAnotherVanillaPlusMod.LOGGER.warn("Ignoring dispensing attempt for Recycler without matching block entity at {}", pos);
        } else {
            BlockPointer blockPointer = new BlockPointer(world, pos, state, blockEntity);
            int i = blockEntity.chooseNonEmptySlot(world.random);
            if (i < 0) {
                world.syncWorldEvent(WorldEvents.DISPENSER_FAILS, pos, 0);
            } else {
                ItemStack input = blockEntity.getStack(i);
                if (!input.isEmpty()) {
                    RecyclingResult result = RecyclingResultRegistry.INSTANCE.get(input.getItem());
                    if (result != null) {
                        ItemStack resultStack = result.toStack(world.random);
                        if (!resultStack.isEmpty()) {
                            Direction direction = world.getBlockState(pos).get(FACING);
                            Inventory inventory = HopperBlockEntity.getInventoryAt(world, pos.offset(direction));
                            if (inventory == null) {
                                input.decrement(1);
                                BEHAVIOR.dispense(blockPointer, resultStack);
                            } else {
                                input = HopperBlockEntity.transfer(blockEntity, inventory, input.copyWithCount(1), direction.getOpposite());
                                if (input.isEmpty()) {
                                    input = input.copy();
                                    input.decrement(1);
                                } else {
                                    input = input.copy();
                                }
                            }

                            blockEntity.setStack(i, input);
                        }
                    }
                }
            }
        }
    }
}
