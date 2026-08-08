package net.asunacraft.eof.worldgen;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFFeatures;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class EOFConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPHALERITE = createKey("ore", "sphalerite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CASSITERITE = createKey("ore", "cassiterite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VEIN_ACANTHITE = createKey("vein", "vein_acanthite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_WOLFRAMITE = createKey("ore", "wolframite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUTILE = createKey("ore", "rutile_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CHROMITE = createKey("ore", "chromite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GALENA = createKey("ore", "galena_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYRITE = createKey("ore", "pyrite_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        ore(context, ORE_SPHALERITE, 7, stone, deepslate, EOFBlocks.SPHALERITE_ORE.get(), EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get());
        ore(context, ORE_CASSITERITE, 9, stone, deepslate, EOFBlocks.CASSITERITE_ORE.get(), EOFBlocks.DEEPSLATE_CASSITERITE_ORE.get());
        vein(context, VEIN_ACANTHITE, 8, stone, deepslate, EOFBlocks.ACANTHITE_ORE.get(), EOFBlocks.DEEPSLATE_ACANTHITE_ORE.get());
        ore(context, ORE_WOLFRAMITE, 7, stone, deepslate, EOFBlocks.WOLFRAMITE_ORE.get(), EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE.get());
        ore(context, ORE_RUTILE, 7, stone, deepslate, EOFBlocks.RUTILE_ORE.get(), EOFBlocks.DEEPSLATE_RUTILE_ORE.get());
        ore(context, ORE_CHROMITE, 7, stone, deepslate, EOFBlocks.CHROMITE_ORE.get(), EOFBlocks.DEEPSLATE_CHROMITE_ORE.get());
        ore(context, ORE_GALENA, 8, stone, deepslate, EOFBlocks.GALENA_ORE.get(), EOFBlocks.DEEPSLATE_GALENA_ORE.get());
        ore(context, ORE_PYRITE, 8, stone, deepslate, EOFBlocks.PYRITE_ORE.get(), EOFBlocks.DEEPSLATE_PYRITE_ORE.get());
    }

    private static void vein(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key,
                             int veinSize, RuleTest stone, RuleTest deepslate, Block base, Block deepslateVariant) {
        List<OreConfiguration.TargetBlockState> targets = List.of(
                OreConfiguration.target(stone, base.defaultBlockState()),
                OreConfiguration.target(deepslate, deepslateVariant.defaultBlockState()));
        context.register(key, new ConfiguredFeature<>(EOFFeatures.VEIN.get(), new OreConfiguration(targets, veinSize)));
    }

    private static void ore(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key,
                            int veinSize, RuleTest stone, RuleTest deepslate, Block base, Block deepslateVariant) {
        List<OreConfiguration.TargetBlockState> targets = List.of(
                OreConfiguration.target(stone, base.defaultBlockState()),
                OreConfiguration.target(deepslate, deepslateVariant.defaultBlockState()));
        context.register(key, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets, veinSize)));
    }

    @SuppressWarnings("removal")
    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String category, String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EchoesOfFactoria.MODID, category + "/" + name));
    }
}
