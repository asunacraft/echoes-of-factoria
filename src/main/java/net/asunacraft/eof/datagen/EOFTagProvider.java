package net.asunacraft.eof.datagen;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.registry.EOFBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class EOFTagProvider extends TagsProvider<Block> {
    public EOFTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK, lookupProvider, EchoesOfFactoria.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(key(EOFBlocks.SPHALERITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get()),
                        key(EOFBlocks.RAW_TIN_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_RAW_TIN_ORE.get()),
                        key(EOFBlocks.RAW_SILVER_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_RAW_SILVER_ORE.get()));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(key(EOFBlocks.RAW_TIN_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_RAW_TIN_ORE.get()));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(key(EOFBlocks.SPHALERITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get()),
                        key(EOFBlocks.RAW_SILVER_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_RAW_SILVER_ORE.get()));
    }

    @SuppressWarnings("deprecation")
    private ResourceKey<Block> key(Block block) {
        return block.builtInRegistryHolder().key();
    }
}
