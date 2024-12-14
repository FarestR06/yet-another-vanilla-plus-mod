package com.farestr06.yavpm.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;

public class VoidPhantomEntity extends PhantomEntity {
    public VoidPhantomEntity(EntityType<? extends VoidPhantomEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onSizeChanged() {
        this.calculateDimensions();
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue((8 + this.getPhantomSize()) * 1.2);
    }
}
