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
        add(EOFBlocks.ELECTRIC_FURNACE.get(), "Electric Furnace");

        add(EOFBlocks.SPHALERITE_ORE.get(), "Sphalerite Ore");
        add(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get(), "Deepslate Sphalerite Ore");
        add(EOFBlocks.RAW_TIN_ORE.get(), "Raw Tin Ore");
        add(EOFBlocks.DEEPSLATE_RAW_TIN_ORE.get(), "Deepslate Raw Tin Ore");
        add(EOFBlocks.RAW_SILVER_ORE.get(), "Raw Silver Ore");
        add(EOFBlocks.DEEPSLATE_RAW_SILVER_ORE.get(), "Deepslate Raw Silver Ore");

        add(EOFItems.RAW_SPHALERITE.get(), "Raw Sphalerite");
        add(EOFItems.RAW_TIN.get(), "Raw Tin");
        add(EOFItems.RAW_SILVER.get(), "Raw Silver");

        add(EOFItems.ZINC_INGOT.get(), "Zinc Ingot");
        add(EOFItems.TIN_INGOT.get(), "Tin Ingot");
        add(EOFItems.SILVER_INGOT.get(), "Silver Ingot");
    }
}
