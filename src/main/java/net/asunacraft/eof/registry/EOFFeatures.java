package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.worldgen.VeinFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOFFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
        DeferredRegister.create(ForgeRegistries.FEATURES, EchoesOfFactoria.MODID);

    public static final RegistryObject<Feature<OreConfiguration>> VEIN = FEATURES.register(
        "vein",
        () -> new VeinFeature()
    );
}
