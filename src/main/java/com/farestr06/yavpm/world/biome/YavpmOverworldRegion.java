package com.farestr06.yavpm.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class YavpmOverworldRegion extends Region {
    public YavpmOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.HOT))
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY))
                .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.COAST, ParameterUtils.Continentalness.MID_INLAND))
                .erosion(ParameterUtils.Erosion.EROSION_0)
                .depth(ParameterUtils.Depth.SURFACE)
                .weirdness(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING)
                .build().forEach(point -> builder.add(point, YavpmBiomes.Overworld.WITHERED_SCAR));

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.NEUTRAL, ParameterUtils.Temperature.WARM))
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.HUMID))
                .continentalness(ParameterUtils.Continentalness.INLAND)
                .erosion(ParameterUtils.Erosion.EROSION_1)
                .weirdness(ParameterUtils.Weirdness.PEAK_NORMAL)
                .depth(ParameterUtils.Depth.SURFACE)
                .build().forEach(point -> builder.add(point, YavpmBiomes.Overworld.ORCHARD_PEAKS));

        addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder ->
                modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DARK_FOREST, YavpmBiomes.Overworld.EBONY_FOREST));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
