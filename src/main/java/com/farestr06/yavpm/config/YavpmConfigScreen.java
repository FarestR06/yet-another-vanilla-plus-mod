package com.farestr06.yavpm.config;

import com.farestr06.api.util.FarestsUtils;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class YavpmConfigScreen implements ModMenuApi {
    private static final Style SGA = Style.EMPTY.withFont(Identifier.ofVanilla("alt"));
    private static final Style INFO = Style.EMPTY.withFormatting(Formatting.GRAY, Formatting.ITALIC);
    private static final Style COMPAT_DESC = Style.EMPTY.withFormatting(Formatting.YELLOW, Formatting.ITALIC);
    private static final Text RESOURCE_CONDITION_NOTE = Text.translatable("option.yavpm.resourcecondition")
            .setStyle(INFO);
    private static final Text EXPERIMENT_WARNING = Text.translatable("option.yavpm.experiment.warning").formatted(Formatting.RED);
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("option.yavpm.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.blocks_and_fluids"))
                        .option(GLOWING_OBSIDIAN_LUMINANCE)
                        .option(SOUL_GLOWING_OBSIDIAN_LUMINANCE)
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.items"))
                        .option(BABY_KEY_CRIES)
                        .option(WEIRD_TRIAL_CHAMBER_POTIONS)
                        .option(RARE_EQUIPMENT_RECIPES)
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
                        .name(Text.translatable("option.yavpm.experiment")) // Here be dragons!
                        .option(HELP_COMMAND_EXPERIMENT)
                        .option(RECYCLER_EXPERIMENT)
                        .option(NULLIUM_EXPERIMENT)
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("option.yavpm.compat"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.compat.vanillatweaks"))
                                .description(OptionDescription.createBuilder()
                                        .text(Text.translatable("option.yavpm.compat.vanillatweaks.desc"))
                                        .image(makeId("textures/config/vanilla_tweaks.png"), 320, 320)
                                        .text(Text.translatable("option.yavpm.compat.vanillatweaks.info1").setStyle(COMPAT_DESC))
                                        .text(Text.translatable("option.yavpm.compat.vanillatweaks.info2").setStyle(INFO))
                                        .build()
                                )
                                .option(VANILLA_TWEAKS_LINK)
                                .option(DROPPER_TO_RECYCLER)
                                .option(DOUBLE_SLABS)
                                .option(MORE_TRAPDOORS)
                                .option(MORE_STAIRS)
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
            ).controller(opt -> IntegerSliderControllerBuilder.create(opt).range(500, 5000).step(50)
                    .formatValue(val -> {
                        float valInSeconds = val / 20f;
                        return Text.translatable("option.yavpm.format.ticks", val, valInSeconds);
                    }))
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
            ).controller(opt -> IntegerSliderControllerBuilder.create(opt).range(500, 5000).step(50)
                    .formatValue(val -> {
                        float valInSeconds = val / 20f;
                        return Text.translatable("option.yavpm.format.ticks", val, valInSeconds);
                    }))
            // ).controller(opt -> IntegerFieldControllerBuilder.create(opt).range(500, 4000))
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
            ).controller(opt -> FloatSliderControllerBuilder.create(opt).range(0f, 1f).step(0.01f))
            // ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(0f, 1f))
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
            ).controller(opt -> FloatSliderControllerBuilder.create(opt).range(1f, 3f).step(0.1f)
                    .formatValue(val -> Text.translatable("option.yavpm.format.multiplier", val)))
            // ).controller(opt -> FloatFieldControllerBuilder.create(opt).range(0.5f, 2.5f))
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
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    // endregion

    // region Blocks
    protected static final Option<Integer> GLOWING_OBSIDIAN_LUMINANCE = Option.<Integer>createBuilder()
            .name(Text.translatable("option.yavpm.glowing_obsidian_luminance.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.glowing_obsidian_luminance.desc"))
                    .image(makeId("textures/config/glowing_obsidian_luminance.png"), 480, 360)
                    .build()
            )
            .binding(
                    12,
                    () -> HANDLER.instance().glowingObsidianLuminance,
                    newVal -> HANDLER.instance().glowingObsidianLuminance = newVal
            ).controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 15).step(1))
            .build();
    protected static final Option<Integer> SOUL_GLOWING_OBSIDIAN_LUMINANCE = Option.<Integer>createBuilder()
            .name(Text.translatable("option.yavpm.soul_glowing_obsidian_luminance.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.soul_glowing_obsidian_luminance.desc"))
                    .image(makeId("textures/config/soul_glowing_obsidian_luminance.png"), 480, 360)
                    .build()
            )
            .binding(
                    9,
                    () -> HANDLER.instance().soulGlowingObsidianLuminance,
                    newVal -> HANDLER.instance().soulGlowingObsidianLuminance = newVal
            ).controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 15).step(1))
            .build();
    // endregion

    // region Items
    protected static final Option<Boolean> BABY_KEY_CRIES = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.baby_key_cries.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.baby_key_cries.desc"))
                    .image(makeId("textures/config/baby_key_cries.png"), 480, 360)
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().babyKeyCries,
                    newVal -> HANDLER.instance().babyKeyCries = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    protected static final Option<Boolean> WEIRD_TRIAL_CHAMBER_POTIONS = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.weird_trial_chamber_potions.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.weird_trial_chamber_potions.desc"))
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().weirdTrialChamberPotions,
                    newVal -> HANDLER.instance().weirdTrialChamberPotions = newVal
            ).controller(YavpmConfigScreen::booleanBuilder).flag(OptionFlag.GAME_RESTART)
            .build();
    protected static final Option<Boolean> RARE_EQUIPMENT_RECIPES = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.rare_equipment_recipes.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.rare_equipment_recipes.desc"))
                    .text(RESOURCE_CONDITION_NOTE)
                    .image(makeId("textures/config/rare_equipment_recipes.png"), 480, 360)
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().rareEquipmentCraftingRecipes,
                    newVal -> HANDLER.instance().rareEquipmentCraftingRecipes = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    // endregion

    protected static final Option<Boolean> HELP_COMMAND_EXPERIMENT = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.yavpm_help_experiment.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.yavpm_help_experiment.desc"))
                    .text(EXPERIMENT_WARNING)
                    .build()
            )
            .binding(
                    FabricLoader.getInstance().isDevelopmentEnvironment(),
                    () -> HANDLER.instance().yavpmHelpExperiment,
                    newVal -> HANDLER.instance().yavpmHelpExperiment = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    protected static final Option<Boolean> RECYCLER_EXPERIMENT = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.recycler_experiment.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.recycler_experiment.desc"))
                    .text(EXPERIMENT_WARNING)
                    .build()
            )
            .binding(
                    FabricLoader.getInstance().isDevelopmentEnvironment(),
                    () -> HANDLER.instance().recyclerExperiment,
                    newVal -> HANDLER.instance().recyclerExperiment = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    protected static final Option<Boolean> NULLIUM_EXPERIMENT = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.nullium_experiment.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.nullium_experiment.desc"))
                    .text(EXPERIMENT_WARNING)
                    .build()
            )
            .binding(
                    FabricLoader.getInstance().isDevelopmentEnvironment(),
                    () -> HANDLER.instance().nulliumExperiment,
                    newVal -> HANDLER.instance().nulliumExperiment = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();

    // region Compat
    protected static final ButtonOption VANILLA_TWEAKS_LINK = ButtonOption.createBuilder()
            .name(Text.translatable("option.yavpm.compat.vanillatweaks.link.title"))
            .description(
                    OptionDescription.createBuilder()
                            .text(Text.translatable("option.yavpm.compat.vanillatweaks.link.desc"))
                            .build()
            )
            .action((yaclScreen, buttonOption) -> FarestsUtils.openUrl(yaclScreen, "https://vanillatweaks.net"))
            .build();
    protected static final Option<Boolean> DROPPER_TO_RECYCLER = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.dropper_to_recycler.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.dropper_to_recycler.desc"))
                    .text(RESOURCE_CONDITION_NOTE)
                    .build()
            )
            .available(HANDLER.instance().recyclerExperiment)
            .binding(
                    false,
                    () -> HANDLER.instance().dropperToRecycler,
                    newVal -> HANDLER.instance().dropperToRecycler = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    protected static final Option<Boolean> DOUBLE_SLABS = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.double_slabs.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.double_slabs.desc"))
                    .text(RESOURCE_CONDITION_NOTE)
                    .build()
            )
            .binding(
                    false,
                    () -> HANDLER.instance().doubleSlabs,
                    newVal -> HANDLER.instance().doubleSlabs = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    protected static final Option<Boolean> MORE_TRAPDOORS = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.more_trapdoors.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.more_trapdoors.desc"))
                    .text(RESOURCE_CONDITION_NOTE)
                    .build()
            )
            .binding(
                    false,
                    () -> HANDLER.instance().moreTrapdoors,
                    newVal -> HANDLER.instance().moreTrapdoors = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    protected static final Option<Boolean> MORE_STAIRS = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.more_stairs.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.more_stairs.desc"))
                    .text(RESOURCE_CONDITION_NOTE)
                    .build()
            )
            .binding(
                    false,
                    () -> HANDLER.instance().moreStairs,
                    newVal -> HANDLER.instance().moreStairs = newVal
            ).controller(YavpmConfigScreen::booleanBuilder)
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
            ).controller(YavpmConfigScreen::booleanBuilder)
            .build();
    // endregion

    private static BooleanControllerBuilder booleanBuilder(Option<Boolean> option) {
        return BooleanControllerBuilder.create(option).trueFalseFormatter().coloured(true);
    }
}
