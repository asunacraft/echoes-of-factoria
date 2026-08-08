package net.asunacraft.eof.datagen;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.worldgen.EOFBiomeModifiers;
import net.asunacraft.eof.worldgen.EOFConfiguredFeatures;
import net.asunacraft.eof.worldgen.EOFPlacedFeatures;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new EOFBlockStateProvider(packOutput, EchoesOfFactoria.MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOFItemModelProvider(packOutput, EchoesOfFactoria.MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOFLanguageProvider(packOutput, EchoesOfFactoria.MODID, "en_us"));

        generator.addProvider(event.includeServer(), new EOFLootTableProvider(packOutput));
        generator.addProvider(event.includeServer(), new EOFTagProvider(packOutput, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(event.includeServer(), new EOFRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                packOutput,
                event.getLookupProvider(),
                new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, EOFConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, EOFPlacedFeatures::bootstrap)
                        .add(ForgeRegistries.Keys.BIOME_MODIFIERS, EOFBiomeModifiers::bootstrap),
                Set.of(EchoesOfFactoria.MODID)));
    }
}
