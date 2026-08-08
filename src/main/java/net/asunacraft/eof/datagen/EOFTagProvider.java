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
                        key(EOFBlocks.CASSITERITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_CASSITERITE_ORE.get()),
                        key(EOFBlocks.ACANTHITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_ACANTHITE_ORE.get()),
                        key(EOFBlocks.WOLFRAMITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE.get()),
                        key(EOFBlocks.RUTILE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_RUTILE_ORE.get()),
                        key(EOFBlocks.CHROMITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_CHROMITE_ORE.get()),
                        key(EOFBlocks.GALENA_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_GALENA_ORE.get()),
                        key(EOFBlocks.PYRITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_PYRITE_ORE.get()));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(key(EOFBlocks.CASSITERITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_CASSITERITE_ORE.get()),
                        key(EOFBlocks.GALENA_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_GALENA_ORE.get()),
                        key(EOFBlocks.PYRITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_PYRITE_ORE.get()));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(key(EOFBlocks.SPHALERITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_SPHALERITE_ORE.get()),
                        key(EOFBlocks.ACANTHITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_ACANTHITE_ORE.get()),
                        key(EOFBlocks.WOLFRAMITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_WOLFRAMITE_ORE.get()),
                        key(EOFBlocks.RUTILE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_RUTILE_ORE.get()),
                        key(EOFBlocks.CHROMITE_ORE.get()),
                        key(EOFBlocks.DEEPSLATE_CHROMITE_ORE.get()));
    }

    @SuppressWarnings("deprecation")
    private ResourceKey<Block> key(Block block) {
        return block.builtInRegistryHolder().key();
    }
}
