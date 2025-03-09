package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.entity.PinataBlockEntity;
import com.farestr06.yavpm.util.YavpmSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class PinataBlock extends BlockWithEntity {
    public static final MapCodec<PinataBlock> CODEC = createCodec(PinataBlock::new);
    public static final IntProperty HITS = IntProperty.of("hits", 0, 8);
    public static final BooleanProperty OPENED = BooleanProperty.of("opened");
    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 8, 12);

    public PinataBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(OPENED, true).with(HITS, 0));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Random rand = ctx.getWorld().getRandom();
        return this.getDefaultState().with(HITS, rand.nextBetween(0, 4));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HITS);
        builder.add(OPENED);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PinataBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity entity) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            } else {
                ItemStack entityStack = entity.getStack();
                if (
                        !stack.isEmpty()
                                && (entityStack.isEmpty() || ItemStack.areItemsAndComponentsEqual(entityStack, stack)
                                && entityStack.getCount() < entityStack.getMaxCount())
                ) {
                    player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                    ItemStack toPutIn = stack.splitUnlessCreative(1, player);
                    float f;
                    if (entity.isEmpty()) {
                        entity.setStack(toPutIn);
                        f = (float) toPutIn.getCount() / (float)toPutIn.getMaxCount();
                        world.setBlockState(pos, state.with(OPENED, false));
                    } else {
                        entityStack.increment(1);
                        f = (float)entityStack.getCount() / (float)entityStack.getMaxCount();
                    }

                    world.playSound(null, pos, YavpmSounds.BLOCK_PINATA_INSERT, SoundCategory.BLOCKS, 1f, 0.7f + 0.5f * f);
                    entity.markDirty();
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
                }
            }
        } else return ActionResult.PASS;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity) {
            world.playSound(null, pos, YavpmSounds.BLOCK_PINATA_INSERT_FAIL, SoundCategory.BLOCKS, 1f, 1f);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return ActionResult.SUCCESS;
        } else return ActionResult.PASS;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.offset(Direction.UP), Direction.UP.getOpposite())
        && !world.getBlockState(pos.up()).isOf(YavpmBlocks.PINATA);
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!state.get(OPENED) && !player.isInCreativeMode() && hasNoSilkTouch(player)) {
            player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_WEAK);
            int hitCount = state.get(HITS);
            if (hitCount == 4) {
                crackOpen(world, pos, player);
            } else {
                world.setBlockState(pos, state.with(HITS, ++hitCount));
            }
        }
        super.onBlockBreakStart(state, world, pos, player);
    }

    private boolean hasNoSilkTouch(PlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        return !EnchantmentHelper.hasAnyEnchantmentsIn(stack, EnchantmentTags.PREVENTS_DECORATED_POT_SHATTERING);
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return super.canReplace(state, context);
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return !canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(
                state, world, tickView, pos, direction, neighborPos, neighborState, random
        );
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!state.get(OPENED)) {
            boolean bl = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up()) || world.isReceivingRedstonePower(pos.up(2));
            if (bl) releaseContents(world, pos, state);
        }
    }

    private void crackOpen(World world, BlockPos pos, PlayerEntity player) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity entity) {
            dropStack(world, pos, Direction.DOWN, entity.getStack());
            world.playSound(player, pos, YavpmSounds.BLOCK_PINATA_BREAK, SoundCategory.BLOCKS, 1f, getPitch(world.getRandom()));
            world.breakBlock(pos, true);
        } else {
            world.breakBlock(pos, true);
            YetAnotherVanillaPlusMod.LOGGER.warn("Couldn't drop contents; not a pinata block entity");
        }
    }

    private void releaseContents(World world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity entity) {
            if (!entity.isEmpty()) {
                dropStack(world, pos, Direction.DOWN, entity.getStack());
                world.playSound(null, pos, YavpmSounds.BLOCK_PINATA_OPEN, SoundCategory.BLOCKS, 1f, getPitch(world.getRandom()));
                entity.setStack(ItemStack.EMPTY);
            }
            world.setBlockState(pos, state.with(OPENED, true));
        }
    }

    private float getPitch(Random rand) {
        return MathHelper.map(rand.nextFloat(), 0, 1, 0.85f, 1.15f);
    }
}
