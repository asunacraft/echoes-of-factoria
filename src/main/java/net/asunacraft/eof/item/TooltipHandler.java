package net.asunacraft.eof.item;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = EchoesOfFactoria.MODID)
public class TooltipHandler {
    private static final Map<RegistryObject<? extends Item>, Component> CHEMISTRY = new HashMap<>();

    static {
        CHEMISTRY.put(EOFItems.SPHALERITE_ORE, formula("ZnS \u00B7 Zinc Sulfide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_SPHALERITE_ORE, formula("ZnS \u00B7 Zinc Sulfide"));
        CHEMISTRY.put(EOFItems.CASSITERITE_ORE, formula("SnO\u2082 \u00B7 Tin Dioxide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_CASSITERITE_ORE, formula("SnO\u2082 \u00B7 Tin Dioxide"));
        CHEMISTRY.put(EOFItems.ACANTHITE_ORE, formula("Ag\u2082S \u00B7 Silver Sulfide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_ACANTHITE_ORE, formula("Ag\u2082S \u00B7 Silver Sulfide"));

        CHEMISTRY.put(EOFItems.WOLFRAMITE_ORE, formula("WO\u2083 \u00B7 Tungsten Trioxide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_WOLFRAMITE_ORE, formula("WO\u2083 \u00B7 Tungsten Trioxide"));
        CHEMISTRY.put(EOFItems.RUTILE_ORE, formula("TiO\u2082 \u00B7 Titanium Dioxide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_RUTILE_ORE, formula("TiO\u2082 \u00B7 Titanium Dioxide"));
        CHEMISTRY.put(EOFItems.CHROMITE_ORE, formula("FeCr\u2082O\u2084 \u00B7 Chromite"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_CHROMITE_ORE, formula("FeCr\u2082O\u2084 \u00B7 Chromite"));
        CHEMISTRY.put(EOFItems.GALENA_ORE, formula("PbS \u00B7 Lead Sulfide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_GALENA_ORE, formula("PbS \u00B7 Lead Sulfide"));
        CHEMISTRY.put(EOFItems.PYRITE_ORE, formula("FeS\u2082 \u00B7 Iron Sulfide"));
        CHEMISTRY.put(EOFItems.DEEPSLATE_PYRITE_ORE, formula("FeS\u2082 \u00B7 Iron Sulfide"));

        CHEMISTRY.put(EOFItems.RAW_SPHALERITE, formula("ZnS \u00B7 Zinc Sulfide"));
        CHEMISTRY.put(EOFItems.RAW_CASSITERITE, formula("SnO\u2082 \u00B7 Tin Dioxide"));
        CHEMISTRY.put(EOFItems.RAW_ACANTHITE, formula("Ag\u2082S \u00B7 Silver Sulfide"));
        CHEMISTRY.put(EOFItems.RAW_WOLFRAMITE, formula("WO\u2083 \u00B7 Tungsten Trioxide"));
        CHEMISTRY.put(EOFItems.RAW_RUTILE, formula("TiO\u2082 \u00B7 Titanium Dioxide"));
        CHEMISTRY.put(EOFItems.RAW_CHROMITE, formula("FeCr\u2082O\u2084 \u00B7 Chromite"));
        CHEMISTRY.put(EOFItems.RAW_GALENA, formula("PbS \u00B7 Lead Sulfide"));
        CHEMISTRY.put(EOFItems.RAW_PYRITE, formula("FeS\u2082 \u00B7 Iron Sulfide"));

        CHEMISTRY.put(EOFItems.ZINC_INGOT, formula("Zn \u00B7 Zinc"));
        CHEMISTRY.put(EOFItems.TIN_INGOT, formula("Sn \u00B7 Tin"));
        CHEMISTRY.put(EOFItems.SILVER_INGOT, formula("Ag \u00B7 Silver"));
        CHEMISTRY.put(EOFItems.TUNGSTEN_INGOT, formula("W \u00B7 Tungsten"));
        CHEMISTRY.put(EOFItems.TITANIUM_INGOT, formula("Ti \u00B7 Titanium"));
        CHEMISTRY.put(EOFItems.CHROMIUM_INGOT, formula("Cr \u00B7 Chromium"));
        CHEMISTRY.put(EOFItems.LEAD_INGOT, formula("Pb \u00B7 Lead"));
        CHEMISTRY.put(EOFItems.BRASS_INGOT, formula("CuZn \u00B7 Brass"));
        CHEMISTRY.put(EOFItems.BRONZE_INGOT, formula("CuSn \u00B7 Bronze"));
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        for (Map.Entry<RegistryObject<? extends Item>, Component> entry : CHEMISTRY.entrySet()) {
            if (entry.getKey().get() == item) {
                event.getToolTip().add(entry.getValue());
                return;
            }
        }
    }

    private static Component formula(String text) {
        return Component.literal(text).withStyle(ChatFormatting.GRAY);
    }
}
