package net.asunacraft.eof.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class VeinFeature extends Feature<OreConfiguration> {
    public VeinFeature() {
        super(OreConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        OreConfiguration config = context.config();

        int minX = (int) SectionPos.blockToSection(context.origin().getX()) << 4;
        int minZ = (int) SectionPos.blockToSection(context.origin().getZ()) << 4;
        int maxX = minX + 15;
        int maxZ = minZ + 15;

        BlockPos.MutableBlockPos pos = context.origin().mutable();

        Direction drift = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int dip = random.nextInt(3) - 1;
        int target = config.size;
        int placed = 0;

        for (int step = 0; step < target * 8 && placed < target; step++) {
            if (tryPlace(level, random, config, pos)) {
                placed++;
            }

            if (random.nextFloat() < 0.4F && placed < target) {
                Direction sideDir = drift.getClockWise();
                if (withinBounds(pos, sideDir, minX, maxX, minZ, maxZ)) {
                    BlockPos.MutableBlockPos side = pos.mutable().move(sideDir);
                    if (tryPlace(level, random, config, side)) {
                        placed++;
                    }
                }
            }

            if (random.nextFloat() < 0.3F && placed < target) {
                BlockPos.MutableBlockPos branch = pos.mutable();
                Direction branchDir = random.nextBoolean() ? drift.getClockWise() : drift.getCounterClockWise();
                for (int b = 0; b < random.nextInt(3) + 2 && placed < target; b++) {
                    branchDir = moveInBounds(branch, branchDir, minX, maxX, minZ, maxZ);
                    if (random.nextFloat() < 0.6F) {
                        branch.move(0, dip, 0);
                    }
                    if (tryPlace(level, random, config, branch)) {
                        placed++;
                    }
                }
            }

            drift = moveInBounds(pos, drift, minX, maxX, minZ, maxZ);
            if (random.nextFloat() < 0.5F) {
                pos.move(0, dip, 0);
            }
        }

        return placed > 0;
    }

    private static Direction moveInBounds(BlockPos.MutableBlockPos pos, Direction dir, int minX, int maxX, int minZ, int maxZ) {
        if (dir.getAxis() == Direction.Axis.X) {
            int next = pos.getX() + dir.getStepX();
            if (next < minX || next > maxX) {
                dir = dir.getOpposite();
            }
        } else {
            int next = pos.getZ() + dir.getStepZ();
            if (next < minZ || next > maxZ) {
                dir = dir.getOpposite();
            }
        }
        pos.move(dir);
        return dir;
    }

    private static boolean withinBounds(BlockPos pos, Direction dir, int minX, int maxX, int minZ, int maxZ) {
        int x = pos.getX() + dir.getStepX();
        int z = pos.getZ() + dir.getStepZ();
        return x >= minX && x <= maxX && z >= minZ && z <= maxZ;
    }

    private static boolean tryPlace(WorldGenLevel level, RandomSource random, OreConfiguration config, BlockPos pos) {
        if (!level.ensureCanWrite(pos)) {
            return false;
        }
        for (OreConfiguration.TargetBlockState target : config.targetStates) {
            if (OreFeature.canPlaceOre(level.getBlockState(pos), level::getBlockState, random, config, target, pos.mutable())) {
                level.setBlock(pos, target.state, 2);
                return true;
            }
        }
        return false;
    }
}
