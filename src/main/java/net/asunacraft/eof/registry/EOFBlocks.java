package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.block.ElectricCrusherBlock;
import net.asunacraft.eof.block.ElectricFurnaceBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOFBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            EchoesOfFactoria.MODID);

    public static final RegistryObject<Block> ELECTRIC_FURNACE = BLOCKS.register(
            "electric_furnace",
            () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ELECTRIC_CRUSHER = BLOCKS.register(
            "electric_crusher",
            () -> new ElectricCrusherBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CASING_ULV = casing("casing_ulv");
    public static final RegistryObject<Block> CASING_LV = casing("casing_lv");
    public static final RegistryObject<Block> CASING_MV = casing("casing_mv");
    public static final RegistryObject<Block> CASING_HV = casing("casing_hv");
    public static final RegistryObject<Block> CASING_EV = casing("casing_ev");
    public static final RegistryObject<Block> CASING_IV = casing("casing_iv");

    public static final RegistryObject<Block> SPHALERITE_ORE = ore("sphalerite_ore", MapColor.SAND, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_SPHALERITE_ORE = ore("deepslate_sphalerite_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> CASSITERITE_ORE = ore("cassiterite_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_CASSITERITE_ORE = ore("deepslate_cassiterite_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> ACANTHITE_ORE = ore("acanthite_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_ACANTHITE_ORE = ore("deepslate_acanthite_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> WOLFRAMITE_ORE = ore("wolframite_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_WOLFRAMITE_ORE = ore("deepslate_wolframite_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> RUTILE_ORE = ore("rutile_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_RUTILE_ORE = ore("deepslate_rutile_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> CHROMITE_ORE = ore("chromite_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_CHROMITE_ORE = ore("deepslate_chromite_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> GALENA_ORE = ore("galena_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_GALENA_ORE = ore("deepslate_galena_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    public static final RegistryObject<Block> PYRITE_ORE = ore("pyrite_ore", MapColor.STONE, SoundType.STONE);
    public static final RegistryObject<Block> DEEPSLATE_PYRITE_ORE = ore("deepslate_pyrite_ore", MapColor.DEEPSLATE, SoundType.DEEPSLATE);

    private static RegistryObject<Block> casing(String name) {
        return BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(3.5f)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops()));
    }

    private static RegistryObject<Block> ore(String name, MapColor color, SoundType sound) {
        return BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(3.0f)
                .sound(sound)
                .requiresCorrectToolForDrops()));
    }
}
