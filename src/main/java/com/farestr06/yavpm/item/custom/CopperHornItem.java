package com.farestr06.yavpm.item.custom;

import com.farestr06.yavpm.item.component.CopperInstrument;
import com.farestr06.yavpm.item.component.YavpmDataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.*;

public class CopperHornItem extends Item {
    protected static final Map<Identifier, CopperInstrument> INSTRUMENTS = new HashMap<>();
    private static final StatusEffectInstance BASS_EFFECT = new StatusEffectInstance(
            StatusEffects.RESISTANCE, 16 * 20
    );
    private static final StatusEffectInstance HARMONY_EFFECT = new StatusEffectInstance(
            StatusEffects.STRENGTH, 16 * 20
    );
    private static final StatusEffectInstance MELODY_EFFECT = new StatusEffectInstance(
            StatusEffects.SPEED, 16 * 20
    );

    public CopperHornItem(Settings settings) {
        super(settings);
    }

    public static void registerCopperInstrument(Identifier id, CopperInstrument instrument) {
        INSTRUMENTS.put(id, instrument);
    }

    public static Optional<CopperInstrument> getCopperInstrument(Identifier id) {
        if (INSTRUMENTS.containsKey(id)) {
            return Optional.of(INSTRUMENTS.get(id));
        }
        return Optional.empty();
    }

    public static Map<Identifier, CopperInstrument> getCopperInstruments() {
        return INSTRUMENTS;
    }

    public static ItemStack getStackForInstrument(Item item, CopperInstrument instrument) {
        ItemStack stack = new ItemStack(item);
        stack.set(YavpmDataComponentTypes.COPPER_INSTRUMENT, instrument);
        return stack;
    }

    public static ItemStack getStackForId(Item item, Identifier id) {
        ItemStack stack = new ItemStack(item);
        if (INSTRUMENTS.containsKey(id)) {
            stack.set(YavpmDataComponentTypes.COPPER_INSTRUMENT, INSTRUMENTS.get(id));
        }
        return stack;
    }

    private static TootResult playSound(World world, PlayerEntity player, CopperInstrument instrument) {
        SoundEvent soundEvent;
        TootResult result;
        if (player.isSneaking()) {
            soundEvent = instrument.bass().value();
            result = TootResult.BASS;
        } else if (player.getPitch() <= -45) {
            soundEvent = instrument.harmony().value();
            result = TootResult.HARMONY;
        } else {
            soundEvent = instrument.melody().value();
            result = TootResult.MELODY;
        }
        float f = instrument.range() / 16.0F;
        world.playSoundFromEntity(player, player, soundEvent, SoundCategory.RECORDS, f, 1.0F);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
        return result;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);
            Optional<CopperInstrument> optional = this.getInstrument(stack);
            if (optional.isPresent()) {
                CopperInstrument instrument = optional.get();
                user.setCurrentHand(hand);
                affectUser(world, user, playSound(world, user, instrument));
                user.getItemCooldownManager().set(stack, MathHelper.floor(instrument.useDuration() * 2 * 20f));
                user.incrementStat(Stats.USED.getOrCreateStat(this));
                return ActionResult.CONSUME;
            } else return ActionResult.FAIL;
        } else return ActionResult.PASS;
    }

    private void affectUser(World world, PlayerEntity user, TootResult tootResult) {
        StatusEffectInstance effectInstance = switch (tootResult) {
            case BASS -> BASS_EFFECT;
            case HARMONY -> HARMONY_EFFECT;
            case MELODY -> MELODY_EFFECT;
            case null -> null;
        };
        if (effectInstance != null) {
            Box box = new Box(user.getX() - 24, user.getY() - 24, user.getZ() - 24,
                    user.getX() + 24, user.getY() + 24, user.getZ() + 24);
            List<PlayerEntity> list = world.getEntitiesByType(TypeFilter.instanceOf(PlayerEntity.class), box, playerEntity -> {
                if (playerEntity.getScoreboardTeam() != null) {
                    return playerEntity.isTeammate(user);
                } else return true;
            });
            for (PlayerEntity player : list) {
                player.addStatusEffect(effectInstance, user);
            }
        }
    }


    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        Optional<CopperInstrument> optional = this.getInstrument(stack);
        return optional.map(instrument -> MathHelper.floor(instrument.useDuration() * 20f)).orElse(0);
    }

    private Optional<CopperInstrument> getInstrument(ItemStack stack) {
        CopperInstrument instrument = stack.get(YavpmDataComponentTypes.COPPER_INSTRUMENT);
        if (instrument != null) {
            return Optional.of(instrument);
        } else {
            List<Identifier> ids = new ArrayList<>(INSTRUMENTS.keySet().stream().toList());
            if (!ids.isEmpty()) {
                Collections.shuffle(ids);
                return Optional.of(INSTRUMENTS.get(ids.getFirst()));
            }
            return Optional.empty();
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    private enum TootResult {
        BASS,
        HARMONY,
        MELODY;
    }
}
