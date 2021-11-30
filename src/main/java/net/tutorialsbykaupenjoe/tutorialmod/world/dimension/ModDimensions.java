package net.tutorialsbykaupenjoe.tutorialmod.world.dimension;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;

public class ModDimensions {
    public static ResourceKey<Level> KJDim = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(TutorialMod.MOD_ID, "kjdim"));
}
