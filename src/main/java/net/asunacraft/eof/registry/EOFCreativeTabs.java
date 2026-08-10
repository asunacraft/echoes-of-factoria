package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.asunacraft.eof.item.VialItem;

public class EOFCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EchoesOfFactoria.MODID);

    public static final RegistryObject<CreativeModeTab> EOF_TAB = CREATIVE_TABS.register("eof", () ->
        CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.eof"))
            .icon(() -> new ItemStack(EOFBlocks.ELECTRIC_FURNACE.get()))
            .displayItems((parameters, output) -> {
                output.accept(EOFBlocks.ELECTRIC_FURNACE.get());
                output.accept(EOFBlocks.ELECTRIC_CRUSHER.get());
                output.accept(EOFBlocks.STEAM_BOILER.get());

                output.accept(EOFBlocks.CASING_ULV.get());
                output.accept(EOFBlocks.CASING_LV.get());
                output.accept(EOFBlocks.CASING_MV.get());
                output.accept(EOFBlocks.CASING_HV.get());
                output.accept(EOFBlocks.CASING_EV.get());
                output.accept(EOFBlocks.CASING_IV.get());

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

                output.accept(EOFItems.CRACKED_RAW_SPHALERITE.get());
                output.accept(EOFItems.CRACKED_RAW_CASSITERITE.get());
                output.accept(EOFItems.CRACKED_RAW_ACANTHITE.get());
                output.accept(EOFItems.CRACKED_RAW_WOLFRAMITE.get());
                output.accept(EOFItems.CRACKED_RAW_RUTILE.get());
                output.accept(EOFItems.CRACKED_RAW_CHROMITE.get());
                output.accept(EOFItems.CRACKED_RAW_GALENA.get());
                output.accept(EOFItems.CRACKED_RAW_PYRITE.get());

                output.accept(EOFItems.ZINC_DUST.get());
                output.accept(EOFItems.TIN_DUST.get());
                output.accept(EOFItems.SILVER_DUST.get());
                output.accept(EOFItems.TUNGSTEN_DUST.get());
                output.accept(EOFItems.TITANIUM_DUST.get());
                output.accept(EOFItems.CHROMIUM_DUST.get());
                output.accept(EOFItems.LEAD_DUST.get());
                output.accept(EOFItems.IRON_DUST.get());

                output.accept(EOFItems.ZINC_INGOT.get());
                output.accept(EOFItems.TIN_INGOT.get());
                output.accept(EOFItems.SILVER_INGOT.get());
                output.accept(EOFItems.TUNGSTEN_INGOT.get());
                output.accept(EOFItems.TITANIUM_INGOT.get());
                output.accept(EOFItems.CHROMIUM_INGOT.get());
                output.accept(EOFItems.LEAD_INGOT.get());
                output.accept(EOFItems.BRASS_INGOT.get());
                output.accept(EOFItems.BRONZE_INGOT.get());

                output.accept(EOFItems.VIAL.get());

                output.accept(filledVial(EOFFluids.BRINE.get()));
                output.accept(filledVial(EOFFluids.SODIUM_HYDROXIDE.get()));
                output.accept(filledVial(EOFFluids.SULFURIC_ACID.get()));
                output.accept(filledVial(EOFFluids.HYDROCHLORIC_ACID.get()));
            })
            .build()
    );

    private static ItemStack filledVial(net.minecraft.world.level.material.Fluid fluid) {
        ItemStack stack = new ItemStack(EOFItems.VIAL.get());
        VialItem.setFluid(stack, new FluidStack(fluid, VialItem.CAPACITY));
        return stack;
    }
}
