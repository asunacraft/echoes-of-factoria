package net.asunacraft.eof.energy;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Heat tiers for the packet-based temperature system.
 *
 * <p>All heat values are stored in Kelvin (the absolute scale used by real
 * thermochemistry) and converted to Celsius only when displayed. Each tier
 * spans a real-world process range: brine evaporation, sulfide roasting,
 * smelting, iron making, and refractory processes.</p>
 */
public enum TemperatureTier {
    ULV("ulv", 313, 473, ChatFormatting.DARK_GRAY),      // 40–200°C    brine evaporation, drying
    LV("lv", 473, 873, ChatFormatting.GREEN),            // 200–600°C   sulfide roasting → SO₂
    MV("mv", 873, 1473, ChatFormatting.GOLD),            // 600–1200°C  brass/bronze/lead smelting
    HV("hv", 1473, 2073, ChatFormatting.RED),            // 1200–1800°C blast furnace, Kroll (Ti)
    EV("ev", 2073, 2773, ChatFormatting.LIGHT_PURPLE),   // 1800–2500°C chromite roast
    IV("iv", 2773, 3773, ChatFormatting.BLUE);           // 2500–3500°C tungsten sintering

    /** The temperature of a machine that has not been heated, in Kelvin. */
    public static final int AMBIENT_KELVIN = 293;

    private final String name;
    private final int minKelvin;
    private final int maxKelvin;
    private final ChatFormatting color;

    TemperatureTier(String name, int minKelvin, int maxKelvin, ChatFormatting color) {
        this.name = name;
        this.minKelvin = minKelvin;
        this.maxKelvin = maxKelvin;
        this.color = color;
    }

    /** Convert an absolute temperature to the human-readable Celsius scale. */
    public static int toCelsius(int kelvin) {
        return kelvin - 273;
    }

    /** Convert a Celsius value to the internal Kelvin scale. 273C is absolute zero */
    public static int fromCelsius(int celsius) {
        return celsius + 273;
    }

    public int getMinKelvin() {
        return minKelvin;
    }

    public int getMaxKelvin() {
        return maxKelvin;
    }

    public int getMinCelsius() {
        return toCelsius(minKelvin);
    }

    public int getMaxCelsius() {
        return toCelsius(maxKelvin);
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getColor() {
        return color;
    }

    public Component getDisplayName() {
        return Component.translatable("heat.eof.tier." + name);
    }

    /** Does this tier cover the given absolute temperature? */
    public boolean contains(int kelvin) {
        return kelvin >= minKelvin && kelvin < maxKelvin;
    }

    public TemperatureTier next() {
        TemperatureTier[] values = values();
        int i = ordinal() + 1;
        return i < values.length ? values[i] : values[values.length - 1];
    }

    public TemperatureTier previous() {
        int i = ordinal() - 1;
        return i >= 0 ? values()[i] : values()[0];
    }
}
