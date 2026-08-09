package net.asunacraft.eof.client.screen;

import net.asunacraft.eof.menu.ElectricFurnaceMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ElectricFurnaceScreen extends AbstractElectricMachineScreen<ElectricFurnaceMenu> {
    public ElectricFurnaceScreen(ElectricFurnaceMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }
}
