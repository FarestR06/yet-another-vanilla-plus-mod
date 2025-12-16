package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DiamondChickenEntity extends Chicken implements NeutralMob {
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(DiamondChickenEntity.class, EntityDataSerializers.INT);
    private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(12, 36);
    private float flapEffects;
    @Nullable
    private UUID angryAt;

    public DiamondChickenEntity(EntityType<? extends Chicken> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4f));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.0, Ingredient.of(Items.GLOW_BERRIES), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Spider.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, CaveSpider.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Silverfish.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANGER_TIME, 0);
    }

    public static AttributeSupplier.Builder createCarbonfowlAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.ATTACK_DAMAGE, 2.5)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ARMOR, 7.5);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(YavpmTags.Items.CARBONFOWL_FOODS);
    }

    @Override
    public @Nullable Chicken getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return YavpmEntities.CARBONFOWL.create(serverWorld, EntitySpawnReason.BREEDING);
    }

    protected boolean isFlapping() {
        return this.flyDist > this.flapEffects;
    }

    protected void onFlap() {
        this.flapEffects = this.flyDist + this.flapSpeed / 2.0F;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int angerTime) {
        this.entityData.set(ANGER_TIME, angerTime);
    }

    @Override
    public @Nullable UUID getPersistentAngerTarget() {
        return angryAt;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID angryAt) {
        if (!this.isBaby()) {
            this.angryAt = angryAt;
        }
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(this.random));
    }
}
