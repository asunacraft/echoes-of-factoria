package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.menu.ElectricCrusherMenu;
import net.asunacraft.eof.menu.ElectricFurnaceMenu;
import net.asunacraft.eof.menu.SteamBoilerMenu;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOFMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, EchoesOfFactoria.MODID);

    public static final RegistryObject<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE =
            MENUS.register("electric_furnace",
                    () -> new MenuType<>(ElectricFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final RegistryObject<MenuType<ElectricCrusherMenu>> ELECTRIC_CRUSHER =
            MENUS.register("electric_crusher",
                    () -> new MenuType<>(ElectricCrusherMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final RegistryObject<MenuType<SteamBoilerMenu>> STEAM_BOILER =
            MENUS.register("steam_boiler",
                    () -> new MenuType<>(SteamBoilerMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
