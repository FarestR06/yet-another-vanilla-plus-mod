package com.farestr06.yavpm.world.feature.configured;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmMiscConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_KIMBERLITE = registerKey("ore_kimberlite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_WITHER_ROSE = registerKey("patch_wither_rose");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        RuleTest ruleTest = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        register(context, ORE_KIMBERLITE, Feature.ORE, new OreFeatureConfig(ruleTest, YavpmBlocks.KIMBERLITE.getDefaultState(), 20));
        register(
                context, PATCH_WITHER_ROSE, Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(Blocks.WITHER_ROSE), 4)
        );
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, makeId(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
}
