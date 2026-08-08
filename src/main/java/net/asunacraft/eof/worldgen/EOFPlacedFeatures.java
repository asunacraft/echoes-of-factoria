package net.asunacraft.eof.worldgen;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class EOFPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ORE_SPHALERITE = createKey("ore", "sphalerite_ore");
    public static final ResourceKey<PlacedFeature> ORE_CASSITERITE = createKey("ore", "cassiterite_ore");
    public static final ResourceKey<PlacedFeature> VEIN_ACANTHITE = createKey("vein", "vein_acanthite");
    public static final ResourceKey<PlacedFeature> ORE_WOLFRAMITE = createKey("ore", "wolframite_ore");
    public static final ResourceKey<PlacedFeature> ORE_RUTILE = createKey("ore", "rutile_ore");
    public static final ResourceKey<PlacedFeature> ORE_CHROMITE = createKey("ore", "chromite_ore");
    public static final ResourceKey<PlacedFeature> ORE_GALENA = createKey("ore", "galena_ore");
    public static final ResourceKey<PlacedFeature> ORE_PYRITE = createKey("ore", "pyrite_ore");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

        ore(context, ORE_SPHALERITE, configured.getOrThrow(EOFConfiguredFeatures.ORE_SPHALERITE), 4, 0, 48);
        ore(context, ORE_CASSITERITE, configured.getOrThrow(EOFConfiguredFeatures.ORE_CASSITERITE), 6, 40, 90);
        ore(context, VEIN_ACANTHITE, configured.getOrThrow(EOFConfiguredFeatures.VEIN_ACANTHITE), 2, 16, 48);
        ore(context, ORE_WOLFRAMITE, configured.getOrThrow(EOFConfiguredFeatures.ORE_WOLFRAMITE), 2, -48, 16);
        ore(context, ORE_RUTILE, configured.getOrThrow(EOFConfiguredFeatures.ORE_RUTILE), 3, -32, 32);
        ore(context, ORE_CHROMITE, configured.getOrThrow(EOFConfiguredFeatures.ORE_CHROMITE), 3, -48, 16);
        ore(context, ORE_GALENA, configured.getOrThrow(EOFConfiguredFeatures.ORE_GALENA), 4, 0, 40);
        ore(context, ORE_PYRITE, configured.getOrThrow(EOFConfiguredFeatures.ORE_PYRITE), 5, 0, 32);
    }

    private static void ore(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                            Holder<ConfiguredFeature<?, ?>> feature, int veinsPerChunk, int minY, int maxY) {
        List<PlacementModifier> placement = List.of(
                CountPlacement.of(veinsPerChunk),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)),
                BiomeFilter.biome());
        context.register(key, new PlacedFeature(feature, placement));
    }

    @SuppressWarnings("removal")
    private static ResourceKey<PlacedFeature> createKey(String category, String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EchoesOfFactoria.MODID, category + "/" + name));
    }
}
