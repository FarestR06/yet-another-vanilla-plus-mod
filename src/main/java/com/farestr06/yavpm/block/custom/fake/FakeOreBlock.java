package com.farestr06.yavpm.block.custom.fake;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FakeOreBlock extends AbstractFakeBlock {

    public static final EnumProperty<OreType> TYPE = EnumProperty.create("ore_type", OreType.class);
    public static final BooleanProperty DEEPSLATE = BooleanProperty.create("deepslate");

    public FakeOreBlock(Properties settings) {
        super(Blocks.DIAMOND_ORE, settings);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
        builder.add(DEEPSLATE);
    }

    public BlockState makeFakeBlockState(RandomSource rand, BlockPos pos) {
        if (pos.getY() <= 0) {
            return this.defaultBlockState().setValue(TYPE, choose(rand)).setValue(DEEPSLATE, true);
        }
        return this.defaultBlockState().setValue(TYPE, choose(rand)).setValue(DEEPSLATE, false);
    }

    private OreType choose(RandomSource rand) {
        int type = rand.nextInt(8);
        switch (type) {

            case 1 -> {
                return OreType.IRON;
            }
            case 2 -> {
                return OreType.GOLD;
            }
            case 3 -> {
                return OreType.COPPER;
            }
            case 4 -> {
                return OreType.LAPIS;
            }
            case 5 -> {
                return OreType.DIAMOND;
            }
            case 6 -> {
                return OreType.EMERALD;
            }
            case 7 -> {
                return OreType.REDSTONE;
            }
            default -> {
                return OreType.COAL;
            }
        }
    }

    public enum OreType implements StringRepresentable {
        COAL("coal"),
        IRON("iron"),
        GOLD("gold"),
        COPPER("copper"),
        LAPIS("lapis"),
        DIAMOND("diamond"),
        EMERALD("emerald"),
        REDSTONE("redstone");

        private final String name;

        OreType(final String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
