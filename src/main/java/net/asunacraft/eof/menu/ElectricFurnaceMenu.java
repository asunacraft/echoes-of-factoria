package net.asunacraft.eof.menu;

import net.asunacraft.eof.block.entity.ElectricFurnaceBlockEntity;
import net.asunacraft.eof.registry.EOFMenuTypes;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class ElectricFurnaceMenu extends AbstractElectricMachineMenu {
    public ElectricFurnaceMenu(int id, Inventory inv) {
        super(EOFMenuTypes.ELECTRIC_FURNACE.get(), id, inv);
    }

    public ElectricFurnaceMenu(int id, Inventory inv, ElectricFurnaceBlockEntity be) {
        super(EOFMenuTypes.ELECTRIC_FURNACE.get(), id, inv, be.getHandler(), be, be);
    }

    @Override
    protected boolean canProcess(Player player, ItemStack stack) {
        return player.level().getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, ElectricFurnaceBlockEntity.containerOf(stack), player.level())
                .isPresent();
    }
}
