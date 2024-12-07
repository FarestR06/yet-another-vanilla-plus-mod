package com.farestr06.yavpm.world;

import com.farestr06.yavpm.block.YavpmBlocks;
import com.farestr06.yavpm.block.custom.PrickleLogBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_KIMBERLITE = registerKey("ore_kimberlite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE = registerKey("apple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_BEES_0002 = registerKey("apple_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_BEES_002 = registerKey("apple_bees_002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_BEES_005 = registerKey("apple_bees_005");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE = ConfiguredFeatures.of("fancy_apple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_0002 = registerKey("fancy_apple_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_002 = registerKey("fancy_apple_bees_002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_005 = registerKey("fancy_apple_bees_005");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_WITHER_ROSE = registerKey("patch_wither_rose");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PRICKLE = registerKey("prickle");

    public static final SaplingGenerator APPLEWOOD_GENERATOR = new SaplingGenerator(
            "applewood",
            0.1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(APPLE),
            Optional.of(FANCY_APPLE),
            Optional.of(APPLE_BEES_005),
            Optional.of(FANCY_APPLE_BEES_005)
    );

    public static final SaplingGenerator PRICKLE_GENERATOR = new SaplingGenerator(
            "prickle",
            Optional.empty(),
            Optional.of(PRICKLE),
            Optional.empty()
    );


    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {

        RuleTest ruleTest = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        ConfiguredFeatures.register(context, ORE_KIMBERLITE, Feature.ORE, new OreFeatureConfig(ruleTest, YavpmBlocks.KIMBERLITE.getDefaultState(), 16));

        BeehiveTreeDecorator beehiveTreeDecorator0002 = new BeehiveTreeDecorator(0.002F);
        BeehiveTreeDecorator beehiveTreeDecorator002 = new BeehiveTreeDecorator(0.02F);
        BeehiveTreeDecorator beehiveTreeDecorator005 = new BeehiveTreeDecorator(0.05F);

        register(context, APPLE, Feature.TREE, makeAppleConfig().build());
        register(context, APPLE_BEES_0002, Feature.TREE, makeAppleConfig().decorators(List.of(beehiveTreeDecorator0002)).build());
        register(context, APPLE_BEES_002, Feature.TREE, makeAppleConfig().decorators(List.of(beehiveTreeDecorator002)).build());
        register(context, APPLE_BEES_005, Feature.TREE, makeAppleConfig().decorators(List.of(beehiveTreeDecorator005)).build());

        register(context, FANCY_APPLE, Feature.TREE, makeFancyAppleConfig().build());
        register(context, FANCY_APPLE_BEES_0002, Feature.TREE, makeFancyAppleConfig().decorators(List.of(beehiveTreeDecorator0002)).build());
        register(context, FANCY_APPLE_BEES_002, Feature.TREE, makeFancyAppleConfig().decorators(List.of(beehiveTreeDecorator002)).build());
        register(context, FANCY_APPLE_BEES_005, Feature.TREE, makeFancyAppleConfig().decorators(List.of(beehiveTreeDecorator005)).build());

        register(context, PRICKLE, Feature.TREE, makePrickleConfig().build());


        register(
                context, PATCH_WITHER_ROSE, Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(Blocks.WITHER_ROSE), 3)
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

    private static TreeFeatureConfig.Builder makePrickleConfig() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(
                        YavpmBlocks.PRICKLE_LOG.getDefaultState()
                                .with(PrickleLogBlock.AXIS, Direction.Axis.Y)
                                .with(PrickleLogBlock.PRICKLY, true)
                ),
                new StraightTrunkPlacer(8, 1, 0),
                BlockStateProvider.of(Blocks.AIR),
                new BlobFoliagePlacer(ConstantIntProvider.ZERO, ConstantIntProvider.ZERO, 0),
                new TwoLayersFeatureSize(0, 0, 0)
        );
    }

    private static TreeFeatureConfig.Builder makeAppleConfig() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(YavpmBlocks.APPLE_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(YavpmBlocks.APPLE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static TreeFeatureConfig.Builder makeFancyAppleConfig() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(YavpmBlocks.APPLE_LOG),
                new LargeOakTrunkPlacer(3, 11, 0),
                BlockStateProvider.of(YavpmBlocks.APPLE_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
        );
    }
}
