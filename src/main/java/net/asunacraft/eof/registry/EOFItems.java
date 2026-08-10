package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;

import net.asunacraft.eof.item.VialItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EOFItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, EchoesOfFactoria.MODID);

    public static final RegistryObject<Item> ELECTRIC_FURNACE = ITEMS.register(
        "electric_furnace",
        () -> new BlockItem(EOFBlocks.ELECTRIC_FURNACE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> ELECTRIC_CRUSHER = ITEMS.register(
        "electric_crusher",
        () -> new BlockItem(EOFBlocks.ELECTRIC_CRUSHER.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> STEAM_BOILER = ITEMS.register(
        "steam_boiler",
        () -> new BlockItem(EOFBlocks.STEAM_BOILER.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> CASING_ULV = blockItem(EOFBlocks.CASING_ULV, "casing_ulv");
    public static final RegistryObject<Item> CASING_LV = blockItem(EOFBlocks.CASING_LV, "casing_lv");
    public static final RegistryObject<Item> CASING_MV = blockItem(EOFBlocks.CASING_MV, "casing_mv");
    public static final RegistryObject<Item> CASING_HV = blockItem(EOFBlocks.CASING_HV, "casing_hv");
    public static final RegistryObject<Item> CASING_EV = blockItem(EOFBlocks.CASING_EV, "casing_ev");
    public static final RegistryObject<Item> CASING_IV = blockItem(EOFBlocks.CASING_IV, "casing_iv");

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

    public static final RegistryObject<Item> CRACKED_RAW_SPHALERITE = item("cracked_raw_sphalerite");
    public static final RegistryObject<Item> CRACKED_RAW_CASSITERITE = item("cracked_raw_cassiterite");
    public static final RegistryObject<Item> CRACKED_RAW_ACANTHITE = item("cracked_raw_acanthite");
    public static final RegistryObject<Item> CRACKED_RAW_WOLFRAMITE = item("cracked_raw_wolframite");
    public static final RegistryObject<Item> CRACKED_RAW_RUTILE = item("cracked_raw_rutile");
    public static final RegistryObject<Item> CRACKED_RAW_CHROMITE = item("cracked_raw_chromite");
    public static final RegistryObject<Item> CRACKED_RAW_GALENA = item("cracked_raw_galena");
    public static final RegistryObject<Item> CRACKED_RAW_PYRITE = item("cracked_raw_pyrite");

    public static final RegistryObject<Item> ZINC_INGOT = item("zinc_ingot");
    public static final RegistryObject<Item> TIN_INGOT = item("tin_ingot");
    public static final RegistryObject<Item> SILVER_INGOT = item("silver_ingot");
    public static final RegistryObject<Item> TUNGSTEN_INGOT = item("tungsten_ingot");
    public static final RegistryObject<Item> TITANIUM_INGOT = item("titanium_ingot");
    public static final RegistryObject<Item> CHROMIUM_INGOT = item("chromium_ingot");
    public static final RegistryObject<Item> LEAD_INGOT = item("lead_ingot");
    public static final RegistryObject<Item> BRASS_INGOT = item("brass_ingot");
    public static final RegistryObject<Item> BRONZE_INGOT = item("bronze_ingot");

    public static final RegistryObject<Item> ZINC_DUST = item("zinc_dust");
    public static final RegistryObject<Item> TIN_DUST = item("tin_dust");
    public static final RegistryObject<Item> SILVER_DUST = item("silver_dust");
    public static final RegistryObject<Item> TUNGSTEN_DUST = item("tungsten_dust");
    public static final RegistryObject<Item> TITANIUM_DUST = item("titanium_dust");
    public static final RegistryObject<Item> CHROMIUM_DUST = item("chromium_dust");
    public static final RegistryObject<Item> LEAD_DUST = item("lead_dust");
    public static final RegistryObject<Item> IRON_DUST = item("iron_dust");

    public static final RegistryObject<Item> VIAL = item("vial", () -> new VialItem(new Item.Properties().stacksTo(16)));

    private static RegistryObject<Item> blockItem(RegistryObject<Block> block, String name) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> item(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> item(String name, Supplier<? extends Item> supplier) {
        return ITEMS.register(name, supplier);
    }
}
