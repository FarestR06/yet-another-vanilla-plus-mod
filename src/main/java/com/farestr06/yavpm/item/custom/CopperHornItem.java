package com.farestr06.yavpm.item.custom;

import com.farestr06.api.util.MathUtil;
import com.farestr06.yavpm.YetAnotherVanillaPlusMod;
import com.farestr06.yavpm.item.component.CopperInstrument;
import com.farestr06.yavpm.item.component.YavpmDataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.*;

public class CopperHornItem extends Item {
    protected static final Map<Identifier, CopperInstrument> INSTRUMENTS = new HashMap<>();

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
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        CopperInstrument component = stack.get(YavpmDataComponentTypes.COPPER_INSTRUMENT);
        if (component != null) {
            MutableText text = component.description().copy();
            Texts.setStyleIfAbsent(text, Style.EMPTY.withColor(Formatting.GRAY));
            tooltip.add(text);
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        Optional<CopperInstrument> optional = this.getInstrument(stack);
        if (optional.isPresent()) {
            CopperInstrument instrument = optional.get();
            user.setCurrentHand(hand);
            affectWorld(world, user, playSound(world, user, instrument));
            user.getItemCooldownManager().set(stack, MathHelper.floor(instrument.useDuration() * 2 * 20f));
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.CONSUME;
        } else return ActionResult.FAIL;
    }

    private void affectWorld(World world, PlayerEntity player, TootResult result) {
        if (world instanceof ServerWorld) {
            switch (result) {
                case BASS -> {
                    List<HostileEntity> list = world.getEntitiesByType(
                            TypeFilter.instanceOf(HostileEntity.class),
                            Box.of(player.getPos(), 24, 8, 24),
                            hostileEntity -> true
                    );
                    for (HostileEntity entity : list) {
                        entity.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.SLOWNESS,
                                24 * 20,
                                3
                        ));
                    }
                }
                case HARMONY -> {
                    float healAmount = MathUtil.randomBigFloat(world.getRandom(), 6, 18);
                    player.heal(MathUtil.roundToHalf(healAmount));
                }
                case MELODY -> {
                    List<AnimalEntity> list = world.getEntitiesByType(
                            TypeFilter.instanceOf(AnimalEntity.class),
                            Box.of(player.getPos(), 24, 8, 24),
                            animalEntity -> true
                    );
                    for (AnimalEntity entity : list) {
                        entity.getNavigation().startMovingTo(player, 1.2);
                    }
                }
                case null, default -> {
                    YetAnotherVanillaPlusMod.LOGGER.warn("Couldn't affect world, as no TootResult was passed in");
                }
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
