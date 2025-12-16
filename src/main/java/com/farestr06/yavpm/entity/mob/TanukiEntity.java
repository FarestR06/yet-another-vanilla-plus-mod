package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.fake.FakeLogBlock;
import com.farestr06.yavpm.block.custom.fake.FakeOreBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class TanukiEntity extends Animal {
    public int tryTransformTime = this.random.nextInt(HANDLER.instance().tanukiRandomTransformDelay) + HANDLER.instance().tanukiBaseTransformDelay;

    public TanukiEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.8));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.15, stack -> stack.is(YavpmTags.Items.TANUKI_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel world) {
            if (world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                if (
                        !this.level().isClientSide() && this.isAlive() && !this.isBaby() && --this.tryTransformTime <= 0
                                && !this.hasCustomName() && !this.isNoAi() && !this.isInvulnerable()
                ) {
                    if (this.getRandom().nextFloat() <= HANDLER.instance().tanukiTransformChance) {
                        transform();
                    } else {
                        this.tryTransformTime = this.random.nextInt(4000) + 2000;
                    }
                }
            }
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(YavpmTags.Items.TANUKI_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return YavpmEntities.TANUKI.create(world, EntitySpawnReason.BREEDING);
    }

    public static AttributeSupplier.Builder createTanukiAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.MAX_HEALTH, 14.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.SAFE_FALL_DISTANCE, 3.5);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return YavpmSounds.ENTITY_TANUKI_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return YavpmSounds.ENTITY_TANUKI_DEATH;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return YavpmSounds.ENTITY_TANUKI_HURT;
    }

    @Override
    protected void playEatingSound() {
        this.playSound(YavpmSounds.ENTITY_TANUKI_EAT, 1.0F, 1.0F);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        if (valueInput.contains("TryTransformTime")) {
            this.tryTransformTime = valueInput.getIntOr("TryTransformTime", 4000);
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putInt("TryTransformTime", this.tryTransformTime);
    }

    private void transform() {
        LevelAccessor worldAccess = this.level();
        RandomSource rand = this.getRandom();
        BlockPos blockPos = this.blockPosition();
        BlockState blockState = worldAccess.getBlockState(blockPos);
        if (blockState.is(Blocks.AIR) | blockState.is(Blocks.CAVE_AIR)) {
            BlockState fake;
            if (blockPos.getY() >= 63) {
                fake = ((FakeLogBlock) YavpmBlocks.FAKE_LOG).makeFakeBlockState(blockPos, worldAccess);
            } else {
                fake = ((FakeOreBlock) YavpmBlocks.FAKE_ORE).makeFakeBlockState(rand, blockPos);
            }
            this.gameEvent(GameEvent.BLOCK_PLACE);
            worldAccess.setBlock(blockPos, fake, Block.UPDATE_ALL);
            this.spawnAnim();
            this.discard();
        }
    }

}
