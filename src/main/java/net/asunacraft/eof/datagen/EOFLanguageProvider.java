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
        add("container.eof.electric_furnace", "Electric Furnace");

        add(EOFBlocks.ELECTRIC_CRUSHER.get(), "Electric Crusher");
        add("container.eof.electric_crusher", "Electric Crusher");

        add(EOFBlocks.CASING_ULV.get(), "ULV Machine Casing");
        add(EOFBlocks.CASING_LV.get(), "LV Machine Casing");
        add(EOFBlocks.CASING_MV.get(), "MV Machine Casing");
        add(EOFBlocks.CASING_HV.get(), "HV Machine Casing");
        add(EOFBlocks.CASING_EV.get(), "EV Machine Casing");
        add(EOFBlocks.CASING_IV.get(), "IV Machine Casing");

        add("energy.eof.tier.ulv", "ULV (8 FE/pkt)");
        add("energy.eof.tier.lv", "LV (32 FE/pkt)");
        add("energy.eof.tier.mv", "MV (128 FE/pkt)");
        add("energy.eof.tier.hv", "HV (512 FE/pkt)");
        add("energy.eof.tier.ev", "EV (2048 FE/pkt)");
        add("energy.eof.tier.iv", "IV (8192 FE/pkt)");

        add("heat.eof.tier.ulv", "ULV Heat (40 - 200°C)");
        add("heat.eof.tier.lv", "LV Heat (200 - 600°C)");
        add("heat.eof.tier.mv", "MV Heat (600 - 1200°C)");
        add("heat.eof.tier.hv", "HV Heat (1200 - 1800°C)");
        add("heat.eof.tier.ev", "EV Heat (1800 - 2500°C)");
        add("heat.eof.tier.iv", "IV Heat (2500 - 3500°C)");

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

        add(EOFItems.CRACKED_RAW_SPHALERITE.get(), "Cracked Sphalerite");
        add(EOFItems.CRACKED_RAW_CASSITERITE.get(), "Cracked Cassiterite");
        add(EOFItems.CRACKED_RAW_ACANTHITE.get(), "Cracked Acanthite");
        add(EOFItems.CRACKED_RAW_WOLFRAMITE.get(), "Cracked Wolframite");
        add(EOFItems.CRACKED_RAW_RUTILE.get(), "Cracked Rutile");
        add(EOFItems.CRACKED_RAW_CHROMITE.get(), "Cracked Chromite");
        add(EOFItems.CRACKED_RAW_GALENA.get(), "Cracked Galena");
        add(EOFItems.CRACKED_RAW_PYRITE.get(), "Cracked Pyrite");

        add(EOFItems.ZINC_INGOT.get(), "Zinc Ingot");
        add(EOFItems.TIN_INGOT.get(), "Tin Ingot");
        add(EOFItems.SILVER_INGOT.get(), "Silver Ingot");
        add(EOFItems.TUNGSTEN_INGOT.get(), "Tungsten Ingot");
        add(EOFItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(EOFItems.CHROMIUM_INGOT.get(), "Chromium Ingot");
        add(EOFItems.LEAD_INGOT.get(), "Lead Ingot");
        add(EOFItems.BRASS_INGOT.get(), "Brass Ingot");
        add(EOFItems.BRONZE_INGOT.get(), "Bronze Ingot");

        add(EOFItems.ZINC_DUST.get(), "Zinc Dust");
        add(EOFItems.TIN_DUST.get(), "Tin Dust");
        add(EOFItems.SILVER_DUST.get(), "Silver Dust");
        add(EOFItems.TUNGSTEN_DUST.get(), "Tungsten Dust");
        add(EOFItems.TITANIUM_DUST.get(), "Titanium Dust");
        add(EOFItems.CHROMIUM_DUST.get(), "Chromium Dust");
        add(EOFItems.LEAD_DUST.get(), "Lead Dust");
        add(EOFItems.IRON_DUST.get(), "Iron Dust");

        add(EOFItems.VIAL.get(), "Vial");
        add("item.eof.vial.contents", "Contents: %1$s (%2$s mB)");

        add("fluid.eof.brine", "Brine");
        add("fluid.eof.sodium_hydroxide", "Sodium Hydroxide");
        add("fluid.eof.sulfuric_acid", "Sulfuric Acid");
        add("fluid.eof.hydrochloric_acid", "Hydrochloric Acid");
    }
}
