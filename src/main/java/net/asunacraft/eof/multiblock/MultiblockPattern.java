package net.asunacraft.eof.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Predicate;

public class MultiblockPattern {
    private final List<PatternEntry> entries;

    public MultiblockPattern(List<PatternEntry> entries) {
        this.entries = entries;
    }

    public record PatternEntry(BlockPos offset, Predicate<BlockState> matcher) {
    }

    /**
     * Checks every offset against the world; returns the first failing offset, or
     * null if formed.
     */
    public BlockPos findMismatch(Level level, BlockPos controllerPos) {
        for (PatternEntry entry : entries) {
            BlockPos target = controllerPos.offset(entry.offset());
            BlockState state = level.getBlockState(target);
            if (!entry.matcher().test(state)) {
                return target;
            }
        }
        return null;
    }

    public boolean matches(Level level, BlockPos controllerPos) {
        return findMismatch(level, controllerPos) == null;
    }

    public List<BlockPos> getAllOffsets(BlockPos controllerPos) {
        return entries.stream().map(e -> controllerPos.offset(e.offset())).toList();
    }
}
