package net.asunacraft.eof.block.entity;

import net.asunacraft.eof.energy.IElectricHost;
import net.asunacraft.eof.energy.PacketEnergyBuffer;
import net.asunacraft.eof.energy.TemperatureTier;
import net.asunacraft.eof.energy.VoltageTier;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Base for machines driven by the packet energy model.
 *
 * <p>Subclasses define what work a machine does via {@link #canCraft()},
 * {@link #craft()} and {@link #getTotalEnergyNeeded()}. Each server tick the
 * machine drains {@code voltage * drawAmps} FE from its buffer (in whole
 * packets) and advances {@link #progress} by that amount; when progress
 * reaches the total energy cost the recipe is completed.</p>
 */
public abstract class AbstractElectricMachineBlockEntity extends BlockEntity implements IElectricHost {
    protected final PacketEnergyBuffer energyBuffer;
    protected int progress;

    /** Current temperature in Kelvin; heats toward {@link #targetHeat} while working. */
    protected int heat = TemperatureTier.AMBIENT_KELVIN;
    protected int targetHeat = TemperatureTier.AMBIENT_KELVIN;

    public AbstractElectricMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
            VoltageTier tier, int maxAmperage, long capacity) {
        super(type, pos, state);
        this.energyBuffer = new PacketEnergyBuffer(tier, maxAmperage, capacity);
    }

    public final void tickServer() {
        if (level == null || level.isClientSide) {
            return;
        }
        updateHeat();
        if (canCraft()) {
            long drained = energyBuffer.drain(getRatedVoltage(), getDrawAmperage(), false);
            if (drained > 0) {
                progress += (int) drained;
                if (progress >= getTotalEnergyNeeded()) {
                    progress -= getTotalEnergyNeeded();
                    craft();
                }
                setChanged();
            }
        } else if (progress > 0) {
            progress = Math.max(0, progress - getRatedVoltage().getVoltage() * getDrawAmperage());
            setChanged();
        }
        onTickServer();
    }

    /** Number of packets this machine draws per tick while working. */
    protected int getDrawAmperage() {
        return energyBuffer.getMaxAmperage();
    }

    // --- heat ---

    /** Approach {@link #targetHeat} at a rate that scales with the machine's tier. */
    protected void updateHeat() {
        int rate = getHeatPerTick();
        if (heat < targetHeat) {
            heat = Math.min(targetHeat, heat + rate);
        } else if (heat > targetHeat) {
            heat = Math.max(targetHeat, heat - Math.max(1, rate / 2));
        }
    }

    /** Kelvin gained per tick while heating up. */
    protected int getHeatPerTick() {
        return Math.max(1, getRatedVoltage().getVoltage() / 8);
    }

    public int getHeatKelvin() {
        return heat;
    }

    public int getHeatCelsius() {
        return TemperatureTier.toCelsius(heat);
    }

    public int getTargetHeatKelvin() {
        return targetHeat;
    }

    public void setTargetHeatKelvin(int kelvin) {
        this.targetHeat = Math.max(TemperatureTier.AMBIENT_KELVIN, kelvin);
    }

    /** Is the machine hot enough for a process requiring this temperature? */
    public boolean isHotEnough(int requiredKelvin) {
        return heat >= requiredKelvin;
    }

    public boolean isHotEnough(TemperatureTier tier) {
        return heat >= tier.getMinKelvin();
    }

    /** Hook for subclasses to run additional server-side logic each tick. */
    protected void onTickServer() {
    }

    /** Directly add FE to the buffer, bypassing the packet model. For dev commands. */
    public long addDebugEnergy(long fe) {
        long before = energyBuffer.getEnergyStored();
        energyBuffer.setEnergy(before + fe);
        setChanged();
        return energyBuffer.getEnergyStored() - before;
    }

    /** Is there a valid, completable job right now? */
    protected abstract boolean canCraft();

    /** Consume inputs and produce outputs. */
    protected abstract void craft();

    /** Total FE required to complete one job. */
    protected abstract int getTotalEnergyNeeded();

    // IElectricHost

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
        if (!getRatedVoltage().canAccept(voltage)) {
            if (!simulate) {
                onOvervoltage(voltage);
            }
            return 0;
        }
        int accepted = energyBuffer.acceptPower(voltage, amperage, simulate);
        if (accepted > 0 && !simulate) {
            setChanged();
        }
        return accepted;
    }

    @Override
    public long getEnergyStored() {
        return energyBuffer.getEnergyStored();
    }

    @Override
    public long getEnergyCapacity() {
        return energyBuffer.getEnergyCapacity();
    }

    /** What happens when power arrives above this machine's rated tier. */
    protected void onOvervoltage(VoltageTier attempted) {
        if (level != null && !level.isClientSide) {
            level.explode(null, worldPosition.getX() + 0.5, worldPosition.getY() + 0.5,
                    worldPosition.getZ() + 0.5, 2.0f, false, Level.ExplosionInteraction.BLOCK);
        }
    }

    // --- persistence ---

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("Energy", energyBuffer.getEnergyStored());
        tag.putInt("Progress", progress);
        tag.putInt("Heat", heat);
        tag.putInt("TargetHeat", targetHeat);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        energyBuffer.setEnergy(tag.getLong("Energy"));
        progress = tag.getInt("Progress");
        heat = tag.getInt("Heat");
        targetHeat = tag.getInt("TargetHeat");
    }
}
