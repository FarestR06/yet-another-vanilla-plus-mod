package com.farestr06.yavpm.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class YavpmConfigScreen implements ModMenuApi {
    private static final Style SGA = Style.EMPTY.withFont(Identifier.ofVanilla("alt"));
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("option.yavpm.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.blocks_and_fluids"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.blocks_and_fluids.void"))
                                .option(VOID_WATER_SOURCE_CONVERSION)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.items"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.items.runes"))
                                .description(OptionDescription.of(
                                        Text.translatable("option.yavpm.items.runes.desc1"),
                                        Text.translatable("option.yavpm.items.runes.desc2")
                                ))
                                .option(RUNE_ATTACK_UPGRADE_FACTOR)
                                .option(RUNE_DURABILITY_UPGRADE_FACTOR)
                                .option(RUNE_SPEED_UPGRADE_FACTOR)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.entities_and_effects"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.entities_and_effects.tanuki"))
                                .description(OptionDescription.of(
                                        Text.translatable("option.yavpm.entities_and_effects.tanuki.desc1"),
                                        Text.translatable("option.yavpm.entities_and_effects.tanuki.desc2")
                                ))
                                .option(TANUKI_TRANSFORM_CHANCE)
                                .option(TANUKI_BASE_TRANSFORM_DELAY)
                                .option(TANUKI_RANDOM_TRANSFORM_DELAY)
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.entities_and_effects.void_touched"))
                                .description(OptionDescription.of(
                                        Text.translatable("option.yavpm.entities_and_effects.void_touched.desc")
                                ))
                                .option(VOID_TOUCHED_DAMAGE_MULTIPLIER)
                                .option(VOID_TOUCHED_DRAGON_FIREBALL)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.easter_eggs").setStyle(SGA))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.easter_eggs.splashes").setStyle(SGA))
                                .option(SNAPSHOT_DAY)
                                .option(FARESTS_BIRTHDAY)
                                .build())
                        .build())
                .save(HANDLER::save)
                .build().generateScreen(screen);
    }
    // region Entities and Effects
    protected static final Option<Integer> TANUKI_BASE_TRANSFORM_DELAY = Option.<Integer>createBuilder()
            .name(Text.translatable("option.yavpm.tanuki_base_transform_delay.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.tanuki_base_transform_delay.desc"))
                    .build()
            )
            .binding(
                    2000,
                    () -> HANDLER.instance().tanukiBaseTransformDelay,
                    newVal -> HANDLER.instance().tanukiBaseTransformDelay = newVal
            ).controller(opt -> IntegerFieldControllerBuilder.create(opt).range(500, 4000))
            .build();
    protected static final Option<Integer> TANUKI_RANDOM_TRANSFORM_DELAY = Option.<Integer>createBuilder()

            .name(Text.translatable("option.yavpm.tanuki_random_transform_delay.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.tanuki_random_transform_delay.desc"))
                    .build()
            )
            .binding(
                    4000,
                    () -> HANDLER.instance().tanukiRandomTransformDelay,
                    newVal -> HANDLER.instance().tanukiRandomTransformDelay = newVal
            ).controller(opt -> IntegerFieldControllerBuilder.create(opt).range(500, 4000))
            .build();

    protected static final Option<Float> TANUKI_TRANSFORM_CHANCE = Option.<Float>createBuilder()
            .name(Text.translatable("option.yavpm.tanuki_transform_chance.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.tanuki_transform_chance.desc"))
                    .build()
            )
            .binding(
                    0.3f,
                    () -> HANDLER.instance().tanukiTransformChance,
                    newVal -> HANDLER.instance().tanukiTransformChance = newVal
            ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(0f, 1f))
            .build();
    protected static final Option<Float> VOID_TOUCHED_DAMAGE_MULTIPLIER = Option.<Float>createBuilder()
            .name(Text.translatable("option.yavpm.void_touched_damage_multiplier.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.void_touched_damage_multiplier.desc"))
                    .build()
            )
            .binding(
                    1.5f,
                    () -> HANDLER.instance().voidTouchedDamageMultiplier,
                    newVal -> HANDLER.instance().voidTouchedDamageMultiplier = newVal
            ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(0.5f, 2.5f))
            .build();

    protected static final Option<Boolean> VOID_TOUCHED_DRAGON_FIREBALL = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.void_touched_dragon_fireball.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.void_touched_dragon_fireball.desc"))
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().voidTouchedDragonFireball,
                    newVal -> HANDLER.instance().voidTouchedDragonFireball = newVal
            ).controller(BooleanControllerBuilder::create)
            .build();
    // endregion

    protected static final Option<Boolean> VOID_WATER_SOURCE_CONVERSION = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.void_water_source_conversion.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.void_water_source_conversion.desc"))
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().voidWaterSourceConversion,
                    newVal -> HANDLER.instance().voidWaterSourceConversion = newVal
            ).controller(BooleanControllerBuilder::create)
            .build();
    // endregion

    // region Items
    protected static final Option<Float> RUNE_ATTACK_UPGRADE_FACTOR = Option.<Float>createBuilder()
            .name(Text.translatable("option.yavpm.rune_attack_upgrade_factor.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.rune_attack_upgrade_factor.desc"))
                    .build()
            )
            .binding(
                    1.2f,
                    () -> HANDLER.instance().runeAttackUpgradeFactor,
                    newVal -> HANDLER.instance().runeAttackUpgradeFactor = newVal
            ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(1f, 2.5f))
            .build();

    protected static final Option<Float> RUNE_DURABILITY_UPGRADE_FACTOR = Option.<Float>createBuilder()
            .name(Text.translatable("option.yavpm.rune_durability_upgrade_factor.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.rune_durability_upgrade_factor.desc"))
                    .build()
            )
            .binding(
                    1.2f,
                    () -> HANDLER.instance().runeDurabilityUpgradeFactor,
                    newVal -> HANDLER.instance().runeDurabilityUpgradeFactor = newVal
            ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(1f, 2.5f))
            .build();

    protected static final Option<Float> RUNE_SPEED_UPGRADE_FACTOR = Option.<Float>createBuilder()
            .name(Text.translatable("option.yavpm.rune_speed_upgrade_factor.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.rune_speed_upgrade_factor.desc"))
                    .build()
            )
            .binding(
                    1.2f,
                    () -> HANDLER.instance().runeSpeedUpgradeFactor,
                    newVal -> HANDLER.instance().runeSpeedUpgradeFactor = newVal
            ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(1f, 2.5f))
            .build();
    // endregion
    
    // region Easter Eggs
    protected static final Option<Float> SNAPSHOT_DAY = Option.<Float>createBuilder()
            .name(Text.translatable("option.yavpm.snapshot_day.title").setStyle(SGA))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.snapshot_day.desc").setStyle(SGA))
                    .build()
            )
            .binding(
                    0.3f,
                    () -> HANDLER.instance().snapshotDaySplashChance,
                    newVal -> HANDLER.instance().snapshotDaySplashChance = newVal
            ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(0f, 1f))
            .build();

    protected static final Option<Boolean> FARESTS_BIRTHDAY = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.farests_birthday.title").setStyle(SGA))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.farests_birthday.desc").setStyle(SGA))
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().farestsBirthday,
                    newVal -> HANDLER.instance().farestsBirthday = newVal
            ).controller(BooleanControllerBuilder::create)
            .build();
    // endregion
}
