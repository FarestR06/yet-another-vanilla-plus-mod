package com.farestr06.yavpm.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Objects;

public class VoidPhantomEntity extends PhantomEntity {
    public VoidPhantomEntity(EntityType<? extends VoidPhantomEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onSizeChanged() {
        this.calculateDimensions();
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue((6 + this.getPhantomSize()) * 1.2);
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return super.isAffectedByDaylight() && !this.getWorld().getDimensionEntry().matchesId(DimensionTypes.THE_END_ID);
    }
}
