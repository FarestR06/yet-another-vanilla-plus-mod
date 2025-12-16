package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public class SaplingCropBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public SaplingCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected int getBonemealAgeIncrease(Level world) {
        return Mth.nextInt(world.random, 1, 2);
    }

    public static class Oak extends SaplingCropBlock {
        private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
                Block.box(4.0, 0.0, 4.0, 12.0, 5.0, 12.0),
                Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),
                Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
        };
        public Oak(Properties settings) {
            super(settings);
        }

        @Override
        protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            return AGE_TO_SHAPE[this.getAge(state)];
        }

        @Override
        protected ItemLike getBaseSeedId() {
            return YavpmItems.ACORN;
        }
    }
    public static class Birch extends SaplingCropBlock {
        private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                Block.box(6.0, 0.0, 6.0, 10.0, 3.0, 10.0),
                Block.box(5.0, 0.0, 5.0, 11.0, 8.0, 11.0),
                Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0),
                Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0),
        };

        public Birch(Properties settings) {
            super(settings);
        }

        @Override
        protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            return AGE_TO_SHAPE[this.getAge(state)];
        }

        @Override
        protected ItemLike getBaseSeedId() {
            return YavpmItems.BIRCH_SEEDS;
        }
    }
    public static abstract class Fungus extends SaplingCropBlock {
        public Fungus(Properties settings) {
            super(settings);
        }

        @Override
        protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
            return floor.is(Blocks.SOUL_SAND);
        }

        @Override
        public void growCrops(Level world, BlockPos pos, BlockState state) {
            int i = this.getAge(state) + (this.isInNativeBiome(world, pos) ? this.getBonemealAgeIncrease(world) : 1);
            int j = this.getMaxAge();
            if (i > j) {
                i = j;
            }

            world.setBlock(pos, this.getStateForAge(i), Block.UPDATE_CLIENTS);
        }

        private boolean isInNativeBiome(Level world, BlockPos pos) {
            Optional<ResourceKey<Biome>> biome = world.getBiome(pos).unwrapKey();
            return biome.filter(biomeRegistryKey -> biomeRegistryKey == this.getBiome()).isPresent();
        }

        abstract ResourceKey<Biome> getBiome();

        @Override
        protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
            if (world.getRawBrightness(pos, 0) >= 7) {
                if (state.getValue(AGE) < this.getMaxAge() && random.nextInt(10) == 0) {
                    this.performBonemeal(world, random, pos, state);
                }
            }
        }

        public static class Crimson extends Fungus {
            private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                    Block.box(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),
                    Block.box(6.0, 0.0, 6.0, 10.0, 6.0, 10.0),
                    Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0),
                    Block.box(3.0, 0.0, 3.0, 13.0, 12.0, 13.0),
            };

            public Crimson(Properties settings) {
                super(settings);
            }

            @Override
            ResourceKey<Biome> getBiome() {
                return Biomes.CRIMSON_FOREST;
            }

            @Override
            protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
                return AGE_TO_SHAPE[this.getAge(state)];
            }

            @Override
            protected ItemLike getBaseSeedId() {
                return YavpmItems.CRIMSON_SPORE;
            }
        }
        public static class Warped extends Fungus {
            private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                    Block.box(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),
                    Block.box(7.0, 0.0, 7.0, 9.0, 3.0, 9.0),
                    Block.box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0),
                    Block.box(3.0, 0.0, 3.0, 13.0, 9.0, 13.0),
            };

            public Warped(Properties settings) {
                super(settings);
            }

            @Override
            ResourceKey<Biome> getBiome() {
                return Biomes.WARPED_FOREST;
            }

            @Override
            protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
                return AGE_TO_SHAPE[this.getAge(state)];
            }

            @Override
            protected ItemLike getBaseSeedId() {
                return YavpmItems.WARPED_SPORE;
            }
        }
    }
}
