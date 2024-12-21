package com.farestr06.yavpm.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import net.minecraft.text.Text;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class YavpmConfigScreen implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("option.yavpm.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.blocks_and_fluids"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.blocks_and_fluids.glowing_obsidian"))
                                .option(GLOWING_OBSIDIAN_LUMINANCE)
                                .option(SOUL_GLOWING_OBSIDIAN_LUMINANCE)
                                .build())
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
                    .image(makeId("textures/config/void_touched_damage_multiplier.png"), 64, 64)
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
                    .image(makeId("textures/config/void_touched_dragon_fireball.png"), 64, 64)
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().voidTouchedDragonFireball,
                    newVal -> HANDLER.instance().voidTouchedDragonFireball = newVal
            ).controller(BooleanControllerBuilder::create)
            .build();
    // endregion

    // region Blocks and Fluids
    protected static final Option<Integer> GLOWING_OBSIDIAN_LUMINANCE = Option.<Integer>createBuilder()
            .name(Text.translatable("option.yavpm.glowing_obsidian_luminance.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.glowing_obsidian_luminance.desc"))
                    .build()
            )
            .binding(
                    15,
                    () -> HANDLER.instance().glowingObsidianLuminance,
                    newVal -> HANDLER.instance().glowingObsidianLuminance = newVal
            )
            .controller(IntegerFieldControllerBuilder::create)
            .build();
    protected static final Option<Integer> SOUL_GLOWING_OBSIDIAN_LUMINANCE = Option.<Integer>createBuilder()
            .name(Text.translatable("option.yavpm.soul_glowing_obsidian_luminance.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.soul_glowing_obsidian_luminance.desc"))
                    .build()
            )
            .binding(
                    11,
                    () -> HANDLER.instance().soulGlowingObsidianLuminance,
                    newVal -> HANDLER.instance().soulGlowingObsidianLuminance = newVal
            )
            .controller(IntegerFieldControllerBuilder::create)
            .build();

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
}
