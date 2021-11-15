package net.tutorialsbykaupenjoe.tutorialmod.block;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.SpeedyBlock;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.TestBlock;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.TomatoPlantBlock;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModCreativeModeTab;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(12f).requiresCorrectToolForDrops()),
            "tooltip.block.tutorialmod.titanium_block");

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SPEEDY_BLOCK = registerBlock("speedy_block",
            () -> new SpeedyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_STAIRS = registerBlock("titanium_stairs",
            () -> new StairBlock(() -> TITANIUM_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_FENCE = registerBlock("titanium_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_WALL = registerBlock("titanium_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_FENCE_GATE = registerBlock("titanium_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_SLAB = registerBlock("titanium_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> TITANIUM_BUTTON = registerBlock("titanium_button",
            () -> new StoneButtonBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_PRESSURE_PLATE = registerBlock("titanium_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TITANIUM_DOOR = registerBlock("titanium_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f)
                    .requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> TITANIUM_TRAPDOOR = registerBlock("titanium_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f)
                    .requiresCorrectToolForDrops().noOcclusion()));


    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new TestBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TOMATO_PLANT = BLOCKS.register("tomato_plant",
            () -> new TomatoPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> ORCHID = registerBlock("orchid",
            () -> new FlowerBlock(MobEffects.BLINDNESS, 2,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)));



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
                tooltip.add(new TranslatableComponent(tooltipKey));
                super.appendHoverText(stack, level, tooltip, flagIn);
            }
        });
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
