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

    public static final RegistryObject<Item> RAW_TIN_ORE = blockItem(EOFBlocks.RAW_TIN_ORE, "raw_tin_ore");
    public static final RegistryObject<Item> DEEPSLATE_RAW_TIN_ORE = blockItem(EOFBlocks.DEEPSLATE_RAW_TIN_ORE, "deepslate_raw_tin_ore");

    public static final RegistryObject<Item> RAW_SILVER_ORE = blockItem(EOFBlocks.RAW_SILVER_ORE, "raw_silver_ore");
    public static final RegistryObject<Item> DEEPSLATE_RAW_SILVER_ORE = blockItem(EOFBlocks.DEEPSLATE_RAW_SILVER_ORE, "deepslate_raw_silver_ore");

    public static final RegistryObject<Item> RAW_SPHALERITE = ITEMS.register(
        "raw_sphalerite",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register(
        "raw_tin",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register(
        "raw_silver",
        () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register(
        "zinc_ingot",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register(
        "tin_ingot",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register(
        "silver_ingot",
        () -> new Item(new Item.Properties())
    );

    private static RegistryObject<Item> blockItem(RegistryObject<Block> block, String name) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
