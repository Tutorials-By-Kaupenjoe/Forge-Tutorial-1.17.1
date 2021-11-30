package net.tutorialsbykaupenjoe.tutorialmod.world.features;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> REDWOOD = register("redwood", Feature.TREE.configured(
            new TreeConfiguration.TreeConfigurationBuilder(
                    new SimpleStateProvider(ModBlocks.REDWOOD_LOG.get().defaultBlockState()),
                    new StraightTrunkPlacer(8, 4, 3),
                    new SimpleStateProvider(ModBlocks.REDWOOD_LEAVES.get().defaultBlockState()),
                    new SimpleStateProvider(ModBlocks.REDWOOD_SAPLING.get().defaultBlockState()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)).build()));

    public static final ConfiguredFeature<?, ?> ORCHID_CONFIG = Feature.FLOWER.configured((
                    new RandomPatchConfiguration.GrassConfigurationBuilder(
                            new SimpleStateProvider(ModBlocks.ORCHID.get().defaultBlockState()),
                            SimpleBlockPlacer.INSTANCE)).tries(12).build())
            .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).count(3);

    private static <FC extends FeatureConfiguration>ConfiguredFeature<FC, ?> register(String name,
                                                                                      ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
