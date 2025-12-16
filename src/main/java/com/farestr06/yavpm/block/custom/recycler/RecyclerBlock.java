package com.farestr06.yavpm.block.custom.recycler;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.custom.entity.YavpmBlockEntities;
import com.farestr06.yavpm.block.custom.recycler.registry.RecyclingResult;
import com.farestr06.yavpm.block.custom.recycler.registry.RecyclingResultRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RecyclerBlock extends DispenserBlock {
    public static final MapCodec<RecyclerBlock> CODEC = simpleCodec(RecyclerBlock::new);
    private static final DispenseItemBehavior BEHAVIOR = new RecycleDispenserBehavior();

    public RecyclerBlock(Properties settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends DispenserBlock> codec() {
        return CODEC;
    }

    @Override
    protected DispenseItemBehavior getDispenseMethod(Level world, ItemStack stack) {
        return BEHAVIOR;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RecyclerBlockEntity(pos, state);
    }

    @Override
    protected void dispenseFrom(ServerLevel world, BlockState state, BlockPos pos) {
        DispenserBlockEntity blockEntity = world.getBlockEntity(pos, YavpmBlockEntities.RECYCLER).orElse(null);
        if (blockEntity == null) {
            YetAnotherVanillaPlusMod.LOGGER.warn("Ignoring dispensing attempt for Recycler without matching block entity at {}", pos);
        } else {
            BlockSource blockPointer = new BlockSource(world, pos, state, blockEntity);
            int i = blockEntity.getRandomSlot(world.random);
            if (i < 0) {
                world.levelEvent(LevelEvent.SOUND_DISPENSER_FAIL, pos, 0);
            } else {
                ItemStack input = blockEntity.getItem(i);
                if (!input.isEmpty()) {
                    RecyclingResult result = RecyclingResultRegistry.INSTANCE.get(input.getItem());
                    if (result != null) {
                        ItemStack resultStack = result.toStack(world.random);
                        if (!resultStack.isEmpty()) {
                            Direction direction = world.getBlockState(pos).getValue(FACING);
                            Container inventory = HopperBlockEntity.getContainerAt(world, pos.relative(direction));
                            if (inventory == null) {
                                input.shrink(1);
                                BEHAVIOR.dispense(blockPointer, resultStack);
                            } else {
                                input = HopperBlockEntity.addItem(blockEntity, inventory, input.copyWithCount(1), direction.getOpposite());
                                if (input.isEmpty()) {
                                    input = input.copy();
                                    input.shrink(1);
                                } else {
                                    input = input.copy();
                                }
                            }

                            blockEntity.setItem(i, input);
                        }
                    }
                }
            }
        }
    }
}
