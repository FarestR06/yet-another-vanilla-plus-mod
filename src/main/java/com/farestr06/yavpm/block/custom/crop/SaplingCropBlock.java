package com.farestr06.yavpm.block.custom.crop;

import com.farestr06.yavpm.item.YavpmItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Optional;

public class SaplingCropBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    public SaplingCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 1, 2);
    }

    public static class Oak extends SaplingCropBlock {
        private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 2.0, 10.0),
                Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 5.0, 12.0),
                Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),
                Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
        };
        public Oak(Settings settings) {
            super(settings);
        }

        @Override
        protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return AGE_TO_SHAPE[this.getAge(state)];
        }

        @Override
        protected ItemConvertible getSeedsItem() {
            return YavpmItems.ACORN;
        }
    }
    public static class Birch extends SaplingCropBlock {
        private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 3.0, 10.0),
                Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0),
                Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 12.0, 12.0),
                Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0),
        };

        public Birch(Settings settings) {
            super(settings);
        }

        @Override
        protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return AGE_TO_SHAPE[this.getAge(state)];
        }

        @Override
        protected ItemConvertible getSeedsItem() {
            return YavpmItems.BIRCH_SEEDS;
        }
    }
    public static abstract class Fungus extends SaplingCropBlock {
        public Fungus(Settings settings) {
            super(settings);
        }

        @Override
        protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
            return floor.isOf(Blocks.SOUL_SAND);
        }

        @Override
        public void applyGrowth(World world, BlockPos pos, BlockState state) {
            int i = this.getAge(state) + (this.isInNativeBiome(world, pos) ? this.getGrowthAmount(world) : 1);
            int j = this.getMaxAge();
            if (i > j) {
                i = j;
            }

            world.setBlockState(pos, this.withAge(i), Block.NOTIFY_LISTENERS);
        }

        private boolean isInNativeBiome(World world, BlockPos pos) {
            Optional<RegistryKey<Biome>> biome = world.getBiome(pos).getKey();
            return biome.filter(biomeRegistryKey -> biomeRegistryKey == this.getBiome()).isPresent();
        }

        abstract RegistryKey<Biome> getBiome();

        @Override
        protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
            if (world.getBaseLightLevel(pos, 0) >= 7) {
                if (state.get(AGE) < this.getMaxAge() && random.nextInt(10) == 0) {
                    this.grow(world, random, pos, state);
                }
            }
        }

        public static class Crimson extends Fungus {
            private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                    Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),
                    Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 6.0, 10.0),
                    Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0),
                    Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 12.0, 13.0),
            };

            public Crimson(Settings settings) {
                super(settings);
            }

            @Override
            RegistryKey<Biome> getBiome() {
                return BiomeKeys.CRIMSON_FOREST;
            }

            @Override
            protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                return AGE_TO_SHAPE[this.getAge(state)];
            }

            @Override
            protected ItemConvertible getSeedsItem() {
                return YavpmItems.CRIMSON_SPORE;
            }
        }
        public static class Warped extends Fungus {
            private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
                    Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),
                    Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 3.0, 9.0),
                    Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 5.0, 11.0),
                    Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 9.0, 13.0),
            };

            public Warped(Settings settings) {
                super(settings);
            }

            @Override
            RegistryKey<Biome> getBiome() {
                return BiomeKeys.WARPED_FOREST;
            }

            @Override
            protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                return AGE_TO_SHAPE[this.getAge(state)];
            }

            @Override
            protected ItemConvertible getSeedsItem() {
                return YavpmItems.WARPED_SPORE;
            }
        }
    }
}
