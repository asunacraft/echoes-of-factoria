package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EOFCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EchoesOfFactoria.MODID);

    public static final RegistryObject<CreativeModeTab> EOF_TAB = CREATIVE_TABS.register("eof", () ->
        CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.eof"))
            .icon(() -> new ItemStack(EOFBlocks.ELECTRIC_FURNACE.get()))
            .displayItems((parameters, output) -> {
                output.accept(EOFBlocks.ELECTRIC_FURNACE.get());

                output.accept(EOFBlocks.SPHALERITE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get());
                output.accept(EOFBlocks.CASSITERITE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_CASSITERITE_ORE.get());
                output.accept(EOFBlocks.ACANTHITE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_ACANTHITE_ORE.get());
                output.accept(EOFBlocks.WOLFRAMITE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE.get());
                output.accept(EOFBlocks.RUTILE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_RUTILE_ORE.get());
                output.accept(EOFBlocks.CHROMITE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_CHROMITE_ORE.get());
                output.accept(EOFBlocks.GALENA_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_GALENA_ORE.get());
                output.accept(EOFBlocks.PYRITE_ORE.get());
                output.accept(EOFBlocks.DEEPSLATE_PYRITE_ORE.get());

                output.accept(EOFItems.RAW_SPHALERITE.get());
                output.accept(EOFItems.RAW_CASSITERITE.get());
                output.accept(EOFItems.RAW_ACANTHITE.get());
                output.accept(EOFItems.RAW_WOLFRAMITE.get());
                output.accept(EOFItems.RAW_RUTILE.get());
                output.accept(EOFItems.RAW_CHROMITE.get());
                output.accept(EOFItems.RAW_GALENA.get());
                output.accept(EOFItems.RAW_PYRITE.get());

                output.accept(EOFItems.ZINC_INGOT.get());
                output.accept(EOFItems.TIN_INGOT.get());
                output.accept(EOFItems.SILVER_INGOT.get());
                output.accept(EOFItems.TUNGSTEN_INGOT.get());
                output.accept(EOFItems.TITANIUM_INGOT.get());
                output.accept(EOFItems.CHROMIUM_INGOT.get());
                output.accept(EOFItems.LEAD_INGOT.get());
                output.accept(EOFItems.BRASS_INGOT.get());
                output.accept(EOFItems.BRONZE_INGOT.get());
            })
            .build()
    );
}
