package net.asunacraft.eof.energy;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Voltage tiers for the packet-based energy system.
 *
 * <p>Each tier has a nominal "voltage": the amount of FE carried by a single
 * packet of that tier. A machine rated for a tier can accept packets at that
 * tier or lower; a cable rated for a tier can carry up to its max amperage
 * packets per tick at that tier.</p>
 */
public enum VoltageTier {
    ULV("ulv", 8, ChatFormatting.DARK_GRAY),
    LV("lv", 32, ChatFormatting.GREEN),
    MV("mv", 128, ChatFormatting.GOLD),
    HV("hv", 512, ChatFormatting.RED),
    EV("ev", 2048, ChatFormatting.LIGHT_PURPLE),
    IV("iv", 8192, ChatFormatting.BLUE);

    private final String name;
    private final int voltage;
    private final ChatFormatting color;

    VoltageTier(String name, int voltage, ChatFormatting color) {
        this.name = name;
        this.voltage = voltage;
        this.color = color;
    }

    /** FE carried by one packet of this tier. */
    public int getVoltage() {
        return voltage;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getColor() {
        return color;
    }

    public Component getDisplayName() {
        return Component.translatable("energy.eof.tier." + name);
    }

    /** Can a host rated for this tier accept packets from {@code other}? */
    public boolean canAccept(VoltageTier other) {
        return other.ordinal() <= this.ordinal();
    }

    public VoltageTier next() {
        VoltageTier[] values = values();
        int i = ordinal() + 1;
        return i < values.length ? values[i] : values[values.length - 1];
    }

    public VoltageTier previous() {
        int i = ordinal() - 1;
        return i >= 0 ? values()[i] : values()[0];
    }
}
