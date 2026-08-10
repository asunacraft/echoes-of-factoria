package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFFluids;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class EOFBlockStateProvider extends BlockStateProvider {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public EOFBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        electricFurnace();
        electricCrusher();
        steamBoiler();

        simpleCasing(EOFBlocks.CASING_ULV);
        simpleCasing(EOFBlocks.CASING_LV);
        simpleCasing(EOFBlocks.CASING_MV);
        simpleCasing(EOFBlocks.CASING_HV);
        simpleCasing(EOFBlocks.CASING_EV);
        simpleCasing(EOFBlocks.CASING_IV);

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

        fluidBlock(EOFFluids.BRINE_BLOCK);
        fluidBlock(EOFFluids.SODIUM_HYDROXIDE_BLOCK);
        fluidBlock(EOFFluids.SULFURIC_ACID_BLOCK);
        fluidBlock(EOFFluids.HYDROCHLORIC_ACID_BLOCK);
    }

    private void electricFurnace() {
        ModelFile off = models().getExistingFile(modLoc("block/electric_furnace"));
        ModelFile on = models().getExistingFile(modLoc("block/electric_furnace_on"));
        getVariantBuilder(EOFBlocks.ELECTRIC_FURNACE.get())
                .partialState().with(FACING, Direction.NORTH).with(LIT, false).modelForState().modelFile(off).rotationY(0).addModel()
                .partialState().with(FACING, Direction.EAST).with(LIT, false).modelForState().modelFile(off).rotationY(90).addModel()
                .partialState().with(FACING, Direction.SOUTH).with(LIT, false).modelForState().modelFile(off).rotationY(180).addModel()
                .partialState().with(FACING, Direction.WEST).with(LIT, false).modelForState().modelFile(off).rotationY(270).addModel()
                .partialState().with(FACING, Direction.NORTH).with(LIT, true).modelForState().modelFile(on).rotationY(0).addModel()
                .partialState().with(FACING, Direction.EAST).with(LIT, true).modelForState().modelFile(on).rotationY(90).addModel()
                .partialState().with(FACING, Direction.SOUTH).with(LIT, true).modelForState().modelFile(on).rotationY(180).addModel()
                .partialState().with(FACING, Direction.WEST).with(LIT, true).modelForState().modelFile(on).rotationY(270).addModel();
        simpleBlockItem(EOFBlocks.ELECTRIC_FURNACE.get(), off);
    }

    private void steamBoiler() {
        ModelFile off = models().getExistingFile(modLoc("block/steam_boiler"));
        ModelFile on = models().getExistingFile(modLoc("block/steam_boiler_on"));
        getVariantBuilder(EOFBlocks.STEAM_BOILER.get())
                .partialState().with(FACING, Direction.NORTH).with(LIT, false).modelForState().modelFile(off).rotationY(0).addModel()
                .partialState().with(FACING, Direction.EAST).with(LIT, false).modelForState().modelFile(off).rotationY(90).addModel()
                .partialState().with(FACING, Direction.SOUTH).with(LIT, false).modelForState().modelFile(off).rotationY(180).addModel()
                .partialState().with(FACING, Direction.WEST).with(LIT, false).modelForState().modelFile(off).rotationY(270).addModel()
                .partialState().with(FACING, Direction.NORTH).with(LIT, true).modelForState().modelFile(on).rotationY(0).addModel()
                .partialState().with(FACING, Direction.EAST).with(LIT, true).modelForState().modelFile(on).rotationY(90).addModel()
                .partialState().with(FACING, Direction.SOUTH).with(LIT, true).modelForState().modelFile(on).rotationY(180).addModel()
                .partialState().with(FACING, Direction.WEST).with(LIT, true).modelForState().modelFile(on).rotationY(270).addModel();
        simpleBlockItem(EOFBlocks.STEAM_BOILER.get(), off);
    }

    private void electricCrusher() {
        ModelFile off = models().getExistingFile(modLoc("block/electric_crusher"));
        ModelFile on = models().getExistingFile(modLoc("block/electric_crusher_on"));
        getVariantBuilder(EOFBlocks.ELECTRIC_CRUSHER.get())
                .partialState().with(FACING, Direction.NORTH).with(LIT, false).modelForState().modelFile(off).rotationY(0).addModel()
                .partialState().with(FACING, Direction.EAST).with(LIT, false).modelForState().modelFile(off).rotationY(90).addModel()
                .partialState().with(FACING, Direction.SOUTH).with(LIT, false).modelForState().modelFile(off).rotationY(180).addModel()
                .partialState().with(FACING, Direction.WEST).with(LIT, false).modelForState().modelFile(off).rotationY(270).addModel()
                .partialState().with(FACING, Direction.NORTH).with(LIT, true).modelForState().modelFile(on).rotationY(0).addModel()
                .partialState().with(FACING, Direction.EAST).with(LIT, true).modelForState().modelFile(on).rotationY(90).addModel()
                .partialState().with(FACING, Direction.SOUTH).with(LIT, true).modelForState().modelFile(on).rotationY(180).addModel()
                .partialState().with(FACING, Direction.WEST).with(LIT, true).modelForState().modelFile(on).rotationY(270).addModel();
        simpleBlockItem(EOFBlocks.ELECTRIC_CRUSHER.get(), off);
    }

    private void simpleOre(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        ResourceLocation texture = modLoc("block/ores/" + name);
        ModelFile model = models().cubeAll(name, texture);
        simpleBlockWithItem(block.get(), model);
    }

    private void simpleCasing(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        ResourceLocation texture = modLoc("block/machine_casings/" + name);
        ModelFile model = models().cubeAll(name, texture);
        simpleBlockWithItem(block.get(), model);
    }

    private void fluidBlock(RegistryObject<LiquidBlock> block) {
        String name = block.getId().getPath();
        ModelFile model = models().getBuilder("block/" + name)
                .texture("particle", modLoc("block/fluids/" + name + "_still"));
        getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(model));
    }
}
