package net.asunacraft.eof.menu;

import net.asunacraft.eof.block.entity.ElectricCrusherBlockEntity;
import net.asunacraft.eof.registry.EOFMenuTypes;
import net.asunacraft.eof.registry.EOFRecipeTypes;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ElectricCrusherMenu extends AbstractElectricMachineMenu {
    public ElectricCrusherMenu(int id, Inventory inv) {
        super(EOFMenuTypes.ELECTRIC_CRUSHER.get(), id, inv);
    }

    public ElectricCrusherMenu(int id, Inventory inv, ElectricCrusherBlockEntity be) {
        super(EOFMenuTypes.ELECTRIC_CRUSHER.get(), id, inv, be.getHandler(), be, be);
    }

    @Override
    protected boolean canProcess(Player player, ItemStack stack) {
        return player.level().getRecipeManager()
                .getRecipeFor(EOFRecipeTypes.CRUSHING.get(), ElectricCrusherBlockEntity.containerOf(stack), player.level())
                .isPresent();
    }
}
