package com.farestr06.yavpm.block.custom.fake;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class FakeOreBlock extends AbstractFakeBlock {

    public static final EnumProperty<OreType> TYPE = EnumProperty.of("ore_type", OreType.class);
    public static final BooleanProperty DEEPSLATE = BooleanProperty.of("deepslate");

    public FakeOreBlock() {
        super(Blocks.DIAMOND_ORE, Settings.copy(Blocks.DIAMOND_ORE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
        builder.add(DEEPSLATE);
    }

    public BlockState makeFakeBlockState(Random rand, BlockPos pos) {
        if (pos.getY() > 0) {
            return this.getDefaultState().with(TYPE, choose(rand)).with(DEEPSLATE, true);
        }
        return this.getDefaultState().with(TYPE, choose(rand)).with(DEEPSLATE, false);
    }

    private OreType choose(Random rand) {
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

    public enum OreType implements StringIdentifiable {
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
        public String asString() {
            return name;
        }
    }
}
