package net.asunacraft.eof.client;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.client.screen.ElectricCrusherScreen;
import net.asunacraft.eof.client.screen.ElectricFurnaceScreen;
import net.asunacraft.eof.client.screen.SteamBoilerScreen;
import net.asunacraft.eof.item.VialItem;
import net.asunacraft.eof.registry.EOFItems;
import net.asunacraft.eof.registry.EOFMenuTypes;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.asunacraft.eof.registry.EOFFluids;

@Mod.EventBusSubscriber(modid = EchoesOfFactoria.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EOFClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(
                    EOFMenuTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
            MenuScreens.register(
                    EOFMenuTypes.ELECTRIC_CRUSHER.get(), ElectricCrusherScreen::new);
            MenuScreens.register(
                    EOFMenuTypes.STEAM_BOILER.get(), SteamBoilerScreen::new);

            ItemProperties.register(EOFItems.VIAL.get(), ResourceLocation.fromNamespaceAndPath(EchoesOfFactoria.MODID, "fluid"),
                    (stack, level, entity, seed) -> {
                        FluidStack fluid = VialItem.getFluid(stack);
                        if (fluid.isEmpty())
                            return 0f;
                        if (fluid.getFluid() == Fluids.WATER)
                            return 1f;
                        return 2f;
                    });

            for (var fluid : EOFFluids.FLUIDS.getEntries()) {
                ItemBlockRenderTypes.setRenderLayer(fluid.get(), RenderType.translucent());
            }
        });
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex != 1) return -1;
            FluidStack fluid = VialItem.getFluid(stack);
            if (fluid.isEmpty()) return -1;
            return IClientFluidTypeExtensions.of(fluid.getFluid().getFluidType()).getTintColor(fluid);
        }, EOFItems.VIAL.get());
    }
}
