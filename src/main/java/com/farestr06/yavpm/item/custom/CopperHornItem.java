package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.world.component.CopperInstrument;
import com.farestr06.yavpm.world.component.YavpmDataComponentTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

import java.util.*;

public class CopperHornItem extends Item {
    protected static final Map<ResourceLocation, CopperInstrument> INSTRUMENTS = new HashMap<>();
    private static final MobEffectInstance BASS_EFFECT = new MobEffectInstance(
            MobEffects.RESISTANCE, 16 * 20
    );
    private static final MobEffectInstance HARMONY_EFFECT = new MobEffectInstance(
            MobEffects.STRENGTH, 16 * 20
    );
    private static final MobEffectInstance MELODY_EFFECT = new MobEffectInstance(
            MobEffects.SPEED, 16 * 20
    );

    public CopperHornItem(net.minecraft.world.item.Item.Properties settings) {
        super(settings);
    }

    public static void registerCopperInstrument(ResourceLocation id, CopperInstrument instrument) {
        INSTRUMENTS.put(id, instrument);
    }

    public static Optional<CopperInstrument> getCopperInstrument(ResourceLocation id) {
        if (INSTRUMENTS.containsKey(id)) {
            return Optional.of(INSTRUMENTS.get(id));
        }
        return Optional.empty();
    }

    public static Map<ResourceLocation, CopperInstrument> getCopperInstruments() {
        return INSTRUMENTS;
    }

    public static ItemStack getStackForInstrument(Item item, CopperInstrument instrument) {
        ItemStack stack = new ItemStack(item);
        stack.set(YavpmDataComponentTypes.Item.COPPER_INSTRUMENT, instrument);
        return stack;
    }

    public static ItemStack getStackForId(Item item, ResourceLocation id) {
        ItemStack stack = new ItemStack(item);
        if (INSTRUMENTS.containsKey(id)) {
            stack.set(YavpmDataComponentTypes.Item.COPPER_INSTRUMENT, INSTRUMENTS.get(id));
        }
        return stack;
    }

    private static TootResult playSound(Level world, Player player, CopperInstrument instrument) {
        SoundEvent soundEvent;
        TootResult result;
        if (player.isShiftKeyDown()) {
            soundEvent = instrument.bass().value();
            result = TootResult.BASS;
        } else if (player.getXRot() <= -45) {
            soundEvent = instrument.harmony().value();
            result = TootResult.HARMONY;
        } else {
            soundEvent = instrument.melody().value();
            result = TootResult.MELODY;
        }
        float f = instrument.range() / 16.0F;
        world.playSound(player, player, soundEvent, SoundSource.RECORDS, f, 1.0F);
        world.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
        return result;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide()) {
            ItemStack stack = user.getItemInHand(hand);
            Optional<CopperInstrument> optional = this.getInstrument(stack);
            if (optional.isPresent()) {
                CopperInstrument instrument = optional.get();
                user.startUsingItem(hand);
                affectUser(world, user, playSound(world, user, instrument));
                user.getCooldowns().addCooldown(stack, Mth.floor(instrument.useDuration() * 2 * 20f));
                user.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResult.CONSUME;
            } else return InteractionResult.FAIL;
        } else return InteractionResult.PASS;
    }

    private void affectUser(Level world, Player user, TootResult tootResult) {
        MobEffectInstance effectInstance = switch (tootResult) {
            case BASS -> BASS_EFFECT;
            case HARMONY -> HARMONY_EFFECT;
            case MELODY -> MELODY_EFFECT;
            case null -> null;
        };
        if (effectInstance != null) {
            AABB box = new AABB(user.getX() - 24, user.getY() - 24, user.getZ() - 24,
                    user.getX() + 24, user.getY() + 24, user.getZ() + 24);
            List<Player> list = world.getEntities(EntityTypeTest.forClass(Player.class), box, playerEntity -> {
                if (playerEntity.getTeam() != null) {
                    return playerEntity.isAlliedTo(user);
                } else return true;
            });
            for (Player player : list) {
                player.addEffect(effectInstance, user);
            }
        }
    }


    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        Optional<CopperInstrument> optional = this.getInstrument(stack);
        return optional.map(instrument -> Mth.floor(instrument.useDuration() * 20f)).orElse(0);
    }

    private Optional<CopperInstrument> getInstrument(ItemStack stack) {
        CopperInstrument instrument = stack.get(YavpmDataComponentTypes.Item.COPPER_INSTRUMENT);
        if (instrument != null) {
            return Optional.of(instrument);
        } else {
            List<ResourceLocation> ids = new ArrayList<>(INSTRUMENTS.keySet().stream().toList());
            if (!ids.isEmpty()) {
                Collections.shuffle(ids);
                return Optional.of(INSTRUMENTS.get(ids.getFirst()));
            }
            return Optional.empty();
        }
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }

    private enum TootResult {
        BASS,
        HARMONY,
        MELODY
    }
}
