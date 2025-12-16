package com.farestr06.yavpm.block.custom;

import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.entity.PinataBlockEntity;
import com.farestr06.yavpm.util.YavpmSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PinataBlock extends BaseEntityBlock {
    public static final MapCodec<PinataBlock> CODEC = simpleCodec(PinataBlock::new);
    public static final IntegerProperty HITS = IntegerProperty.create("hits", 0, 8);
    public static final BooleanProperty OPENED = BooleanProperty.create("opened");
    private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);

    public PinataBlock(Properties settings) {
        super(settings);
        registerDefaultState(defaultBlockState().setValue(OPENED, true).setValue(HITS, 0));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        RandomSource rand = ctx.getLevel().getRandom();
        return this.defaultBlockState().setValue(HITS, rand.nextIntBetweenInclusive(0, 4));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HITS);
        builder.add(OPENED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PinataBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity entity) {
            if (world.isClientSide()) {
                return InteractionResult.SUCCESS;
            } else {
                ItemStack entityStack = entity.getTheItem();
                if (
                        !stack.isEmpty()
                                && (entityStack.isEmpty() || ItemStack.isSameItemSameComponents(entityStack, stack)
                                && entityStack.getCount() < entityStack.getMaxStackSize())
                ) {
                    player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                    ItemStack toPutIn = stack.consumeAndReturn(1, player);
                    float f;
                    if (entity.isEmpty()) {
                        entity.setTheItem(toPutIn);
                        f = (float) toPutIn.getCount() / (float)toPutIn.getMaxStackSize();
                        world.setBlockAndUpdate(pos, state.setValue(OPENED, false));
                    } else {
                        entityStack.grow(1);
                        f = (float)entityStack.getCount() / (float)entityStack.getMaxStackSize();
                    }

                    world.playSound(null, pos, YavpmSounds.BLOCK_PINATA_INSERT, SoundSource.BLOCKS, 1f, 0.7f + 0.5f * f);
                    entity.setChanged();
                    world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
            }
        } else return InteractionResult.PASS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity) {
            world.playSound(null, pos, YavpmSounds.BLOCK_PINATA_INSERT_FAIL, SoundSource.BLOCKS, 1f, 1f);
            world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return InteractionResult.SUCCESS;
        } else return InteractionResult.PASS;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return Block.canSupportCenter(world, pos.relative(Direction.UP), Direction.UP.getOpposite())
        && !world.getBlockState(pos.above()).is(YavpmBlocks.PINATA);
    }

    @Override
    protected void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (!state.getValue(OPENED) && !player.hasInfiniteMaterials() && hasNoSilkTouch(player)) {
            player.makeSound(SoundEvents.PLAYER_ATTACK_WEAK);
            int hitCount = state.getValue(HITS);
            if (hitCount == 4) {
                crackOpen(world, pos, player);
            } else {
                world.setBlockAndUpdate(pos, state.setValue(HITS, ++hitCount));
            }
        }
        super.attack(state, world, pos, player);
    }

    private boolean hasNoSilkTouch(Player player) {
        ItemStack stack = player.getMainHandItem();
        return !EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_DECORATED_POT_SHATTERING);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return super.canBeReplaced(state, context);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos, Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return !canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(
                state, world, tickView, pos, direction, neighborPos, neighborState, random
        );
    }

    @Override
    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, @Nullable Orientation wireOrientation, boolean notify) {
        if (!state.getValue(OPENED)) {
            boolean bl = world.hasNeighborSignal(pos) || world.hasNeighborSignal(pos.above()) || world.hasNeighborSignal(pos.above(2));
            if (bl) releaseContents(world, pos, state);
        }
    }

    private void crackOpen(Level world, BlockPos pos, Player player) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity entity) {
            popResourceFromFace(world, pos, Direction.DOWN, entity.getTheItem());
            world.playSound(player, pos, YavpmSounds.BLOCK_PINATA_BREAK, SoundSource.BLOCKS, 1f, getPitch(world.getRandom()));
            world.destroyBlock(pos, true);
        } else {
            world.destroyBlock(pos, true);
            YetAnotherVanillaPlusMod.LOGGER.warn("Couldn't drop contents; not a pinata block entity");
        }
    }

    private void releaseContents(Level world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos) instanceof PinataBlockEntity entity) {
            if (!entity.isEmpty()) {
                popResourceFromFace(world, pos, Direction.DOWN, entity.getTheItem());
                world.playSound(null, pos, YavpmSounds.BLOCK_PINATA_OPEN, SoundSource.BLOCKS, 1f, getPitch(world.getRandom()));
                entity.setTheItem(ItemStack.EMPTY);
            }
            world.setBlockAndUpdate(pos, state.setValue(OPENED, true));
        }
    }

    private float getPitch(RandomSource rand) {
        return Mth.map(rand.nextFloat(), 0, 1, 0.85f, 1.15f);
    }
}
