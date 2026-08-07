package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
}