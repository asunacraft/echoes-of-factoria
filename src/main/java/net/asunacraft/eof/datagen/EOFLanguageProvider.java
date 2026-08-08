package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EOFLanguageProvider extends LanguageProvider {
    public EOFLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.eof", "Echoes of Factoria");

        add(EOFBlocks.ELECTRIC_FURNACE.get(), "Electric Furnace");

        add(EOFBlocks.SPHALERITE_ORE.get(), "Sphalerite Ore");
        add(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get(), "Deepslate Sphalerite Ore");
        add(EOFBlocks.CASSITERITE_ORE.get(), "Cassiterite Ore");
        add(EOFBlocks.DEEPSLATE_CASSITERITE_ORE.get(), "Deepslate Cassiterite Ore");
        add(EOFBlocks.ACANTHITE_ORE.get(), "Acanthite Ore");
        add(EOFBlocks.DEEPSLATE_ACANTHITE_ORE.get(), "Deepslate Acanthite Ore");
        add(EOFBlocks.WOLFRAMITE_ORE.get(), "Wolframite Ore");
        add(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE.get(), "Deepslate Wolframite Ore");
        add(EOFBlocks.RUTILE_ORE.get(), "Rutile Ore");
        add(EOFBlocks.DEEPSLATE_RUTILE_ORE.get(), "Deepslate Rutile Ore");
        add(EOFBlocks.CHROMITE_ORE.get(), "Chromite Ore");
        add(EOFBlocks.DEEPSLATE_CHROMITE_ORE.get(), "Deepslate Chromite Ore");
        add(EOFBlocks.GALENA_ORE.get(), "Galena Ore");
        add(EOFBlocks.DEEPSLATE_GALENA_ORE.get(), "Deepslate Galena Ore");
        add(EOFBlocks.PYRITE_ORE.get(), "Pyrite Ore");
        add(EOFBlocks.DEEPSLATE_PYRITE_ORE.get(), "Deepslate Pyrite Ore");

        add(EOFItems.RAW_SPHALERITE.get(), "Raw Sphalerite");
        add(EOFItems.RAW_CASSITERITE.get(), "Raw Cassiterite");
        add(EOFItems.RAW_ACANTHITE.get(), "Raw Acanthite");
        add(EOFItems.RAW_WOLFRAMITE.get(), "Raw Wolframite");
        add(EOFItems.RAW_RUTILE.get(), "Raw Rutile");
        add(EOFItems.RAW_CHROMITE.get(), "Raw Chromite");
        add(EOFItems.RAW_GALENA.get(), "Raw Galena");
        add(EOFItems.RAW_PYRITE.get(), "Raw Pyrite");

        add(EOFItems.ZINC_INGOT.get(), "Zinc Ingot");
        add(EOFItems.TIN_INGOT.get(), "Tin Ingot");
        add(EOFItems.SILVER_INGOT.get(), "Silver Ingot");
        add(EOFItems.TUNGSTEN_INGOT.get(), "Tungsten Ingot");
        add(EOFItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(EOFItems.CHROMIUM_INGOT.get(), "Chromium Ingot");
        add(EOFItems.LEAD_INGOT.get(), "Lead Ingot");
        add(EOFItems.BRASS_INGOT.get(), "Brass Ingot");
        add(EOFItems.BRONZE_INGOT.get(), "Bronze Ingot");
    }
}
