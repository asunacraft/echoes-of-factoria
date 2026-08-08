package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;

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
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SPHALERITE_ORE = BLOCKS.register(
            "sphalerite_ore",
            () -> ore(BlockBehaviour.Properties.of().mapColor(MapColor.SAND), SoundType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_SPHALERITE_ORE = BLOCKS.register(
            "deepslate_sphalerite_ore",
            () -> ore(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE), SoundType.DEEPSLATE));

    public static final RegistryObject<Block> RAW_TIN_ORE = BLOCKS.register(
            "raw_tin_ore",
            () -> ore(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), SoundType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_RAW_TIN_ORE = BLOCKS.register(
            "deepslate_raw_tin_ore",
            () -> ore(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE), SoundType.DEEPSLATE));

    public static final RegistryObject<Block> RAW_SILVER_ORE = BLOCKS.register(
            "raw_silver_ore",
            () -> ore(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), SoundType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_RAW_SILVER_ORE = BLOCKS.register(
            "deepslate_raw_silver_ore",
            () -> ore(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE), SoundType.DEEPSLATE));

    private static Block ore(BlockBehaviour.Properties properties, SoundType sound) {
        return new Block(properties
                .strength(3.0f)
                .sound(sound)
                .requiresCorrectToolForDrops());
    }
}
