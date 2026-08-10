package net.asunacraft.eof.block.entity;

import net.asunacraft.eof.block.SteamBoilerBlock;
import net.asunacraft.eof.energy.IElectricHost;
import net.asunacraft.eof.energy.PacketEnergyBuffer;
import net.asunacraft.eof.energy.VoltageTier;
import net.asunacraft.eof.menu.SteamBoilerMenu;
import net.asunacraft.eof.registry.EOFBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;

import org.jetbrains.annotations.Nullable;

/**
 * Tier 1 machine: generates ULV power from fuel and water.
 * 1 amp, 4096 FE capacity. (work in progress lool)
 */
public class SteamBoilerBlockEntity extends BlockEntity implements IElectricHost, MenuProvider, ContainerData {
    private static final int SLOT_FUEL = 0;
    private static final int ENERGY_CAPACITY = 4096;

    private final PacketEnergyBuffer energyBuffer;
    private int burnTime = 0;
    private int burnDuration = 0;

    private final ItemStackHandler handler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> itemCapability = LazyOptional.of(() -> handler);
    
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> new IEnergyStorage() {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int packets = maxExtract / VoltageTier.ULV.getVoltage();
            if (packets <= 0) return 0;
            long drained = energyBuffer.drain(VoltageTier.ULV, packets, simulate);
            if (drained > 0 && !simulate) setChanged();
            return (int) Math.min(Integer.MAX_VALUE, drained);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) { return 0; }

        @Override
        public int getEnergyStored() {
            return (int) Math.min(Integer.MAX_VALUE, energyBuffer.getEnergyStored());
        }

        @Override
        public int getMaxEnergyStored() {
            return (int) Math.min(Integer.MAX_VALUE, energyBuffer.getEnergyCapacity());
        }

        @Override
        public boolean canExtract() { return true; }

        @Override
        public boolean canReceive() { return false; }
    });

    public SteamBoilerBlockEntity(BlockPos pos, BlockState state) {
        super(EOFBlockEntities.STEAM_BOILER.get(), pos, state);
        this.energyBuffer = new PacketEnergyBuffer(VoltageTier.ULV, 1, ENERGY_CAPACITY);
    }

    public static boolean isFuel(ItemStack stack) {
        return getBurnTime(stack) > 0;
    }

    public static int getBurnTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        ItemStack copy = stack.copy();
        copy.setCount(1);
        Item item = copy.getItem();
        if (item == Items.COAL) return 1600;
        if (item == Items.CHARCOAL) return 1600;
        if (item == Items.COAL_BLOCK) return 16000;
        if (item == Items.BLAZE_ROD) return 2400;
        if (item == Items.LAVA_BUCKET) return 20000;
        return 0;
    }

    private boolean hasWaterNearby() {
        if (level == null) return false;
        for (Direction dir : Direction.values()) {
            if (level.getFluidState(worldPosition.relative(dir)).is(net.minecraft.tags.FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    private boolean canBurn() {
        if (burnTime > 0) return false;
        ItemStack fuel = handler.getStackInSlot(SLOT_FUEL);
        return !fuel.isEmpty() && getBurnTime(fuel) > 0 && hasWaterNearby();
    }

    public final void tickServer() {
        if (level == null || level.isClientSide) return;

        boolean wasBurning = burnTime > 0;

        if (burnTime > 0) {
            burnTime--;
            if (hasWaterNearby()) {
                energyBuffer.acceptPower(VoltageTier.ULV, 1, false);
            } else {
                burnTime = 0;
            }
        }

        if (burnTime == 0 && canBurn()) {
            ItemStack fuel = handler.getStackInSlot(SLOT_FUEL);
            burnDuration = getBurnTime(fuel);
            burnTime = burnDuration;
            fuel.shrink(1);
        }

        boolean lit = burnTime > 0 && hasWaterNearby();
        if (level.getBlockState(worldPosition).getValue(SteamBoilerBlock.LIT) != lit) {
            level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(SteamBoilerBlock.LIT, lit), 3);
        }

        if ((burnTime > 0) != wasBurning) {
            setChanged();
        }
    }

    public ItemStackHandler getHandler() {
        return handler;
    }

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

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.eof.steam_boiler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new SteamBoilerMenu(id, inv, this);
    }

    @Override
    public int get(int index) {
        return switch (index) {
            case 0 -> (int) getEnergyStored();
            case 1 -> (int) getEnergyCapacity();
            case 2 -> burnTime;
            case 3 -> burnDuration;
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

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", handler.serializeNBT());
        tag.putInt("BurnTime", burnTime);
        tag.putInt("BurnDuration", burnDuration);
        tag.putLong("Energy", energyBuffer.getEnergyStored());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        handler.deserializeNBT(tag.getCompound("Inventory"));
        burnTime = tag.getInt("BurnTime");
        burnDuration = tag.getInt("BurnDuration");
        energyBuffer.setEnergy(tag.getLong("Energy"));
    }

    public long addDebugEnergy(long fe) {
        long before = energyBuffer.getEnergyStored();
        energyBuffer.setEnergy(before + fe);
        setChanged();
        return energyBuffer.getEnergyStored() - before;
    }

    public long fillDebugEnergy() {
        long before = energyBuffer.getEnergyStored();
        energyBuffer.setEnergy(energyBuffer.getEnergyCapacity());
        setChanged();
        return energyBuffer.getEnergyStored() - before;
    }

    @Override
    public VoltageTier getRatedVoltage() {
        return energyBuffer.getRatedVoltage();
    }

    @Override
    public int getMaxAmperage() {
        return energyBuffer.getMaxAmperage();
    }

    @Override
    public int acceptPower(VoltageTier voltage, int amperage, boolean simulate) {
        return 0;
    }

    @Override
    public long getEnergyStored() {
        return energyBuffer.getEnergyStored();
    }

    @Override
    public long getEnergyCapacity() {
        return energyBuffer.getEnergyCapacity();
    }
}
