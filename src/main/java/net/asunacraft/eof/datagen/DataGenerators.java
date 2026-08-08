package net.asunacraft.eof.datagen;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
    }
}
