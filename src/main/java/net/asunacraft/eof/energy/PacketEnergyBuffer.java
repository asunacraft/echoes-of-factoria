package net.asunacraft.eof.energy;

/**
 * An energy store that accepts and drains power in whole packets of a fixed
 * rated tier. Packets above the rated tier are rejected (overvoltage) and the
 * number of packets accepted per tick is capped by {@link #maxAmperage}.
 */
public class PacketEnergyBuffer {
    private final VoltageTier ratedVoltage;
    private final int maxAmperage;
    private final long capacity;
    private long energy;

    public PacketEnergyBuffer(VoltageTier ratedVoltage, int maxAmperage, long capacity) {
        this.ratedVoltage = ratedVoltage;
        this.maxAmperage = maxAmperage;
        this.capacity = capacity;
    }

    /**
     * Accept up to {@code amperage} packets of {@code voltage} FE each.
     *
     * @return the number of packets accepted (in whole packets)
     */
    public int acceptPower(VoltageTier voltage, int amperage, boolean simulate) {
        if (!ratedVoltage.canAccept(voltage)) {
            return 0;
        }
        long free = capacity - energy;
        long packetsByCapacity = free / voltage.getVoltage();
        int accepted = (int) Math.min(amperage, Math.min(maxAmperage, packetsByCapacity));
        if (!simulate && accepted > 0) {
            energy += (long) accepted * voltage.getVoltage();
        }
        return accepted;
    }

    /** Drain up to {@code amperage} packets at this buffer's rated tier. */
    public long drain(VoltageTier voltage, int amperage, boolean simulate) {
        int cappedAmperage = Math.min(amperage, maxAmperage);
        long maxDrain = (long) voltage.getVoltage() * cappedAmperage;
        long drained = Math.min(energy, maxDrain);
        if (!simulate) {
            energy -= drained;
        }
        return drained;
    }

    public VoltageTier getRatedVoltage() {
        return ratedVoltage;
    }

    public int getMaxAmperage() {
        return maxAmperage;
    }

    public long getEnergyStored() {
        return energy;
    }

    public long getEnergyCapacity() {
        return capacity;
    }

    public void setEnergy(long energy) {
        this.energy = Math.max(0, Math.min(capacity, energy));
    }

    public float getFillPercent() {
        return capacity == 0 ? 0.0f : (float) energy / capacity;
    }
}
