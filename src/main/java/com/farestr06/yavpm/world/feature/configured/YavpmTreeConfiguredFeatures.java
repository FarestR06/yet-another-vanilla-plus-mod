package com.farestr06.yavpm.world.feature.configured;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static com.farestr06.yavpm.YetAnotherVanillaPlusMod.makeId;

public class YavpmTreeConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE = registerKey("apple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_BEES_0002 = registerKey("apple_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_BEES_002 = registerKey("apple_bees_002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_BEES_005 = registerKey("apple_bees_005");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE = registerKey("fancy_apple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_0002 = registerKey("fancy_apple_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_002 = registerKey("fancy_apple_bees_002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_005 = registerKey("fancy_apple_bees_005");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PERSIMMON = registerKey("persimmon");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_PERSIMMON = registerKey("fancy_persimmon");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PRICKLE = registerKey("prickle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_PRICKLE = registerKey("fancy_prickle");

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
    public static final SaplingGenerator PERSIMMON_GENERATOR = new SaplingGenerator(
            "persimmon",
            0.2f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PERSIMMON),
            Optional.of(FANCY_PERSIMMON),
            Optional.empty(),
            Optional.empty()
    );
    public static final SaplingGenerator PRICKLE_GENERATOR = new SaplingGenerator(
            "prickle",
            0.15f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PRICKLE),
            Optional.of(FANCY_PRICKLE),
            Optional.empty(),
            Optional.empty()
    );

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        BeehiveTreeDecorator beehiveTreeDecorator0002 = new BeehiveTreeDecorator(0.002F);
        BeehiveTreeDecorator beehiveTreeDecorator002 = new BeehiveTreeDecorator(0.02F);
        BeehiveTreeDecorator beehiveTreeDecorator005 = new BeehiveTreeDecorator(0.05F);

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
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, FC configuration) {
        context.register(key, new ConfiguredFeature<>((F) Feature.TREE, configuration));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, makeId(name));
    }

    private static TreeFeatureConfig.Builder makePrickleConfig() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(YavpmBlocks.PRICKLE_LOG),
                new StraightTrunkPlacer(6, 3, 0),
                BlockStateProvider.of(Blocks.AIR),
                new BlobFoliagePlacer(ConstantIntProvider.ZERO, ConstantIntProvider.ZERO, 0),
                new TwoLayersFeatureSize(0, 0, 0)
        ).dirtProvider(BlockStateProvider.of(Blocks.END_STONE));
    }


    private static TreeFeatureConfig.Builder makeFancyPrickleConfig() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(YavpmBlocks.PRICKLE_LOG),
                new CherryTrunkPlacer(
                        6,
                        1,
                        2,
                        new WeightedListIntProvider(
                                DataPool.<IntProvider>builder().add(ConstantIntProvider.create(1), 1)
                                        .add(ConstantIntProvider.create(1), 1)
                                        .add(ConstantIntProvider.create(2), 1)
                                        .build()
                        ),
                        UniformIntProvider.create(2, 4), UniformIntProvider.create(-4, -3), UniformIntProvider.create(-1, 0)
                ),
                BlockStateProvider.of(Blocks.AIR),
                new BlobFoliagePlacer(ConstantIntProvider.ZERO, ConstantIntProvider.ZERO, 0),
                new TwoLayersFeatureSize(0, 0, 0)
        ).dirtProvider(BlockStateProvider.of(Blocks.END_STONE)));
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

    private static TreeFeatureConfig.Builder makePersimmonConfig() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(YavpmBlocks.PERSIMMON_LOG),
                new BendingTrunkPlacer(4, 2, 0, 3, UniformIntProvider.create(1, 2)),
                BlockStateProvider.of(YavpmBlocks.PERSIMMON_LEAVES),
                new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 40),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static TreeFeatureConfig.Builder makeFancyPersimmonConfig() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(YavpmBlocks.PERSIMMON_LOG),
                new BendingTrunkPlacer(8, 4, 0, 6, UniformIntProvider.create(2, 3)),
                BlockStateProvider.of(YavpmBlocks.PERSIMMON_LEAVES),
                new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 50),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }
}
