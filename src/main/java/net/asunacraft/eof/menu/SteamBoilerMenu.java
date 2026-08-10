package net.asunacraft.eof.menu;

import net.asunacraft.eof.block.entity.SteamBoilerBlockEntity;
import net.asunacraft.eof.registry.EOFMenuTypes;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SteamBoilerMenu extends AbstractContainerMenu {
    private static final int SLOT_FUEL = 0;
    private static final int PLAYER_INV_START = 1;
    private static final int PLAYER_INV_END = 38;

    protected final ContainerData data;
    protected final BlockEntity blockEntity;
    
    public SteamBoilerMenu(int id, Inventory inv) {
        this(id, inv, new ItemStackHandler(1), new SimpleContainerData(5), null);
    }

    public SteamBoilerMenu(int id, Inventory inv, SteamBoilerBlockEntity be) {
        this(id, inv, be.getHandler(), be, (BlockEntity) be);
    }

    private SteamBoilerMenu(int id, Inventory inv, ItemStackHandler itemHandler, ContainerData data, BlockEntity blockEntity) {
        super(EOFMenuTypes.STEAM_BOILER.get(), id);
        this.data = data;
        this.blockEntity = blockEntity;
        addDataSlots(data);

        addSlot(new SlotItemHandler(itemHandler, SLOT_FUEL, 56, 35));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    public static boolean isFuel(ItemStack stack) {
        return SteamBoilerBlockEntity.isFuel(stack);
    }

    @Override
    public boolean stillValid(Player player) {
        if (blockEntity == null || blockEntity.getLevel() == null) {
            return true;
        }
        return player.distanceToSqr(blockEntity.getBlockPos().getX() + 0.5,
                blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) < 64.0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == SLOT_FUEL) {
                if (!this.moveItemStackTo(stack, PLAYER_INV_START, PLAYER_INV_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else if (index >= PLAYER_INV_START && index < PLAYER_INV_END) {
                if (isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, SLOT_FUEL, SLOT_FUEL + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stack);
            if (player instanceof ServerPlayer) {
                slot.onQuickCraft(stack, itemstack);
            }
        }
        return itemstack;
    }

    public int getEnergyStored() { return data.get(0); }
    public int getEnergyCapacity() { return data.get(1); }
    public int getBurnTime() { return data.get(2); }
    public int getBurnDuration() { return data.get(3); }
    public int getTierOrdinal() { return data.get(4); }
    public boolean isBurning() { return getBurnTime() > 0; }
}
