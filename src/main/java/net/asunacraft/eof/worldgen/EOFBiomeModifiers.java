package net.asunacraft.eof.worldgen;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class EOFBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ORE_SPHALERITE = createKey("ore", "add_ore_sphalerite");
    public static final ResourceKey<BiomeModifier> ADD_ORE_CASSITERITE = createKey("ore", "add_ore_cassiterite");
    public static final ResourceKey<BiomeModifier> ADD_VEIN_ACANTHITE = createKey("vein", "add_vein_acanthite");
    public static final ResourceKey<BiomeModifier> ADD_ORE_WOLFRAMITE = createKey("ore", "add_ore_wolframite");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RUTILE = createKey("ore", "add_ore_rutile");
    public static final ResourceKey<BiomeModifier> ADD_ORE_CHROMITE = createKey("ore", "add_ore_chromite");
    public static final ResourceKey<BiomeModifier> ADD_ORE_GALENA = createKey("ore", "add_ore_galena");
    public static final ResourceKey<BiomeModifier> ADD_ORE_PYRITE = createKey("ore", "add_ore_pyrite");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        addOre(context, ADD_ORE_SPHALERITE, biomes, placedFeatures, EOFPlacedFeatures.ORE_SPHALERITE);
        addOre(context, ADD_ORE_CASSITERITE, biomes, placedFeatures, EOFPlacedFeatures.ORE_CASSITERITE);
        addOre(context, ADD_VEIN_ACANTHITE, biomes, placedFeatures, EOFPlacedFeatures.VEIN_ACANTHITE);
        addOre(context, ADD_ORE_WOLFRAMITE, biomes, placedFeatures, EOFPlacedFeatures.ORE_WOLFRAMITE);
        addOre(context, ADD_ORE_RUTILE, biomes, placedFeatures, EOFPlacedFeatures.ORE_RUTILE);
        addOre(context, ADD_ORE_CHROMITE, biomes, placedFeatures, EOFPlacedFeatures.ORE_CHROMITE);
        addOre(context, ADD_ORE_GALENA, biomes, placedFeatures, EOFPlacedFeatures.ORE_GALENA);
        addOre(context, ADD_ORE_PYRITE, biomes, placedFeatures, EOFPlacedFeatures.ORE_PYRITE);
    }

    private static void addOre(BootstapContext<BiomeModifier> context, ResourceKey<BiomeModifier> key,
                               HolderGetter<Biome> biomes, HolderGetter<PlacedFeature> placedFeatures,
                               ResourceKey<PlacedFeature> feature) {
        context.register(key, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(feature)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    @SuppressWarnings("removal")
    private static ResourceKey<BiomeModifier> createKey(String category, String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(EchoesOfFactoria.MODID, category + "/" + name));
    }
}
