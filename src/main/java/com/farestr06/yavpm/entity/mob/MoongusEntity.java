package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.entity.YavpmDamageTypes;
import com.farestr06.yavpm.entity.YavpmEntities;
import com.farestr06.yavpm.item.YavpmPotions;
import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.util.YavpmTags;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

import static com.farestr06.yavpm.item.YavpmItems.*;

public class MoongusEntity extends CowEntity implements Shearable {
    private static final TrackedData<Integer> TYPE = DataTracker.registerData(MoongusEntity.class, TrackedDataHandlerRegistry.INTEGER);
    
    @Nullable
    private RegistryEntry<Potion> potionContents;

    private boolean corrupted = false;
    private boolean isSheared = false;

    public MoongusEntity(EntityType<? extends MoongusEntity> entityVariant, World world) {
        super(entityVariant, world);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return (world.getBlockState(pos.down()).isOf(Blocks.CRIMSON_NYLIUM) || world.getBlockState(pos.down()).isOf(Blocks.WARPED_NYLIUM)) ? 10f : 0f;
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        if (world.getBiome(this.getBlockPos()).isIn(YavpmTags.Biomes.SPAWNS_WARPED_MOONGUS)) {
            this.setMoongusVariant(Variant.WARPED);
        } else if (world.getBiome(this.getBlockPos()).isIn(YavpmTags.Biomes.SPAWNS_CRIMSON_MOONGUS)) {
            this.setMoongusVariant(Variant.CRIMSON);
        } else {
            this.setMoongusVariant(world.getRandom().nextBoolean() ? Variant.CRIMSON : Variant.WARPED);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Nullable
    @Override
    public MoongusEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return YavpmEntities.MOONGUS.create(serverWorld, SpawnReason.BREEDING);
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        BlockState state = world.getBlockState(getBlockPos().down());
        return state.isOf(Blocks.CRIMSON_NYLIUM) || state.isOf(Blocks.WARPED_NYLIUM);
    }

    @Override
    public void tick() {
        super.tick();
        if (isSheared && age % 30 == 0 && this.getWorld() instanceof ServerWorld serverWorld) {
            this.damage(serverWorld, YavpmDamageTypes.cut(serverWorld), 1.5f);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, Variant.DEFAULT.getIndex());
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getMoongusVariant().getIndex());
        if (this.potionContents != null) {
            Potion.CODEC.encodeStart(NbtOps.INSTANCE, this.potionContents).ifSuccess(nbtElement -> nbt.put("potion_contents", nbtElement));
        }
        nbt.putBoolean("Corrupted", corrupted);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setMoongusVariant(MoongusEntity.Variant.fromIndex(nbt.getInt("Variant").orElse(Variant.DEFAULT.getIndex())));
        if (nbt.contains("potion_contents")) {
            Potion.CODEC
                    .parse(NbtOps.INSTANCE, nbt.get("potion_contents"))
                    .ifSuccess(component -> this.potionContents = component);
        }
        corrupted = nbt.getBoolean("Corrupted").orElse(false);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        // Get stack from player
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.GLASS_BOTTLE) && !this.isBaby()) {
            ItemStack potion = ItemStack.EMPTY;
            if (getMoongusVariant() == Variant.CRIMSON) {
                if (potionContents != null) {
                    potion = PotionContentsComponent.createStack(Items.POTION, potionContents);
                    potionContents = null;
                } else {
                    potion = PotionContentsComponent.createStack(Items.POTION, Potions.AWKWARD);
                }
                corrupted = false;
                this.playSound(YavpmSounds.ENTITY_MOONGUS_MILK_CRIMSON, 1, 1);
            } else if (getMoongusVariant() == Variant.WARPED) {
                if (potionContents != null) {
                    potion = PotionContentsComponent.createStack(Items.POTION, potionContents);
                    potionContents = null;
                } else {
                    potion = PotionContentsComponent.createStack(Items.POTION, YavpmPotions.WEIRD);
                }
                this.playSound(YavpmSounds.ENTITY_MOONGUS_MILK_WARPED, 1, 1);
            }
            ItemStack exchangedStack = ItemUsage.exchangeStack(stack, player, potion, false);
            player.setStackInHand(hand, exchangedStack);
            return ActionResult.SUCCESS;
        } else if (stack.isOf(Items.FERMENTED_SPIDER_EYE) && this.getMoongusVariant() == Variant.CRIMSON && !this.corrupted && this.potionContents == null) {
            corrupted = true;
            this.eat(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return ActionResult.SUCCESS;
        } else if (stack.isIn(YavpmTags.Items.CRIMSON_MOONGUS_FOOD_CORRUPTED) && this.getMoongusVariant() == Variant.CRIMSON && corrupted && this.potionContents == null) {
            Item item = stack.getItem();
            this.potionContents = CRIMSON_MOONGUS_FOOD_CORRUPTED.get(item);
            this.eat(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return ActionResult.SUCCESS;
        } else if (stack.isIn(YavpmTags.Items.CRIMSON_MOONGUS_FOOD) && this.getMoongusVariant() == Variant.CRIMSON && !corrupted && this.potionContents == null) {
            Item item = stack.getItem();
            this.potionContents = CRIMSON_MOONGUS_FOOD.get(item);
            this.eat(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return ActionResult.SUCCESS;
        } else if (stack.isIn(YavpmTags.Items.WARPED_MOONGUS_FOOD) && this.getMoongusVariant() == Variant.WARPED && this.potionContents == null) {
            Item item = stack.getItem();
            this.potionContents = WARPED_MOONGUS_FOOD.get(item);
            this.eat(player, hand, stack);
            this.playSound(YavpmSounds.ENTITY_MOONGUS_EAT, 2.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
        else if (stack.isOf(Items.SHEARS) && this.isShearable() && this.getWorld() instanceof ServerWorld serverWorld) {
            this.sheared(serverWorld, SoundCategory.PLAYERS, stack);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient) {
                stack.damage(1, player, getSlotForHand(hand));
            }
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
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
    public void sheared(ServerWorld world, SoundCategory shearedSoundCategory, ItemStack shears) {
        this.getWorld().playSoundFromEntity(null, this, YavpmSounds.ENTITY_MOONGUS_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.isSheared = true;
        for (int i = 0; i < 5; i++) {
            this.getWorld()
                    .spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getBodyY(1.0), this.getZ(), new ItemStack(this.getMushroom())));
        }
    }

    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby() && !this.isSheared;
    }

    public void setMoongusVariant(Variant variant) {
        this.dataTracker.set(TYPE, variant.getIndex());
    }

    public Variant getMoongusVariant() {
        return Variant.fromIndex(this.dataTracker.get(TYPE));
    }

    public static enum Variant implements StringIdentifiable {
        CRIMSON("crimson", 0, Blocks.CRIMSON_FUNGUS.getDefaultState()),
        WARPED("warped", 1, Blocks.WARPED_FUNGUS.getDefaultState());

        public static final MoongusEntity.Variant DEFAULT = CRIMSON;
        public static final Codec<MoongusEntity.Variant> CODEC = StringIdentifiable.createCodec(MoongusEntity.Variant::values);
        private static final IntFunction<MoongusEntity.Variant> INDEX_MAPPER = ValueLists.createIndexToValueFunction(
                MoongusEntity.Variant::getIndex, values(), ValueLists.OutOfBoundsHandling.CLAMP
        );
        public static final PacketCodec<ByteBuf, MoongusEntity.Variant> PACKET_CODEC = PacketCodecs.indexed(INDEX_MAPPER, MoongusEntity.Variant::getIndex);
        private final String name;
        final int index;
        private final BlockState fungus;

        private Variant(final String name, final int index, final BlockState mushroom) {
            this.name = name;
            this.index = index;
            this.fungus = mushroom;
        }

        public BlockState getFungusState() {
            return this.fungus;
        }

        @Override
        public String asString() {
            return this.name;
        }

        private int getIndex() {
            return this.index;
        }

        static MoongusEntity.Variant fromIndex(int index) {
            return INDEX_MAPPER.apply(index);
        }
    }
}
