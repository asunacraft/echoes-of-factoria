#!/usr/bin/env python3
"""generate the electric machine GUI background texture.

The machines use a two-slot layout: input at (56, 35), output at (116, 35),
plus a left-hand energy bar at (8, 17)-(20, 69). The vanilla furnace
background bakes slots at (56, 17) and (56, 53) - which would put the item
input right on top of the vanilla flame/arrow artwork - so this script paints
a proper layout onto a copy of the vanilla texture:

  * erase the baked input slot at (56, 17)
  * erase the baked fuel slot at (56, 53)
  * stamp a fresh slot background at (55, 34) (our item input is at (56, 35),
    and vanilla slot sprites sit one pixel up-left of their slot)

The baked output slot (116, 35), the progress-arrow sprite at (176, 14) and
the player inventory slots are left untouched, so the runtime blits in
AbstractElectricMachineScreen keep working unchanged.

run with: python3 scripts/gen_gui.py
requires: pillow (pip install Pillow)
"""

from PIL import Image
import os

HERE = os.path.dirname(os.path.abspath(__file__))
TEMPLATE = os.path.join(HERE, "templates", "furnace.png")
OUT = os.path.join(HERE, "..", "src", "main", "resources", "assets", "eof",
                   "textures", "gui", "machine.png")

PANEL = (198, 198, 198, 255)   # vanilla furnace window background
SLOT = (18, 18)                # vanilla slot sprite size

# vanilla baked slot positions (measured: 18x18 sprites at these top-lefts)
OLD_INPUT = (55, 16)
OLD_FUEL = (55, 52)

# our machine layout. Vanilla slot sprites are drawn at slotPos - (1, 1):
# the item renders at slotPos + (1, 1), so the sprite's 2px asymmetric border
# puts the item perfectly in the middle of the slot - identical to the player
# inventory slots. Menu slot is at (56, 35), so the sprite goes at (55, 34).
INPUT_SPRITE = (55, 34)


def main():
    im = Image.open(TEMPLATE).convert("RGBA")
    panel = Image.new("RGBA", SLOT, PANEL)
    # a clean slot sprite from the player inventory area of the texture
    slot = im.crop((7, 83, 7 + SLOT[0], 83 + SLOT[1]))

    im.paste(panel, OLD_INPUT)
    im.paste(panel, OLD_FUEL)
    im.paste(slot, INPUT_SPRITE)

    os.makedirs(os.path.dirname(OUT), exist_ok=True)
    im.save(OUT)
    print("wrote", OUT, im.size)


if __name__ == "__main__":
    main()
