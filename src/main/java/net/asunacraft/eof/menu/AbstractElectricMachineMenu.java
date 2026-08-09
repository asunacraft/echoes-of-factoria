package net.asunacraft.eof.menu;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Shared UI skeleton for electric machines: an input/output slot pair, the
 * standard player inventory layout, energy/progress {@link ContainerData}
 * accessors and shift-clicking driven by {@link #canProcess}.
 */
public abstract class AbstractElectricMachineMenu extends AbstractContainerMenu {
    private static final int SLOT_INPUT = 0;
    private static final int SLOT_OUTPUT = 1;
    private static final int PLAYER_MAIN_START = 2;
    private static final int PLAYER_MAIN_END = 29;
    private static final int PLAYER_HOTBAR_START = 29;
    private static final int PLAYER_HOTBAR_END = 38;

    protected final ContainerData data;
    protected final BlockEntity blockEntity;

    protected AbstractElectricMachineMenu(MenuType<?> type, int id, Inventory inv,
            IItemHandler itemHandler, ContainerData data, BlockEntity blockEntity) {
        super(type, id);
        this.data = data;
        this.blockEntity = blockEntity;
        addDataSlots(data);

        addSlot(new SlotItemHandler(itemHandler, SLOT_INPUT, 56, 35));
        addSlot(new SlotItemHandler(itemHandler, SLOT_OUTPUT, 116, 35));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    /** Dummy handler/data variant used by the {@link MenuType} factory on the client. */
    protected AbstractElectricMachineMenu(MenuType<?> type, int id, Inventory inv) {
        this(type, id, inv, new ItemStackHandler(2), new SimpleContainerData(5), null);
    }

    /** Can the given stack be processed by this machine? Drives shift-clicking. */
    protected abstract boolean canProcess(Player player, ItemStack stack);

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == SLOT_OUTPUT) {
                if (!this.moveItemStackTo(stack, PLAYER_MAIN_START, PLAYER_HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else if (index == SLOT_INPUT) {
                if (!this.moveItemStackTo(stack, PLAYER_MAIN_START, PLAYER_HOTBAR_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (canProcess(player, stack)) {
                if (!this.moveItemStackTo(stack, SLOT_INPUT, SLOT_OUTPUT, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= PLAYER_MAIN_START && index < PLAYER_MAIN_END) {
                if (!this.moveItemStackTo(stack, PLAYER_HOTBAR_START, PLAYER_HOTBAR_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= PLAYER_HOTBAR_START && index < PLAYER_HOTBAR_END
                    && !this.moveItemStackTo(stack, PLAYER_MAIN_START, PLAYER_MAIN_END, false)) {
                return ItemStack.EMPTY;
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

    @Override
    public boolean stillValid(Player player) {
        if (blockEntity == null || blockEntity.getLevel() == null) {
            return true;
        }
        return player.distanceToSqr(blockEntity.getBlockPos().getX() + 0.5,
                blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) < 64.0;
    }

    // --- data accessors (written by the block entity's ContainerData) ---

    public int getEnergyStored() {
        return data.get(0);
    }

    public int getEnergyCapacity() {
        return data.get(1);
    }

    public int getProgress() {
        return data.get(2);
    }

    public int getMaxProgress() {
        return data.get(3);
    }

    public int getTierOrdinal() {
        return data.get(4);
    }
}
