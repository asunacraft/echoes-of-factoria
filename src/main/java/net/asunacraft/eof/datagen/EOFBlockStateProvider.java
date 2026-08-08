package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class EOFBlockStateProvider extends BlockStateProvider {
    public EOFBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleOre(EOFBlocks.SPHALERITE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_SPHALERITE_ORE);
        simpleOre(EOFBlocks.RAW_TIN_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_RAW_TIN_ORE);
        simpleOre(EOFBlocks.RAW_SILVER_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_RAW_SILVER_ORE);
    }

    private void simpleOre(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        ResourceLocation texture = modLoc("block/ores/" + name);
        ModelFile model = models().cubeAll(name, texture);
        simpleBlockWithItem(block.get(), model);
    }
}
