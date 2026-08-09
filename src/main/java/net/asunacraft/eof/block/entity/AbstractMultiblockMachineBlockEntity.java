package net.asunacraft.eof.block.entity;

import net.asunacraft.eof.energy.VoltageTier;
import net.asunacraft.eof.multiblock.MultiblockPattern;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractMultiblockMachineBlockEntity extends AbstractElectricMachineBlockEntity {
    private static final int FORMATION_CHECK_INTERVAL = 20; // recheck once a second, not every tick

    private boolean formed = false;
    private int ticksSinceCheck = 0;

    public AbstractMultiblockMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
            VoltageTier tier, int maxAmperage, long capacity) {
        super(type, pos, state, tier, maxAmperage, capacity);
    }

    /** The pattern this multiblock must match to be considered formed. */
    protected abstract MultiblockPattern getPattern();

    /** Called once when the structure transitions unformed -> formed. */
    protected void onFormed() {
    }

    /**
     * Called once when the structure transitions formed -> unformed (e.g. a block
     * was broken).
     */
    protected void onUnformed() {
    }

    public boolean isFormed() {
        return formed;
    }

    @Override
    protected void onTickServer() {
        if (++ticksSinceCheck >= FORMATION_CHECK_INTERVAL) {
            ticksSinceCheck = 0;
            revalidateFormation();
        }
    }

    /** Force a re-check now, e.g. from a neighbor-changed callback. */
    public void revalidateFormation() {
        if (level == null) {
            return;
        }
        boolean nowFormed = getPattern().matches(level, worldPosition);
        if (nowFormed && !formed) {
            formed = true;
            onFormed();
            setChanged();
        } else if (!nowFormed && formed) {
            formed = false;
            onUnformed();
            setChanged();
        }
    }

    /**
     * Gate crafting behind formation, subclasses' canCraft() logic runs only when
     * formed.
     */
    @Override
    protected final boolean canCraft() {
        return formed && canCraftWhenFormed();
    }

    protected abstract boolean canCraftWhenFormed();

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("Formed", formed);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        formed = tag.getBoolean("Formed");
    }
}