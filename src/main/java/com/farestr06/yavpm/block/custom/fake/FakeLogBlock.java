package com.farestr06.yavpm.block.custom.fake;

import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public class FakeLogBlock extends AbstractFakeBlock{
    public static final EnumProperty<WoodType> TYPE = EnumProperty.of("wood_type", WoodType.class);

    public FakeLogBlock() {
        super(Blocks.OAK_LOG, Settings.copy(Blocks.CRIMSON_STEM).sounds(BlockSoundGroup.WOOD));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    @Override
    public BlockState makeFakeBlockState(Random rand) {
        return this.getDefaultState().with(TYPE, choose(rand));
    }

    public BlockState makeFakeBlockState(BlockPos pos, WorldAccess world) {
        return this.getDefaultState().with(TYPE, choose(pos, world));
    }

    protected WoodType choose(BlockPos pos, WorldAccess world) {
        RegistryEntry<Biome> biome = world.getBiome(pos);
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_SPRUCE)) {
            return WoodType.SPRUCE;
        }
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_BIRCH)) {
            return WoodType.BIRCH;
        }
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_JUNGLE)) {
            return WoodType.JUNGLE;
        }
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_ACACIA)) {
            return WoodType.ACACIA;
        }
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_CHERRY)) {
            return WoodType.CHERRY;
        }
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_DARK_OAK)) {
            return WoodType.DARK_OAK;
        }
        if (biome.isIn(YavpmTags.Biomes.FAKE_LOG_IS_MANGROVE)) {
            return WoodType.MANGROVE;
        }
        return WoodType.OAK;
    }

    private WoodType choose(Random rand) {
        int type = rand.nextInt(8);
        switch (type) {
            case 1 -> {
                return WoodType.SPRUCE;
            }
            case 2 -> {
                return WoodType.BIRCH;
            }
            case 3 -> {
                return WoodType.JUNGLE;
            }
            case 4 -> {
                return WoodType.ACACIA;
            }
            case 5 -> {
                return WoodType.CHERRY;
            }
            case 6 -> {
                return WoodType.DARK_OAK;
            }
            case 7 -> {
                return WoodType.MANGROVE;
            }
            default -> {
                return WoodType.OAK;
            }
        }
    }

    public enum WoodType implements StringIdentifiable {
        OAK("oak"),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        CHERRY("cherry"),
        DARK_OAK("dark_oak"),
        MANGROVE("mangrove");

        private final String name;

        WoodType(final String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }
    }
}
