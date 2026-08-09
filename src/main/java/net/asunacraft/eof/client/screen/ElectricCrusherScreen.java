package net.asunacraft.eof.client.screen;

import net.asunacraft.eof.menu.ElectricCrusherMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ElectricCrusherScreen extends AbstractElectricMachineScreen<ElectricCrusherMenu> {
    public ElectricCrusherScreen(ElectricCrusherMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }
}
