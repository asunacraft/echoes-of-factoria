#!/usr/bin/env python3
"""generate per-metal raw and ingot item textures from base templates.

takes the base shapes in scripts/templates/ and recolors them through each metal's palette. 

run with: python3 scripts/gen_textures.py
requires: pillow (pip install Pillow)
"""

from PIL import Image
import os

HERE = os.path.dirname(os.path.abspath(__file__))
TEMPLATES = os.path.join(HERE, "templates")
ASSETS = os.path.join(HERE, "..", "src", "main", "resources", "assets", "eof", "textures")
ITEM_RAW = os.path.join(ASSETS, "item", "raw")
ITEM_INGOT = os.path.join(ASSETS, "item", "ingot")

RAW_BASE = os.path.join(TEMPLATES, "raw_base.png")
INGOT_BASE = os.path.join(TEMPLATES, "ingot_base.png")

# name: raw texture suffix (raw_<name>.png)
# ingot: ingot texture suffix (<ingot>_ingot.png)
# palette: (dark stone, mid, bright metal) RGB stops for the luminance->color map
METALS = [
    {
        "name": "sphalerite",
        "ingot": "zinc",
        "palette": ((58, 72, 64), (128, 176, 140), (186, 220, 190)),
    },
    {
        "name": "tin",
        "ingot": "tin",
        "palette": ((92, 84, 74), (180, 160, 140), (238, 218, 196)),
    },
    {
        "name": "silver",
        "ingot": "silver",
        "palette": ((56, 62, 78), (106, 116, 144), (172, 184, 210)),
    },
]


def lerp(a, b, t):
    return tuple(round(a[i] + (b[i] - a[i]) * t) for i in range(3))


def recolor(base_path, palette):
    """Resize the base template to 16x16 and remap luminance through the palette."""
    img = Image.open(base_path).convert("RGBA").resize((16, 16), Image.LANCZOS)
    px = img.load()
    dark, mid, light = palette
    for y in range(16):
        for x in range(16):
            r, g, b, a = px[x, y]
            if a < 40:
                continue
            t = min(1.0, max(0.0, 0.2126 * r + 0.7152 * g + 0.0722 * b) / 255.0)
            color = lerp(dark, mid, t / 0.5) if t < 0.5 else lerp(mid, light, (t - 0.5) / 0.5)
            px[x, y] = (*color, a)
    return img


def main():
    os.makedirs(ITEM_RAW, exist_ok=True)
    os.makedirs(ITEM_INGOT, exist_ok=True)
    for metal in METALS:
        recolor(RAW_BASE, metal["palette"]).save(os.path.join(ITEM_RAW, f"raw_{metal['name']}.png"))
        recolor(INGOT_BASE, metal["palette"]).save(os.path.join(ITEM_INGOT, f"{metal['ingot']}_ingot.png"))
        print(f"item/raw/raw_{metal['name']}.png, item/ingot/{metal['ingot']}_ingot.png")


if __name__ == "__main__":
    main()
