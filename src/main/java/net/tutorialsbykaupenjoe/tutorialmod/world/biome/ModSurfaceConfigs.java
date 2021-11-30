package net.tutorialsbykaupenjoe.tutorialmod.world.biome;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;

public class ModSurfaceConfigs {
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> LAVA_SURFACE_BUILDER =
            register("lava_surface", SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderBaseConfiguration(
                    Blocks.MAGMA_BLOCK.defaultBlockState(),
                    ModBlocks.TITANIUM_BLOCK.get().defaultBlockState(),
                    Blocks.SANDSTONE.defaultBlockState())));

    private static <T extends SurfaceBuilderBaseConfiguration> ConfiguredSurfaceBuilder<T> register(String name,
                                                                                                    ConfiguredSurfaceBuilder<T> surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(TutorialMod.MOD_ID, name), surfaceBuilder);
    }
}
