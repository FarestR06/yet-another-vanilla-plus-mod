package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.fake.FakeLogBlock;
import com.farestr06.yavpm.block.custom.fake.FakeOreBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class TanukiEntity extends AnimalEntity {
    public int tryTransformTime = this.random.nextInt(HANDLER.instance().tanukiRandomTransformDelay) + HANDLER.instance().tanukiBaseTransformDelay;

    public TanukiEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new PowderSnowJumpGoal(this, this.getWorld()));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.8));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1));
        this.goalSelector.add(3, new TemptGoal(this, 1.15, stack -> stack.isIn(YavpmTags.Items.TANUKI_FOOD), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 3f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.getWorld() instanceof ServerWorld world) {
            if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                if (
                        !this.getWorld().isClient && this.isAlive() && !this.isBaby() && --this.tryTransformTime <= 0
                                && !this.hasCustomName() && !this.isAiDisabled() && !this.isInvulnerable()
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
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(YavpmTags.Items.TANUKI_FOOD);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return YavpmEntities.TANUKI.create(world, SpawnReason.BREEDING);
    }

    public static DefaultAttributeContainer.Builder createTanukiAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.MAX_HEALTH, 14.0)
                .add(EntityAttributes.FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.SAFE_FALL_DISTANCE, 3.5);
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
    protected void playEatSound() {
        this.playSound(YavpmSounds.ENTITY_TANUKI_EAT, 1.0F, 1.0F);
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.tryTransformTime = view.getInt("TryTransformTime", 4000);
    }

    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        view.putInt("TryTransformTime", this.tryTransformTime);
    }

    private void transform() {
        WorldAccess worldAccess = this.getWorld();
        Random rand = this.getRandom();
        BlockPos blockPos = this.getBlockPos();
        BlockState blockState = worldAccess.getBlockState(blockPos);
        if (blockState.isOf(Blocks.AIR) | blockState.isOf(Blocks.CAVE_AIR)) {
            BlockState fake;
            if (blockPos.getY() >= 63) {
                fake = ((FakeLogBlock) YavpmBlocks.FAKE_LOG).makeFakeBlockState(blockPos, worldAccess);
            } else {
                fake = ((FakeOreBlock) YavpmBlocks.FAKE_ORE).makeFakeBlockState(rand, blockPos);
            }
            this.emitGameEvent(GameEvent.BLOCK_PLACE);
            worldAccess.setBlockState(blockPos, fake, Block.NOTIFY_ALL);
            this.playSpawnEffects();
            this.discard();
        }
    }

}
