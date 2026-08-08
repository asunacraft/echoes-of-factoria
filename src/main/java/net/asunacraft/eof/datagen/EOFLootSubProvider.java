package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class EOFLootSubProvider extends BlockLootSubProvider {
    public EOFLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(EOFBlocks.ELECTRIC_FURNACE.get());

        this.add(EOFBlocks.SPHALERITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_SPHALERITE.get()));
        this.add(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get(),
                block -> createOreDrop(block, EOFItems.RAW_SPHALERITE.get()));
        this.add(EOFBlocks.CASSITERITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_CASSITERITE.get()));
        this.add(EOFBlocks.DEEPSLATE_CASSITERITE_ORE.get(),
                block -> createOreDrop(block, EOFItems.RAW_CASSITERITE.get()));
        this.add(EOFBlocks.ACANTHITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_ACANTHITE.get()));
        this.add(EOFBlocks.DEEPSLATE_ACANTHITE_ORE.get(),
                block -> createOreDrop(block, EOFItems.RAW_ACANTHITE.get()));
        this.add(EOFBlocks.WOLFRAMITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_WOLFRAMITE.get()));
        this.add(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE.get(),
                block -> createOreDrop(block, EOFItems.RAW_WOLFRAMITE.get()));
        this.add(EOFBlocks.RUTILE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_RUTILE.get()));
        this.add(EOFBlocks.DEEPSLATE_RUTILE_ORE.get(),
                block -> createOreDrop(block, EOFItems.RAW_RUTILE.get()));
        this.add(EOFBlocks.CHROMITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_CHROMITE.get()));
        this.add(EOFBlocks.DEEPSLATE_CHROMITE_ORE.get(),
                block -> createOreDrop(block, EOFItems.RAW_CHROMITE.get()));
        this.add(EOFBlocks.GALENA_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_GALENA.get()));
        this.add(EOFBlocks.DEEPSLATE_GALENA_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_GALENA.get()));
        this.add(EOFBlocks.PYRITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_PYRITE.get()));
        this.add(EOFBlocks.DEEPSLATE_PYRITE_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_PYRITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EOFBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
