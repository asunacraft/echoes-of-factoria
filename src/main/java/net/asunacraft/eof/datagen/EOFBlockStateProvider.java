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
        simpleOre(EOFBlocks.CASSITERITE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_CASSITERITE_ORE);
        simpleOre(EOFBlocks.ACANTHITE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_ACANTHITE_ORE);
        simpleOre(EOFBlocks.WOLFRAMITE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE);
        simpleOre(EOFBlocks.RUTILE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_RUTILE_ORE);
        simpleOre(EOFBlocks.CHROMITE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_CHROMITE_ORE);
        simpleOre(EOFBlocks.GALENA_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_GALENA_ORE);
        simpleOre(EOFBlocks.PYRITE_ORE);
        simpleOre(EOFBlocks.DEEPSLATE_PYRITE_ORE);
    }

    private void simpleOre(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        ResourceLocation texture = modLoc("block/ores/" + name);
        ModelFile model = models().cubeAll(name, texture);
        simpleBlockWithItem(block.get(), model);
    }
}
