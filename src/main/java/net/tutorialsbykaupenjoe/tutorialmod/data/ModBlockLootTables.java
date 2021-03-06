package net.tutorialsbykaupenjoe.tutorialmod.data;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.TomatoPlantBlock;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        this.add(ModBlocks.TITANIUM_SLAB.get(), (block) -> {
            return createSlabItemTable(ModBlocks.TITANIUM_SLAB.get());
        });

        this.add(ModBlocks.TITANIUM_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.RAW_TITANIUM.get());
        });

        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_DOOR.get());
        this.dropSelf(ModBlocks.TITANIUM_TRAPDOOR.get());
        this.dropSelf(ModBlocks.TITANIUM_BUTTON.get());
        this.dropSelf(ModBlocks.TITANIUM_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.TITANIUM_FENCE.get());
        this.dropSelf(ModBlocks.TITANIUM_WALL.get());
        this.dropSelf(ModBlocks.TITANIUM_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SPEEDY_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_STAIRS.get());
        this.dropSelf(ModBlocks.TEST_BLOCK.get());

        LootItemCondition.Builder tomatoLootConditionBuilder = LootItemBlockStatePropertyCondition.
                hasBlockStateProperties(ModBlocks.TOMATO_PLANT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoPlantBlock.AGE, 7));

        this.add(ModBlocks.TOMATO_PLANT.get(), createCropDrops(ModBlocks.TOMATO_PLANT.get(),
                ModItems.TOMATO.get(), ModItems.TOMATO_SEEDS.get(), tomatoLootConditionBuilder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
