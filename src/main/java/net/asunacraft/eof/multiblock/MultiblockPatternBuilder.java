package net.asunacraft.eof.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MultiblockPatternBuilder {
    private final List<MultiblockPattern.PatternEntry> entries = new ArrayList<>();

    public MultiblockPatternBuilder require(int dx, int dy, int dz, Predicate<BlockState> matcher) {
        entries.add(new MultiblockPattern.PatternEntry(new BlockPos(dx, dy, dz), matcher));
        return this;
    }

    public MultiblockPatternBuilder requireBlock(int dx, int dy, int dz, Block block) {
        return require(dx, dy, dz, state -> state.is(block));
    }

    public MultiblockPattern build() {
        return new MultiblockPattern(List.copyOf(entries));
    }
}