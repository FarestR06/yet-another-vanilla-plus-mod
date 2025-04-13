package com.farestr06.yavpm.entity.mob;

import com.farestr06.api.util.FarestsUtils;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class VoidPhantomEntity extends PhantomEntity {
    public VoidPhantomEntity(EntityType<? extends VoidPhantomEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        int size = this.getRandom().nextInt(2);
        this.setPhantomSize(size);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected void onSizeChanged() {
        this.calculateDimensions();
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE)).setBaseValue(
                FarestsUtils.Math.roundToHalf((6 + this.getPhantomSize()) * 1.2f));
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return super.isAffectedByDaylight() && !this.getWorld().getDimensionEntry().matchesId(DimensionTypes.THE_END_ID);
    }
}
