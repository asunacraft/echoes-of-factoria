package net.asunacraft.eof.energy;

import net.minecraftforge.energy.IEnergyStorage;

/**
 * Vanilla-FE compatibility shim: converts raw FE into packets at the host's
 * rated voltage, so standard {@link IEnergyStorage} sources can feed machines.
 */
public class MachineEnergyStorage implements IEnergyStorage {
    private final IElectricHost host;

    public MachineEnergyStorage(IElectricHost host) {
        this.host = host;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }
        int voltage = host.getRatedVoltage().getVoltage();
        int amperage = maxReceive / voltage;
        if (amperage <= 0) {
            return 0;
        }
        return host.acceptPower(host.getRatedVoltage(), amperage, simulate) * voltage;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return (int) Math.min(Integer.MAX_VALUE, host.getEnergyStored());
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) Math.min(Integer.MAX_VALUE, host.getEnergyCapacity());
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
