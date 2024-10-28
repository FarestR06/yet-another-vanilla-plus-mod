package com.farestr06.yavpm.entity.mob;

import com.farestr06.yavpm.util.YavpmSounds;
import com.farestr06.yavpm.item.YavpmItems;
import com.farestr06.yavpm.util.YavpmTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class MoongusEntity extends MooshroomEntity implements Shearable, VariantHolder<MooshroomEntity.Type> {


    @Nullable
    private RegistryEntry<Potion> potionContents;

    private static final TrackedData<String> TYPE = DataTracker.registerData(MoongusEntity.class, TrackedDataHandlerRegistry.STRING);
    private boolean isSheared = false;
    private PlayerEntity shearer;

    public MoongusEntity(EntityType<? extends MooshroomEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return (world.getBlockState(pos.down()).isOf(Blocks.CRIMSON_NYLIUM) || world.getBlockState(pos.down()).isOf(Blocks.WARPED_NYLIUM)) ? 10f : 0f;
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        if (world.getBiome(this.getBlockPos()).isIn(YavpmTags.Biomes.SPAWNS_WARPED_MOONGUS)) {
            this.setVariant(Type.BROWN);
        } else if (world.getBiome(this.getBlockPos()).isIn(YavpmTags.Biomes.SPAWNS_CRIMSON_MOONGUS)) {
            this.setVariant(Type.RED);
        } else {
            this.setVariant(world.getRandom().nextBoolean() ? Type.RED : Type.BROWN);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Nullable
    @Override
    public MooshroomEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return YavpmMobs.MOONGUS.create(serverWorld);
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return super.canSpawn(world, spawnReason);
    }

    @Override
    public void tick() {
        super.tick();
        if (isSheared) {
            this.damage(this.getWorld().getDamageSources().playerAttack(shearer), 1);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, Type.RED.name());
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        if (this.potionContents != null) {
            Potion.CODEC.encodeStart(NbtOps.INSTANCE, this.potionContents).ifSuccess(nbtElement -> nbt.put("potion_contents", nbtElement));
        }
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("potion_contents", NbtElement.LIST_TYPE)) {
            Potion.CODEC
                    .parse(NbtOps.INSTANCE, nbt.get("potion_contents"))
                    .ifSuccess(component -> this.potionContents = component);
        }
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        // Get stack from player
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.GLASS_BOTTLE) && !this.isBaby()) {
            ItemStack potion = ItemStack.EMPTY;
            if (getVariant() == Type.RED) {
                if (potionContents != null) {
                    potion = PotionContentsComponent.createStack(Items.POTION, potionContents);
                    potionContents = null;
                } else {
                    potion = PotionContentsComponent.createStack(Items.POTION, Potions.AWKWARD);
                }
                this.playSound(YavpmSounds.ENTITY_MOONGUS_MILK_CRIMSON, 1, 1);
            } else if (getVariant() == Type.BROWN) {
                potion = PotionContentsComponent.createStack(Items.POTION, Potions.MUNDANE);
                potionContents = null;
                this.playSound(YavpmSounds.ENTITY_MOONGUS_MILK_WARPED, 1, 1);
            }
            ItemStack exchangedStack = ItemUsage.exchangeStack(stack, player, potion, false);
            player.setStackInHand(hand, exchangedStack);
            return ActionResult.success(this.getWorld().isClient());
        } else if (
                (stack.isIn(YavpmTags.Items.CRIMSON_MOONGUS_FOOD) && this.getVariant() == Type.RED)
                        || (stack.isIn(YavpmTags.Items.WARPED_MOONGUS_FOOD) && this.getVariant() == Type.BROWN)
        ) {
            Item item = stack.getItem();
            this.potionContents = YavpmItems.MOONGUS_FOOD.get(item);
            this.eat(player, hand, stack);
            return ActionResult.success(this.getWorld().isClient);
        } else if (stack.isOf(Items.SHEARS) && this.isShearable()) {
            this.shearer = player;
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient) {
                stack.damage(1, player, getSlotForHand(hand));
            }
            return ActionResult.success(this.getWorld().isClient);
        }
        return ActionResult.PASS;
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity(null, this, YavpmSounds.ENTITY_MOONGUS_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.isSheared = true;
        for (int i = 0; i < 5; i++) {
            this.getWorld()
                    .spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getBodyY(1.0), this.getZ(), new ItemStack(this.getMushroom())));
        }
    }

    protected Block getMushroom() {
        if (this.getVariant() == Type.RED) {
            return Blocks.CRIMSON_FUNGUS;
        } else {
            return Blocks.WARPED_FUNGUS;
        }
    }

    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
    }
}