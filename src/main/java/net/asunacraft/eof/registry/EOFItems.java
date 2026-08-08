package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOFItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, EchoesOfFactoria.MODID);

    public static final RegistryObject<Item> ELECTRIC_FURNACE = ITEMS.register(
        "electric_furnace",
        () -> new BlockItem(EOFBlocks.ELECTRIC_FURNACE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> SPHALERITE_ORE = blockItem(EOFBlocks.SPHALERITE_ORE, "sphalerite_ore");
    public static final RegistryObject<Item> DEEPSLATE_SPHALERITE_ORE = blockItem(EOFBlocks.DEEPSLATE_SPHALERITE_ORE, "deepslate_sphalerite_ore");

    public static final RegistryObject<Item> CASSITERITE_ORE = blockItem(EOFBlocks.CASSITERITE_ORE, "cassiterite_ore");
    public static final RegistryObject<Item> DEEPSLATE_CASSITERITE_ORE = blockItem(EOFBlocks.DEEPSLATE_CASSITERITE_ORE, "deepslate_cassiterite_ore");

    public static final RegistryObject<Item> ACANTHITE_ORE = blockItem(EOFBlocks.ACANTHITE_ORE, "acanthite_ore");
    public static final RegistryObject<Item> DEEPSLATE_ACANTHITE_ORE = blockItem(EOFBlocks.DEEPSLATE_ACANTHITE_ORE, "deepslate_acanthite_ore");

    public static final RegistryObject<Item> WOLFRAMITE_ORE = blockItem(EOFBlocks.WOLFRAMITE_ORE, "wolframite_ore");
    public static final RegistryObject<Item> DEEPSLATE_WOLFRAMITE_ORE = blockItem(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE, "deepslate_wolframite_ore");

    public static final RegistryObject<Item> RUTILE_ORE = blockItem(EOFBlocks.RUTILE_ORE, "rutile_ore");
    public static final RegistryObject<Item> DEEPSLATE_RUTILE_ORE = blockItem(EOFBlocks.DEEPSLATE_RUTILE_ORE, "deepslate_rutile_ore");

    public static final RegistryObject<Item> CHROMITE_ORE = blockItem(EOFBlocks.CHROMITE_ORE, "chromite_ore");
    public static final RegistryObject<Item> DEEPSLATE_CHROMITE_ORE = blockItem(EOFBlocks.DEEPSLATE_CHROMITE_ORE, "deepslate_chromite_ore");

    public static final RegistryObject<Item> GALENA_ORE = blockItem(EOFBlocks.GALENA_ORE, "galena_ore");
    public static final RegistryObject<Item> DEEPSLATE_GALENA_ORE = blockItem(EOFBlocks.DEEPSLATE_GALENA_ORE, "deepslate_galena_ore");

    public static final RegistryObject<Item> PYRITE_ORE = blockItem(EOFBlocks.PYRITE_ORE, "pyrite_ore");
    public static final RegistryObject<Item> DEEPSLATE_PYRITE_ORE = blockItem(EOFBlocks.DEEPSLATE_PYRITE_ORE, "deepslate_pyrite_ore");

    public static final RegistryObject<Item> RAW_SPHALERITE = item("raw_sphalerite");
    public static final RegistryObject<Item> RAW_CASSITERITE = item("raw_cassiterite");
    public static final RegistryObject<Item> RAW_ACANTHITE = item("raw_acanthite");
    public static final RegistryObject<Item> RAW_WOLFRAMITE = item("raw_wolframite");
    public static final RegistryObject<Item> RAW_RUTILE = item("raw_rutile");
    public static final RegistryObject<Item> RAW_CHROMITE = item("raw_chromite");
    public static final RegistryObject<Item> RAW_GALENA = item("raw_galena");
    public static final RegistryObject<Item> RAW_PYRITE = item("raw_pyrite");

    public static final RegistryObject<Item> ZINC_INGOT = item("zinc_ingot");
    public static final RegistryObject<Item> TIN_INGOT = item("tin_ingot");
    public static final RegistryObject<Item> SILVER_INGOT = item("silver_ingot");
    public static final RegistryObject<Item> TUNGSTEN_INGOT = item("tungsten_ingot");
    public static final RegistryObject<Item> TITANIUM_INGOT = item("titanium_ingot");
    public static final RegistryObject<Item> CHROMIUM_INGOT = item("chromium_ingot");
    public static final RegistryObject<Item> LEAD_INGOT = item("lead_ingot");
    public static final RegistryObject<Item> BRASS_INGOT = item("brass_ingot");
    public static final RegistryObject<Item> BRONZE_INGOT = item("bronze_ingot");

    private static RegistryObject<Item> blockItem(RegistryObject<Block> block, String name) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> item(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }
}
