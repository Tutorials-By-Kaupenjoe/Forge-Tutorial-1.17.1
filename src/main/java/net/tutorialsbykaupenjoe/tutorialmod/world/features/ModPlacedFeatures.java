package net.tutorialsbykaupenjoe.tutorialmod.world.features;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacedFeatures {
    public static final PlacedFeature REDWOOD_PLACED = PlacementUtils.register("redwood_placed",
            ModConfiguredFeatures.REDWOOD_TREE_CHECKED.placed(VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2))));

    public static final PlacedFeature ORCHID_PLACED = PlacementUtils.register("orchid_placed",
            ModConfiguredFeatures.ORCHID_CONFIG.placed(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
}
