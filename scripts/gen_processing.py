#!/usr/bin/env python3
"""generate cracked-ore, dust, and electric-crusher textures.

cracked_<ore>.png   - the raw ore texture with dark crack lines scribed across it
<metal>_dust.png    - a granular powder pile, colors sampled from the ingot texture
electric_[crusher|furnace]_front[_on].png - machine faces in the LV casing style

run with: python3 scripts/gen_processing.py
requires: pillow (pip install Pillow)
"""

from PIL import Image
import math
import os
import random

HERE = os.path.dirname(os.path.abspath(__file__))
ASSETS = os.path.join(HERE, "..", "src", "main", "resources", "assets", "eof", "textures")
ITEM_RAW = os.path.join(ASSETS, "item", "raw")
ITEM_INGOT = os.path.join(ASSETS, "item", "ingot")
ITEM_CRACKED = os.path.join(ASSETS, "item", "cracked")
ITEM_DUST = os.path.join(ASSETS, "item", "dust")
BLOCK = os.path.join(ASSETS, "block")
BLOCK_MACHINES = os.path.join(BLOCK, "machines")

ORES = ["sphalerite", "cassiterite", "acanthite", "wolframite",
        "rutile", "chromite", "galena", "pyrite"]

# metal: name of the ingot texture to sample colors from.
# iron has no eof ingot yet, so it gets an explicit palette.
METAL_INGOT = {
    "zinc": "zinc", "tin": "tin", "silver": "silver", "tungsten": "tungsten",
    "titanium": "titanium", "chromium": "chromium", "lead": "lead",
}
IRON_DUST_PALETTE = ((42, 44, 50), (110, 114, 122), (168, 172, 180))

random.seed(137)


def dominant(ingot_path):
    """Average RGB of the opaque pixels of an ingot texture."""
    im = Image.open(ingot_path).convert("RGBA")
    px = im.load()
    rs = gs = bs = n = 0
    for y in range(im.height):
        for x in range(im.width):
            r, g, b, a = px[x, y]
            if a < 40:
                continue
            rs += r
            gs += g
            bs += b
            n += 1
    return (rs // n, gs // n, bs // n) if n else (128, 128, 128)


def shade(base, t):
    return tuple(max(0, min(255, round(c * t))) for c in base)

def gen_cracked():
    os.makedirs(ITEM_CRACKED, exist_ok=True)
    for ore in ORES:
        src = os.path.join(ITEM_RAW, f"raw_{ore}.png")
        if not os.path.exists(src):
            print(f"skip {ore}: missing {src}")
            continue
        im = Image.open(src).convert("RGBA")
        px = im.load()
        # keep track of used starting regions to reduce crack-on-crack overlap
        used_starts = []
        for _ in range(3):
            for _attempt in range(5):
                x = random.randint(1, 15)
                y = random.randint(1, 7)
                if all(abs(x - ux) + abs(y - uy) > 4 for ux, uy in used_starts):
                    break
            used_starts.append((x, y))
            length = random.randint(6, 10)
            for i in range(length):
                for (ox, oy, factor) in [(0, 0, 0.30), (1, 0, 0.55)]:
                    nx, ny = x + ox, y + oy
                    if 0 <= nx < 16 and 0 <= ny < 16:
                        r, g, b, a = px[nx, ny]
                        if a > 40:
                            px[nx, ny] = (int(r * factor), int(g * factor), int(b * factor), a)
                if random.random() < 0.25:
                    x += random.choice([-2, 2])
                else:
                    x += random.choice([-1, 0, 1])
                y += 1
        out = os.path.join(ITEM_CRACKED, f"cracked_raw_{ore}.png")
        im.save(out)
        print(f"item/cracked/cracked_raw_{ore}.png")

def gen_dust():
    os.makedirs(ITEM_DUST, exist_ok=True)
    # little powder-pile silhouette on a transparent canvas
    pile = [[0] * 16 for _ in range(16)]
    for y in range(4, 15):
        for x in range(1, 15):
            if math.hypot(x - 8, (y - 11) * 1.6) < 6.6 - (y - 9) * 0.45:
                pile[y][x] = 1
    for metal, ingot in METAL_INGOT.items():
        base = dominant(os.path.join(ITEM_INGOT, f"{ingot}_ingot.png"))
        im = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
        px = im.load()
        for y in range(16):
            for x in range(16):
                if not pile[y][x]:
                    continue
                # granularity: mix a few discrete grain shades around the base
                t = random.choice((0.82, 0.92, 1.0, 1.0, 1.08, 1.18))
                px[x, y] = (*shade(base, t), 255)
        im.save(os.path.join(ITEM_DUST, f"{metal}_dust.png"))
        print(f"item/dust/{metal}_dust.png")
    # iron dust: explicit palette
    im = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = im.load()
    for y in range(16):
        for x in range(16):
            if not pile[y][x]:
                continue
            t = random.choice((0.82, 0.92, 1.0, 1.0, 1.08, 1.18))
            color = IRON_DUST_PALETTE[2] if t >= 1.05 else shade(IRON_DUST_PALETTE[1], t)
            px[x, y] = (*color, 255)
    im.save(os.path.join(ITEM_DUST, "iron_dust.png"))
    print("item/dust/iron_dust.png")


def gen_machine_fronts():
    os.makedirs(BLOCK_MACHINES, exist_ok=True)
    casing = Image.open(os.path.join(BLOCK, "machine_casings", "casing_lv.png")).convert("RGB")
    for machine, detail in (("electric_furnace", paint_furnace_front),
                            ("electric_crusher", paint_crusher_front)):
        for lit in (False, True):
            im = casing.copy()
            detail(im.load(), lit)
            name = f"{machine}_front{'_on' if lit else ''}.png"
            im.save(os.path.join(BLOCK_MACHINES, name))
            print(f"block/machines/{name}")


def paint_furnace_front(px, lit):
    # recessed door opening in the panel interior
    for x in range(4, 12):
        for y in range(4, 12):
            px[x, y] = (40, 40, 44)
    for x in range(4, 12):
        px[x, 4] = (24, 24, 26)
    # heating coil: a zigzag element across the opening
    coil = (255, 170, 60) if lit else (120, 74, 30)
    for x in range(5, 11):
        for y in range(7, 9):
            px[x, y] = coil
    px[5, 6] = coil
    px[10, 9] = coil
    if lit:
        # warm glow spilling onto the panel around the opening
        for x in range(3, 13):
            for y in range(3, 13):
                r, g, b = px[x, y]
                px[x, y] = (min(255, r + 30), min(255, g + 22), min(255, b + 14))


def paint_crusher_front(px, lit):
    # top feed hopper (dark mouth)
    for x in range(3, 13):
        for y in range(2, 5):
            px[x, y] = (28, 26, 30)
    for x in range(4, 12):
        px[x, 5] = (52, 56, 60)
    # central grind slot between two rollers
    for x in range(5, 11):
        for y in range(7, 12):
            px[x, y] = (40, 44, 48)
    for x in range(6, 10):
        px[x, 7] = (20, 22, 26)
    # roller edge highlights beside the slot
    for y in range(7, 12):
        px[4, y] = (150, 190, 160)
        px[11, y] = (150, 190, 160)
    # lower output chute
    for x in range(4, 12):
        for y in range(12, 14):
            px[x, y] = (30, 28, 32)
    if lit:
        # glow strip across the grind slot and light spilling from the hopper
        for x in range(6, 10):
            px[x, 7] = (255, 214, 120)
        for x in range(3, 13):
            for y in range(2, 5):
                r, g, b = px[x, y]
                px[x, y] = (min(255, r + 60), min(255, g + 45), min(255, b + 20))


def main():
    gen_cracked()
    gen_dust()
    gen_machine_fronts()


if __name__ == "__main__":
    main()
