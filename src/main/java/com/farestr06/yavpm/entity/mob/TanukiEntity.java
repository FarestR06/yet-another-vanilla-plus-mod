package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.fake.FakeLogBlock;
import com.farestr06.yavpm.block.custom.fake.FakeOreBlock;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
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
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1));
        this.goalSelector.add(3, new TemptGoal(this, 1.15, stack -> stack.isIn(YavpmTags.Items.TANUKI_FOODS), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 3f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (
                !this.getWorld().isClient && this.isAlive() && !this.isBaby()
                && --this.tryTransformTime <= 0 && this.getRandom().nextFloat() <= HANDLER.instance().tanukiTransformChance
        ) {
            transform();
        } else {
            this.tryTransformTime = this.random.nextInt(4000) + 2000;
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ConventionalItemTags.BERRY_FOODS);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return YavpmEntities.TANUKI.create(world);
    }

    public static DefaultAttributeContainer.Builder createTanukiAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 3.5);
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
    public SoundEvent getEatSound(ItemStack stack) {
        return YavpmSounds.ENTITY_TANUKI_EAT;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("TryTransformTime")) {
            this.tryTransformTime = nbt.getInt("TryTransformTime");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("TryTransformTime", this.tryTransformTime);
    }

    private void transform() {
        WorldAccess worldAccess = this.getWorld();
        Random rand = this.getRandom();
        BlockPos blockPos = BlockPos.ofFloored(this.getX(), this.getY() + 0.5, this.getZ()).offset(Direction.random(rand));
        BlockState blockState = worldAccess.getBlockState(blockPos);
        if (blockState.isIn(BlockTags.AIR)) {
            BlockState fake;
            if (blockPos.getY() >= 63) {
                fake = ((FakeLogBlock) YavpmBlocks.FAKE_LOG).makeFakeBlockState(blockPos, worldAccess);
            } else {
                fake = ((FakeOreBlock) YavpmBlocks.FAKE_ORE).makeFakeBlockState(rand, blockPos);
            }
            /*
            if (rand.nextBoolean()) {
                fake = ((FakeLogBlock) YavpmBlocks.FAKE_LOG).makeFakeBlockState(rand);
            } else {
                fake = ((FakeOreBlock) YavpmBlocks.FAKE_ORE).makeFakeBlockState(rand);
            }
             */
            this.emitGameEvent(GameEvent.BLOCK_PLACE);
            worldAccess.setBlockState(blockPos, fake, Block.NOTIFY_ALL);
            this.playSpawnEffects();
            this.discard();
        }
    }

}
