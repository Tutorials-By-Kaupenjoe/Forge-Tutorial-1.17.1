package net.tutorialsbykaupenjoe.tutorialmod.world.features;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> REDWOOD = register("redwood", Feature.TREE.configured(
            new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.REDWOOD_LOG.get()),
                    new StraightTrunkPlacer(8, 4, 3),
                    BlockStateProvider.simple(ModBlocks.REDWOOD_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)).build()));

    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> REDWOOD_TREE_CHECKED =
            FeatureUtils.register("redwood_feature",
                    Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            REDWOOD.filteredByBlockSurvival(ModBlocks.REDWOOD_SAPLING.get()), 0.1f)),
                            REDWOOD.filteredByBlockSurvival(ModBlocks.REDWOOD_SAPLING.get()))));

    public static final ConfiguredFeature<?, ?> ORCHID_CONFIG = FeatureUtils.register("flower_orchid",
            Feature.FLOWER.configured(new RandomPatchConfiguration(32, 6, 2,
                    () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ORCHID.get())))
                            .onlyWhenEmpty())));


    private static <FC extends FeatureConfiguration>ConfiguredFeature<FC, ?> register(String name,
                                                                                      ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
