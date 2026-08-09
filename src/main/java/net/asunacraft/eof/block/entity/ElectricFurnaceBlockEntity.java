package net.asunacraft.eof.block.entity;

import net.asunacraft.eof.block.ElectricFurnaceBlock;
import net.asunacraft.eof.energy.MachineEnergyStorage;
import net.asunacraft.eof.energy.VoltageTier;
import net.asunacraft.eof.menu.ElectricFurnaceMenu;
import net.asunacraft.eof.registry.EOFBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Tier-1 machine: smelts vanilla smelting recipes at 2x furnace speed.
 * LV, 1 amp (32 FE/t), 3200 FE per operation.
 */
public class ElectricFurnaceBlockEntity extends AbstractElectricMachineBlockEntity implements MenuProvider, ContainerData {
    private static final int SLOT_INPUT = 0;
    private static final int SLOT_OUTPUT = 1;
    private static final int TOTAL_ENERGY = 3200;

    private final ItemStackHandler handler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };
    private final LazyOptional<ItemStackHandler> itemCapability = LazyOptional.of(() -> handler);
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> new MachineEnergyStorage(this));

    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(EOFBlockEntities.ELECTRIC_FURNACE.get(), pos, state, VoltageTier.LV, 1, 4096);
    }

    public static SimpleContainer containerOf(ItemStack stack) {
        SimpleContainer container = new SimpleContainer(1);
        container.setItem(0, stack);
        return container;
    }

    private Optional<SmeltingRecipe> findRecipe(ItemStack input) {
        if (level == null || input.isEmpty()) {
            return Optional.empty();
        }
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, containerOf(input), level);
    }

    @Override
    protected boolean canCraft() {
        ItemStack input = handler.getStackInSlot(SLOT_INPUT);
        if (input.isEmpty()) {
            return false;
        }
        Optional<SmeltingRecipe> recipe = findRecipe(input);
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(level.registryAccess());
        ItemStack output = handler.getStackInSlot(SLOT_OUTPUT);
        if (output.isEmpty()) {
            return true;
        }
        if (!ItemStack.isSameItemSameTags(output, result)) {
            return false;
        }
        return output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    @Override
    protected void craft() {
        ItemStack input = handler.getStackInSlot(SLOT_INPUT);
        findRecipe(input).ifPresent(recipe -> {
            ItemStack result = recipe.getResultItem(level.registryAccess());
            handler.extractItem(SLOT_INPUT, 1, false);
            handler.insertItem(SLOT_OUTPUT, result.copy(), false);
        });
    }

    @Override
    protected int getTotalEnergyNeeded() {
        return TOTAL_ENERGY;
    }

    @Override
    protected void onTickServer() {
        boolean lit = canCraft() && energyBuffer.getEnergyStored() > 0;
        if (level.getBlockState(worldPosition).getValue(ElectricFurnaceBlock.LIT) != lit) {
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(ElectricFurnaceBlock.LIT, lit), 3);
        }
    }

    public ItemStackHandler getHandler() {
        return handler;
    }

    // --- capabilities ---

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemCapability.cast();
        }
        if (cap == ForgeCapabilities.ENERGY) {
            return energyCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemCapability.invalidate();
        energyCapability.invalidate();
    }

    // --- MenuProvider ---

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.eof.electric_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new ElectricFurnaceMenu(id, inv, this);
    }

    // --- ContainerData (0=energy, 1=capacity, 2=progress, 3=maxProgress, 4=tier) ---

    @Override
    public int get(int index) {
        return switch (index) {
            case 0 -> (int) getEnergyStored();
            case 1 -> (int) getEnergyCapacity();
            case 2 -> progress;
            case 3 -> getTotalEnergyNeeded();
            case 4 -> getRatedVoltage().ordinal();
            default -> 0;
        };
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void set(int index, int value) {
    }

    // --- persistence ---

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", handler.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        handler.deserializeNBT(tag.getCompound("Inventory"));
    }
}
