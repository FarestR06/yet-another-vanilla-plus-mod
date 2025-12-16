package com.farestr06.yavpm.world.feature.configured;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmMiscConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NAHCOLITE = of("ore_nahcolite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KIMBERLITE = of("ore_kimberlite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WITHER_ROSE = of("patch_wither_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_VOID_WATER = of("lake_void_water");

    @SuppressWarnings("deprecation")
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest ruleTest = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        RuleTest stoneRuleTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateRuleTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        register(context, ORE_KIMBERLITE, Feature.ORE, new OreConfiguration(ruleTest, YavpmBlocks.KIMBERLITE.defaultBlockState(), 20));

        register(context, ORE_NAHCOLITE, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneRuleTest, YavpmBlocks.NAHCOLITE_ORE.defaultBlockState()),
                                OreConfiguration.target(deepslateRuleTest, YavpmBlocks.DEEPSLATE_NAHCOLITE_ORE.defaultBlockState())
                        ),
                        8
                )
        );


        register(
                context, PATCH_WITHER_ROSE, Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.simple(Blocks.WITHER_ROSE), 4)
        );
        register(
                context, LAKE_VOID_WATER, Feature.LAKE,
                new LakeFeature.Configuration(BlockStateProvider.simple(YavpmBlocks.VOID_WATER.defaultBlockState()), BlockStateProvider.simple(YavpmBlocks.CONGLOMERATE.defaultBlockState()))
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> of(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, makeId(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static RandomPatchConfiguration createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block)));
    }
}
