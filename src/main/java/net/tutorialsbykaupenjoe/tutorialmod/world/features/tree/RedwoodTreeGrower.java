package net.tutorialsbykaupenjoe.tutorialmod.world.features.tree;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.tutorialsbykaupenjoe.tutorialmod.world.features.ModConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return (ConfiguredFeature<TreeConfiguration, ?>)ModConfiguredFeatures.REDWOOD;
    }
}
