package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SunburnEntity extends Monster {
    protected static final EntityDataAccessor<Byte> SUNBURN_FLAGS = SynchedEntityData.defineId(SunburnEntity.class, EntityDataSerializers.BYTE);
    private static final int CHARGING_FLAG = 1;
    public SunburnEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new SunburnMoveControl(this);
        this.xpReward = 4;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new ChargeTargetGoal());
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8f));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Phantom.class, false));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SUNBURN_FLAGS, (byte)0);
    }

    public static AttributeSupplier.Builder createSunburnAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 22).add(Attributes.ATTACK_DAMAGE, 3);
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
    }

    private boolean areFlagsSet() {
        int i = this.entityData.get(SUNBURN_FLAGS);
        return (i & SunburnEntity.CHARGING_FLAG) != 0;
    }

    private void setSunburnFlag(boolean value) {
        int i = this.entityData.get(SUNBURN_FLAGS);
        if (value) {
            i |= SunburnEntity.CHARGING_FLAG;
        } else {
            i &= ~SunburnEntity.CHARGING_FLAG;
        }

        this.entityData.set(SUNBURN_FLAGS, (byte)(i & 0xFF));
    }

    public boolean isCharging() {
        return this.areFlagsSet();
    }
    public void setCharging(boolean charging) {
        this.setSunburnFlag(charging);
    }

    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        target.igniteForSeconds(5f);
        return super.doHurtTarget(world, target);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return YavpmSounds.ENTITY_SUNBURN_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return YavpmSounds.ENTITY_SUNBURN_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return YavpmSounds.ENTITY_SUNBURN_HURT;
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    class ChargeTargetGoal extends Goal {
        public ChargeTargetGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = SunburnEntity.this.getTarget();
            return livingEntity != null && livingEntity.isAlive() && !SunburnEntity.this.getMoveControl().hasWanted() && SunburnEntity.this.random.nextInt(reducedTickDelay(7)) == 0 && SunburnEntity.this.distanceToSqr(livingEntity) > 4.0;
        }

        @Override
        public boolean canContinueToUse() {
            return SunburnEntity.this.getMoveControl().hasWanted()
                    && SunburnEntity.this.isCharging()
                    && SunburnEntity.this.getTarget() != null
                    && SunburnEntity.this.getTarget().isAlive();
        }

        @Override
        public void start() {
            LivingEntity livingEntity = SunburnEntity.this.getTarget();
            if (livingEntity != null) {
                Vec3 vec3d = livingEntity.getEyePosition();
                SunburnEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 1.0);
            }

            SunburnEntity.this.setCharging(true);
        }

        @Override
        public void stop() {
            SunburnEntity.this.setCharging(false);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = SunburnEntity.this.getTarget();
            if (livingEntity != null) {
                if (SunburnEntity.this.getBoundingBox().intersects(livingEntity.getBoundingBox())) {
                    SunburnEntity.this.doHurtTarget(getServerLevel(SunburnEntity.this.level()), livingEntity);
                    SunburnEntity.this.setCharging(false);
                } else {
                    double d = SunburnEntity.this.distanceToSqr(livingEntity);
                    if (d < 9.0) {
                        Vec3 vec3d = livingEntity.getEyePosition();
                        SunburnEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 1.0);
                    }
                }
            }
        }
    }


    class SunburnMoveControl extends MoveControl {
        public SunburnMoveControl(final SunburnEntity owner) {
            super(owner);
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3d = new Vec3(this.wantedX - SunburnEntity.this.getX(), this.wantedY - SunburnEntity.this.getY(), this.wantedZ - SunburnEntity.this.getZ());
                double d = vec3d.length();
                if (d < SunburnEntity.this.getBoundingBox().getSize()) {
                    this.operation = MoveControl.Operation.WAIT;
                    SunburnEntity.this.setDeltaMovement(SunburnEntity.this.getDeltaMovement().scale(0.5));
                } else {
                    SunburnEntity.this.setDeltaMovement(SunburnEntity.this.getDeltaMovement().add(vec3d.scale(this.speedModifier * 0.05 / d)));
                    if (SunburnEntity.this.getTarget() == null) {
                        Vec3 vec3d2 = SunburnEntity.this.getDeltaMovement();
                        SunburnEntity.this.setYRot(-((float) Mth.atan2(vec3d2.x, vec3d2.z)) * (180.0F / (float)Math.PI));
                    } else {
                        double e = SunburnEntity.this.getTarget().getX() - SunburnEntity.this.getX();
                        double f = SunburnEntity.this.getTarget().getZ() - SunburnEntity.this.getZ();
                        SunburnEntity.this.setYRot(-((float)Mth.atan2(e, f)) * (180.0F / (float)Math.PI));
                    }
                    SunburnEntity.this.yBodyRot = SunburnEntity.this.getYRot();
                }
            }
        }
    }
}
