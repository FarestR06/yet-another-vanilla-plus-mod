package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmPotions;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

import static com.farestr06.yavpm.item.YavpmItems.*;

public class FungusCowEntity extends Cow implements Shearable {
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(FungusCowEntity.class, EntityDataSerializers.INT);
    
    @Nullable
    private Holder<Potion> potionContents;

    private boolean corrupted = false;
    private boolean isSheared = false;

    public FungusCowEntity(EntityType<? extends FungusCowEntity> entityVariant, Level world) {
        super(entityVariant, world);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader world) {
        return (world.getBlockState(pos.below()).is(Blocks.CRIMSON_NYLIUM) || world.getBlockState(pos.below()).is(Blocks.WARPED_NYLIUM)) ? 10f : 0f;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        if (world.getBiome(this.blockPosition()).is(YavpmTags.Biomes.SPAWNS_WARPED_MOONGUS)) {
            this.setMoongusVariant(Variant.WARPED);
        } else if (world.getBiome(this.blockPosition()).is(YavpmTags.Biomes.SPAWNS_CRIMSON_MOONGUS)) {
            this.setMoongusVariant(Variant.CRIMSON);
        } else {
            this.setMoongusVariant(world.getRandom().nextBoolean() ? Variant.CRIMSON : Variant.WARPED);
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    @Nullable
    @Override
    public FungusCowEntity getBreedOffspring(ServerLevel serverLevel, AgeableMob passiveEntity) {
        return YavpmEntities.MOONGUS.create(serverLevel, EntitySpawnReason.BREEDING);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, EntitySpawnReason spawnReason) {
        BlockState state = world.getBlockState(blockPosition().below());
        return state.is(Blocks.CRIMSON_NYLIUM) || state.is(Blocks.WARPED_NYLIUM);
    }

    @Override
    public void tick() {
        super.tick();
        if (isSheared && tickCount % 30 == 0 && this.level() instanceof ServerLevel serverLevel) {
            this.hurtServer(serverLevel, YavpmDamageTypes.cut(serverLevel), 1.5f);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TYPE, Variant.DEFAULT.getIndex());
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putInt("Variant", this.getMoongusVariant().getIndex());
        if (this.potionContents != null) {
            valueOutput.store("potion_contents", Potion.CODEC, this.potionContents);
        }
        valueOutput.putBoolean("Corrupted", corrupted);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        super.readAdditionalSaveData(valueInput);
        this.setMoongusVariant(FungusCowEntity.Variant.fromIndex(valueInput.getInt("Variant").orElse(Variant.DEFAULT.getIndex())));
        if (valueInput.contains("potion_contents")) {
            this.potionContents = valueInput.read("potion_contents", Potion.CODEC).orElse(Potions.MUNDANE);
        }
        corrupted = valueInput.getBooleanOr("Corrupted", false);
    }

    @Override
    public @Nullable <T> T get(DataComponentType<? extends T> type) {
        return type == YavpmDataComponentTypes.Entity.MOONGUS_VARIANT
                ? castComponentValue(type, this.getVariant())
                : super.get(type);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter getter) {
        this.applyImplicitComponentIfPresent(getter, YavpmDataComponentTypes.Entity.MOONGUS_VARIANT);
        super.applyImplicitComponents(getter);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T object) {
        if (type == YavpmDataComponentTypes.Entity.MOONGUS_VARIANT) {
            this.setMoongusVariant(castComponentValue(YavpmDataComponentTypes.Entity.MOONGUS_VARIANT, object));
            return true;
        } else {
            return super.applyImplicitComponent(type, object);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        // Get stack from player
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(Items.GLASS_BOTTLE) && !this.isBaby()) {
            ItemStack potion = ItemStack.EMPTY;
            if (getMoongusVariant() == Variant.CRIMSON) {
                if (potionContents != null) {
                    potion = PotionContents.createItemStack(Items.POTION, potionContents);
                    potionContents = null;
                } else {
                    potion = PotionContents.createItemStack(Items.POTION, Potions.AWKWARD);
                }
                corrupted = false;
                this.playSound(YavpmSounds.ENTITY_MOONGUS_MILK_CRIMSON, 1, 1);
            } else if (getMoongusVariant() == Variant.WARPED) {
                if (potionContents != null) {
                    potion = PotionContents.createItemStack(Items.POTION, potionContents);
                    potionContents = null;
                } else {
                    potion = PotionContents.createItemStack(Items.POTION, YavpmPotions.WEIRD);
                }
                this.playSound(YavpmSounds.ENTITY_MOONGUS_MILK_WARPED, 1, 1);
            }
            ItemStack exchangedStack = ItemUtils.createFilledResult(stack, player, potion, false);
            player.setItemInHand(hand, exchangedStack);
            return InteractionResult.SUCCESS;
        } else if (stack.is(Items.FERMENTED_SPIDER_EYE) && this.getMoongusVariant() == Variant.CRIMSON && !this.corrupted && this.potionContents == null) {
            corrupted = true;
            this.usePlayerItem(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else if (stack.is(YavpmTags.Items.CRIMSON_MOONGUS_FOOD_CORRUPTED) && this.getMoongusVariant() == Variant.CRIMSON && corrupted && this.potionContents == null) {
            Item item = stack.getItem();
            this.potionContents = CRIMSON_MOONGUS_FOOD_CORRUPTED.get(item);
            this.usePlayerItem(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else if (stack.is(YavpmTags.Items.CRIMSON_MOONGUS_FOOD) && this.getMoongusVariant() == Variant.CRIMSON && !corrupted && this.potionContents == null) {
            Item item = stack.getItem();
            this.potionContents = CRIMSON_MOONGUS_FOOD.get(item);
            this.usePlayerItem(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else if (stack.is(YavpmTags.Items.WARPED_MOONGUS_FOOD) && this.getMoongusVariant() == Variant.WARPED && this.potionContents == null) {
            Item item = stack.getItem();
            this.potionContents = WARPED_MOONGUS_FOOD.get(item);
            this.usePlayerItem(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        else if (stack.is(Items.SHEARS) && this.readyForShearing() && this.level() instanceof ServerLevel serverLevel) {
            this.shear(serverLevel, SoundSource.PLAYERS, stack);
            this.gameEvent(GameEvent.SHEAR, player);
            if (!this.level().isClientSide()) {
                stack.hurtAndBreak(1, player, hand.asEquipmentSlot());
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }


    protected Block getMushroom() {
        if (this.getMoongusVariant() == Variant.CRIMSON) {
            return Blocks.CRIMSON_FUNGUS;
        } else {
            return Blocks.WARPED_FUNGUS;
        }
    }

    public boolean isSheared() {
        return isSheared;
    }

    @Override
    public void shear(ServerLevel world, SoundSource shearedSoundCategory, ItemStack shears) {
        this.level().playSound(null, this, YavpmSounds.ENTITY_MOONGUS_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.isSheared = true;
        for (int i = 0; i < 5; i++) {
            this.level().addFreshEntity(
                    new ItemEntity(
                            this.level(), this.getX(), this.getY(1.0), this.getZ(), new ItemStack(this.getMushroom())
                    )
            );
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby() && !this.isSheared;
    }

    public void setMoongusVariant(Variant variant) {
        this.entityData.set(TYPE, variant.getIndex());
    }

    public Variant getMoongusVariant() {
        return Variant.fromIndex(this.entityData.get(TYPE));
    }

    public enum Variant implements StringRepresentable {
        CRIMSON("crimson", 0, Blocks.CRIMSON_FUNGUS.defaultBlockState()),
        WARPED("warped", 1, Blocks.WARPED_FUNGUS.defaultBlockState());

        public static final FungusCowEntity.Variant DEFAULT = CRIMSON;
        public static final Codec<FungusCowEntity.Variant> CODEC = StringRepresentable.fromEnum(FungusCowEntity.Variant::values);
        private static final IntFunction<FungusCowEntity.Variant> INDEX_MAPPER = ByIdMap.continuous(
                FungusCowEntity.Variant::getIndex, values(), ByIdMap.OutOfBoundsStrategy.CLAMP
        );
        public static final StreamCodec<ByteBuf, FungusCowEntity.Variant> PACKET_CODEC = ByteBufCodecs.idMapper(INDEX_MAPPER, FungusCowEntity.Variant::getIndex);
        private final String name;
        final int index;
        private final BlockState fungus;

        Variant(final String name, final int index, final BlockState mushroom) {
            this.name = name;
            this.index = index;
            this.fungus = mushroom;
        }

        public BlockState getFungusState() {
            return this.fungus;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        private int getIndex() {
            return this.index;
        }

        static FungusCowEntity.Variant fromIndex(int index) {
            return INDEX_MAPPER.apply(index);
        }
    }
}
