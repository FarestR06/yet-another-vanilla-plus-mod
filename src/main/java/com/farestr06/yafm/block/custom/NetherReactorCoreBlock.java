package com.farestr06.yafm.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

public class NetherReactorCoreBlock extends Block {
    public static final EnumProperty<Phase> PHASE = EnumProperty.of("phase", Phase.class);

    public NetherReactorCoreBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(PHASE, Phase.INACTIVE));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PHASE);
    }

    public enum Phase implements StringIdentifiable {
        INACTIVE("inactive"),
        ACTIVE("active"),
        DEAD("dead");

        private final String id;

        Phase(String id) {
            this.id = id;
        }

        @Override
        public String asString() {
            return id;
        }
    }
}
