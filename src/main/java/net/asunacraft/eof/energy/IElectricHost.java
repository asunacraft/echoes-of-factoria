package net.asunacraft.eof.energy;

/**
 * Anything that can receive energy packets in the tiered voltage/amperage model.
 *
 * <p>Power is transferred in discrete packets of a {@link VoltageTier}. The
 * caller supplies a voltage and an amperage (packet count) and receives back
 * the number of amperes actually accepted; the actual FE transferred is
 * {@code acceptedAmps * voltage.getVoltage()}.</p>
 */
public interface IElectricHost {
    /** Highest packet tier this host can safely accept. */
    VoltageTier getRatedVoltage();

    /** Maximum number of packets this host accepts per tick. */
    int getMaxAmperage();

    /**
     * Offer up to {@code amperage} packets at {@code voltage}.
     *
     * @return the number of amperes accepted (0 if overvoltage or full)
     */
    int acceptPower(VoltageTier voltage, int amperage, boolean simulate);

    long getEnergyStored();

    long getEnergyCapacity();
}
