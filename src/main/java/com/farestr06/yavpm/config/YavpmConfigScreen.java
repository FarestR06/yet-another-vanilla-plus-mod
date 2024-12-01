package com.farestr06.yavpm.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.text.Text;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;
import static com.farestr06.yavpm.config.YavpmConfig.HANDLER;

public class YavpmConfigScreen implements ModMenuApi {
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
            ).controller(TickBoxControllerBuilder::create)
            .build();
    protected static final Option<Boolean> VOID_WATER_SOURCE_CONVERSION = Option.<Boolean>createBuilder()
            .name(Text.translatable("option.yavpm.void_water_source_conversion.title"))
            .description(OptionDescription.createBuilder()
                    .text(Text.translatable("option.yavpm.void_water_source_conversion.desc"))
                    // .image(makeId("textures/config/void_water_source_conversion.png"), 64, 64)
                    .build()
            )
            .binding(
                    true,
                    () -> HANDLER.instance().voidWaterSourceConversion,
                    newVal -> HANDLER.instance().voidWaterSourceConversion = newVal
            ).controller(TickBoxControllerBuilder::create)
            .build();

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
                        .name(Text.translatable("option.yavpm.entities_and_effects"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("option.yavpm.entities_and_effects.void_touched"))
                                .option(VOID_TOUCHED_DAMAGE_MULTIPLIER)
                                .option(VOID_TOUCHED_DRAGON_FIREBALL)
                                .build())
                        .build())
                .save(HANDLER::save)
                .build().generateScreen(screen);
    }
}
