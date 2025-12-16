package com.farestr06.yavpm.entity.mob;

import com.farestr06.api.util.FarestsUtils;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class VoidPhantomEntity extends Phantom {
    public VoidPhantomEntity(EntityType<? extends VoidPhantomEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        int size = this.getRandom().nextInt(2);
        this.setPhantomSize(size);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected void updatePhantomSizeInfo() {
        this.refreshDimensions();
        Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(
                FarestsUtils.Math.roundToHalf((6 + this.getPhantomSize()) * 1.2f));
    }

    @Override
    protected boolean isSunBurnTick() {
        return super.isSunBurnTick() && !(this.level().dimension().location() == (BuiltinDimensionTypes.END_EFFECTS));
    }
}
