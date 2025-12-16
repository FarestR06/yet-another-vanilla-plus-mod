package com.farestr06.yavpm.block.custom.fake;

import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FakeLogBlock extends AbstractFakeBlock{
    public static final EnumProperty<WoodType> TYPE = EnumProperty.create("wood_type", WoodType.class);

    public FakeLogBlock(Properties settings) {
        super(Blocks.OAK_LOG, settings);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    public BlockState makeFakeBlockState(BlockPos pos, LevelAccessor world) {
        return this.defaultBlockState().setValue(TYPE, choose(pos, world));
    }

    protected WoodType choose(BlockPos pos, LevelAccessor world) {
        Holder<Biome> biome = world.getBiome(pos);
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_SPRUCE)) {
            return WoodType.SPRUCE;
        }
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_BIRCH)) {
            return WoodType.BIRCH;
        }
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_JUNGLE)) {
            return WoodType.JUNGLE;
        }
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_ACACIA)) {
            return WoodType.ACACIA;
        }
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_CHERRY)) {
            return WoodType.CHERRY;
        }
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_DARK_OAK)) {
            return WoodType.DARK_OAK;
        }
        if (biome.is(YavpmTags.Biomes.FAKE_LOG_IS_MANGROVE)) {
            return WoodType.MANGROVE;
        }
        return WoodType.OAK;
    }

    public enum WoodType implements StringRepresentable {
        OAK("oak"),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        CHERRY("cherry"),
        DARK_OAK("dark_oak"),
        MANGROVE("mangrove"),
        APPLE("apple"),
        PERSIMMON("persimmon");

        private final String name;

        WoodType(final String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
