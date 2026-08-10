package net.asunacraft.eof.client.screen;

import net.asunacraft.eof.energy.VoltageTier;
import net.asunacraft.eof.menu.AbstractElectricMachineMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * Shared GUI for electric machines: renders the background texture, the
 * animated progress arrow and the energy bar, plus a hover tooltip showing
 * stored energy and tier. Subclasses supply the menu type.
 */
public abstract class AbstractElectricMachineScreen<T extends AbstractElectricMachineMenu>
        extends AbstractContainerScreen<T> {
    protected static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("eof",
            "textures/gui/machine.png");
    private static final int ENERGY_BAR_X = 8;
    private static final int ENERGY_BAR_Y = 17;
    private static final int ENERGY_BAR_W = 12;
    private static final int ENERGY_BAR_H = 52;
    private static final int ARROW_H = 17;
    private static final int[] ENERGY_GRADIENT = {
            0xFF5BE8FF, 0xFF3BB7F5, 0xFF2280DC, 0xFF164FB0, 0xFF0E337E
    };

    protected AbstractElectricMachineScreen(T menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    // --- progress arrow geometry (override for custom layouts) ---

    protected int getArrowU() {
        return 176;
    }

    protected int getArrowV() {
        return 14;
    }

    protected int getArrowX() {
        return 79;
    }

    protected int getArrowY() {
        return 34;
    }

    protected int getArrowWidth() {
        return 24;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(BACKGROUND, x, y, 0, 0, imageWidth, imageHeight);

        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();
        if (maxProgress > 0 && progress > 0) {
            int arrow = Math.min(getArrowWidth(), progress * getArrowWidth() / maxProgress);
            guiGraphics.blit(BACKGROUND, x + getArrowX(), y + getArrowY(),
                    getArrowU(), getArrowV(), arrow + 1, ARROW_H);
        }

        int energy = menu.getEnergyStored();
        int capacity = menu.getEnergyCapacity();
        if (capacity > 0) {
            guiGraphics.fill(x + ENERGY_BAR_X - 1, y + ENERGY_BAR_Y - 1,
                    x + ENERGY_BAR_X + ENERGY_BAR_W + 1, y + ENERGY_BAR_Y + ENERGY_BAR_H + 1, 0xFF202020);
            guiGraphics.fill(x + ENERGY_BAR_X, y + ENERGY_BAR_Y,
                    x + ENERGY_BAR_X + ENERGY_BAR_W, y + ENERGY_BAR_Y + ENERGY_BAR_H, 0xFF0A0A0A);

            int fill = (int) ((long) energy * ENERGY_BAR_H / capacity);
            for (int i = 0; i < fill; i++) {
                int row = y + ENERGY_BAR_Y + ENERGY_BAR_H - 1 - i;
                int color = ENERGY_GRADIENT[Math.min(ENERGY_GRADIENT.length - 1,
                        i * ENERGY_GRADIENT.length / Math.max(1, fill))];
                guiGraphics.fill(x + ENERGY_BAR_X, row, x + ENERGY_BAR_X + ENERGY_BAR_W, row + 1, color);
            }
            if (fill > 0) {
                guiGraphics.fill(x + ENERGY_BAR_X, y + ENERGY_BAR_Y + ENERGY_BAR_H - fill,
                        x + ENERGY_BAR_X + ENERGY_BAR_W, y + ENERGY_BAR_Y + ENERGY_BAR_H - fill + 1, 0xFF9FF6FF);
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x404040, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        if (isHoveringEnergyBar(mouseX - x, mouseY - y)) {
            VoltageTier tier = VoltageTier.values()[menu.getTierOrdinal() % VoltageTier.values().length];
            Component line = Component.literal("Energy: " + menu.getEnergyStored() + " / " + menu.getEnergyCapacity()
                    + " FE")
                    .append(Component.literal(" (" + tier.getDisplayName().getString() + " " + tier.getVoltage()
                            + " FE/t · " + "1 A)"));
            guiGraphics.renderTooltip(this.font, line, mouseX, mouseY);
        }
    }

    private boolean isHoveringEnergyBar(int relX, int relY) {
        return relX >= ENERGY_BAR_X - 1 && relX <= ENERGY_BAR_X + ENERGY_BAR_W + 1
                && relY >= ENERGY_BAR_Y - 1 && relY <= ENERGY_BAR_Y + ENERGY_BAR_H + 1;
    }
}
