package com.farestr06.yavpm.world.feature.configured;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE = registerKey("apple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_0002 = registerKey("apple_bees_0002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_002 = registerKey("apple_bees_002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_005 = registerKey("apple_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE = registerKey("fancy_apple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_0002 = registerKey("fancy_apple_bees_0002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_002 = registerKey("fancy_apple_bees_002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_005 = registerKey("fancy_apple_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PERSIMMON = registerKey("persimmon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_PERSIMMON = registerKey("fancy_persimmon");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PRICKLE = registerKey("prickle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_PRICKLE = registerKey("fancy_prickle");

    public static final TreeGrower APPLEWOOD_GENERATOR = new TreeGrower(
            "applewood",
            0.1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(APPLE),
            Optional.of(FANCY_APPLE),
            Optional.of(APPLE_BEES_005),
            Optional.of(FANCY_APPLE_BEES_005)
    );
    public static final TreeGrower PERSIMMON_GENERATOR = new TreeGrower(
            "persimmon",
            0.2f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PERSIMMON),
            Optional.of(FANCY_PERSIMMON),
            Optional.empty(),
            Optional.empty()
    );
    public static final TreeGrower PRICKLE_GENERATOR = new TreeGrower(
            "prickle",
            0.15f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PRICKLE),
            Optional.of(FANCY_PRICKLE),
            Optional.empty(),
            Optional.empty()
    );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        BeehiveDecorator beehiveTreeDecorator0002 = new BeehiveDecorator(0.002F);
        BeehiveDecorator beehiveTreeDecorator002 = new BeehiveDecorator(0.02F);
        BeehiveDecorator beehiveTreeDecorator005 = new BeehiveDecorator(0.05F);

        register(context, APPLE, makeAppleConfig().build());
        register(context, APPLE_BEES_0002, makeAppleConfig().decorators(List.of(beehiveTreeDecorator0002)).build());
        register(context, APPLE_BEES_002, makeAppleConfig().decorators(List.of(beehiveTreeDecorator002)).build());
        register(context, APPLE_BEES_005, makeAppleConfig().decorators(List.of(beehiveTreeDecorator005)).build());

        register(context, FANCY_APPLE, makeFancyAppleConfig().build());
        register(context, FANCY_APPLE_BEES_0002, makeFancyAppleConfig().decorators(List.of(beehiveTreeDecorator0002)).build());
        register(context, FANCY_APPLE_BEES_002, makeFancyAppleConfig().decorators(List.of(beehiveTreeDecorator002)).build());
        register(context, FANCY_APPLE_BEES_005, makeFancyAppleConfig().decorators(List.of(beehiveTreeDecorator005)).build());

        register(context, PERSIMMON, makePersimmonConfig().build());
        register(context, FANCY_PERSIMMON, makeFancyPersimmonConfig().build());

        register(context, PRICKLE, makePrickleConfig().build());
        register(context, FANCY_PRICKLE, makeFancyPrickleConfig().build());
    }


    @SuppressWarnings("unchecked")
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key, FC configuration) {
        context.register(key, new ConfiguredFeature<>((F) Feature.TREE, configuration));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, makeId(name));
    }

    private static TreeConfiguration.TreeConfigurationBuilder makePrickleConfig() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(YavpmBlocks.PRICKLE_LOG),
                new StraightTrunkPlacer(6, 3, 0),
                BlockStateProvider.simple(Blocks.AIR),
                new BlobFoliagePlacer(ConstantInt.ZERO, ConstantInt.ZERO, 0),
                new TwoLayersFeatureSize(0, 0, 0)
        ).dirt(BlockStateProvider.simple(Blocks.END_STONE));
    }


    private static TreeConfiguration.TreeConfigurationBuilder makeFancyPrickleConfig() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(YavpmBlocks.PRICKLE_LOG),
                new CherryTrunkPlacer(
                        6,
                        1,
                        2,
                        new WeightedListInt(
                                WeightedList.<IntProvider>builder().add(ConstantInt.of(1), 1)
                                        .add(ConstantInt.of(1), 1)
                                        .add(ConstantInt.of(2), 1)
                                        .build()
                        ),
                        UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)
                ),
                BlockStateProvider.simple(Blocks.AIR),
                new BlobFoliagePlacer(ConstantInt.ZERO, ConstantInt.ZERO, 0),
                new TwoLayersFeatureSize(0, 0, 0)
        ).dirt(BlockStateProvider.simple(Blocks.END_STONE)));
    }

    private static TreeConfiguration.TreeConfigurationBuilder makeAppleConfig() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(YavpmBlocks.APPLE_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(YavpmBlocks.APPLE_LEAVES.defaultBlockState(), 2)
                                .add(YavpmBlocks.FLOWERING_APPLE_LEAVES.defaultBlockState(), 5)
                ),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder makeFancyAppleConfig() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(YavpmBlocks.APPLE_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(YavpmBlocks.APPLE_LEAVES.defaultBlockState(), 2)
                                .add(YavpmBlocks.FLOWERING_APPLE_LEAVES.defaultBlockState(), 5)
                ),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder makePersimmonConfig() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(YavpmBlocks.PERSIMMON_LOG),
                new BendingTrunkPlacer(4, 2, 0, 3, UniformInt.of(1, 2)),
                BlockStateProvider.simple(YavpmBlocks.PERSIMMON_LEAVES),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 40),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder makeFancyPersimmonConfig() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(YavpmBlocks.PERSIMMON_LOG),
                new BendingTrunkPlacer(8, 4, 0, 6, UniformInt.of(2, 3)),
                BlockStateProvider.simple(YavpmBlocks.PERSIMMON_LEAVES),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }
}
