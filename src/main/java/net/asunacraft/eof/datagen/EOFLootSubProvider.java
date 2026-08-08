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
        this.add(EOFBlocks.RAW_TIN_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_TIN.get()));
        this.add(EOFBlocks.DEEPSLATE_RAW_TIN_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_TIN.get()));
        this.add(EOFBlocks.RAW_SILVER_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_SILVER.get()));
        this.add(EOFBlocks.DEEPSLATE_RAW_SILVER_ORE.get(), block -> createOreDrop(block, EOFItems.RAW_SILVER.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EOFBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
