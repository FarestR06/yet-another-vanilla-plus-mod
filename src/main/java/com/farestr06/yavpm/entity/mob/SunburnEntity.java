package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.util.YavpmSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SunburnEntity extends HostileEntity {
    protected static final TrackedData<Byte> SUNBURN_FLAGS = DataTracker.registerData(SunburnEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final int CHARGING_FLAG = 1;
    public SunburnEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new SunburnMoveControl(this);
        this.experiencePoints = 4;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new ChargeTargetGoal());
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 8f));
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PhantomEntity.class, false));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SUNBURN_FLAGS, (byte)0);
    }

    public static DefaultAttributeContainer.Builder createSunburnAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, 22).add(EntityAttributes.ATTACK_DAMAGE, 3);
    }

    @Override
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
    }

    private boolean areFlagsSet() {
        int i = this.dataTracker.get(SUNBURN_FLAGS);
        return (i & SunburnEntity.CHARGING_FLAG) != 0;
    }

    private void setSunburnFlag(boolean value) {
        int i = this.dataTracker.get(SUNBURN_FLAGS);
        if (value) {
            i |= SunburnEntity.CHARGING_FLAG;
        } else {
            i &= ~SunburnEntity.CHARGING_FLAG;
        }

        this.dataTracker.set(SUNBURN_FLAGS, (byte)(i & 0xFF));
    }

    public boolean isCharging() {
        return this.areFlagsSet();
    }
    public void setCharging(boolean charging) {
        this.setSunburnFlag(charging);
    }

    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        target.setOnFireFor(5f);
        return super.tryAttack(world, target);
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
    public boolean hurtByWater() {
        return true;
    }

    class ChargeTargetGoal extends Goal {
        public ChargeTargetGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = SunburnEntity.this.getTarget();
            return livingEntity != null && livingEntity.isAlive() && !SunburnEntity.this.getMoveControl().isMoving() && SunburnEntity.this.random.nextInt(toGoalTicks(7)) == 0 && SunburnEntity.this.squaredDistanceTo(livingEntity) > 4.0;
        }

        @Override
        public boolean shouldContinue() {
            return SunburnEntity.this.getMoveControl().isMoving()
                    && SunburnEntity.this.isCharging()
                    && SunburnEntity.this.getTarget() != null
                    && SunburnEntity.this.getTarget().isAlive();
        }

        @Override
        public void start() {
            LivingEntity livingEntity = SunburnEntity.this.getTarget();
            if (livingEntity != null) {
                Vec3d vec3d = livingEntity.getEyePos();
                SunburnEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0);
            }

            SunburnEntity.this.setCharging(true);
        }

        @Override
        public void stop() {
            SunburnEntity.this.setCharging(false);
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = SunburnEntity.this.getTarget();
            if (livingEntity != null) {
                if (SunburnEntity.this.getBoundingBox().intersects(livingEntity.getBoundingBox())) {
                    SunburnEntity.this.tryAttack(castToServerWorld(SunburnEntity.this.getWorld()), livingEntity);
                    SunburnEntity.this.setCharging(false);
                } else {
                    double d = SunburnEntity.this.squaredDistanceTo(livingEntity);
                    if (d < 9.0) {
                        Vec3d vec3d = livingEntity.getEyePos();
                        SunburnEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0);
                    }
                }
            }
        }
    }


    class SunburnMoveControl extends MoveControl {
        public SunburnMoveControl(final SunburnEntity owner) {
            super(owner);
        }

        @SuppressWarnings("SuspiciousNameCombination")
        @Override
        public void tick() {
            if (this.state == MoveControl.State.MOVE_TO) {
                Vec3d vec3d = new Vec3d(this.targetX - SunburnEntity.this.getX(), this.targetY - SunburnEntity.this.getY(), this.targetZ - SunburnEntity.this.getZ());
                double d = vec3d.length();
                if (d < SunburnEntity.this.getBoundingBox().getAverageSideLength()) {
                    this.state = MoveControl.State.WAIT;
                    SunburnEntity.this.setVelocity(SunburnEntity.this.getVelocity().multiply(0.5));
                } else {
                    SunburnEntity.this.setVelocity(SunburnEntity.this.getVelocity().add(vec3d.multiply(this.speed * 0.05 / d)));
                    if (SunburnEntity.this.getTarget() == null) {
                        Vec3d vec3d2 = SunburnEntity.this.getVelocity();
                        SunburnEntity.this.setYaw(-((float) MathHelper.atan2(vec3d2.x, vec3d2.z)) * (180.0F / (float)Math.PI));
                    } else {
                        double e = SunburnEntity.this.getTarget().getX() - SunburnEntity.this.getX();
                        double f = SunburnEntity.this.getTarget().getZ() - SunburnEntity.this.getZ();
                        SunburnEntity.this.setYaw(-((float)MathHelper.atan2(e, f)) * (180.0F / (float)Math.PI));
                    }
                    SunburnEntity.this.bodyYaw = SunburnEntity.this.getYaw();
                }
            }
        }
    }
}
