package net.tutorialsbykaupenjoe.tutorialmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.TITANIUM_DOOR.get())
                .define('T', ModItems.TITANIUM_INGOT.get())
                .pattern("TT")
                .pattern("TT")
                .pattern("TT")
                .unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get()))
                .save(pFinishedRecipeConsumer);
    }
}
