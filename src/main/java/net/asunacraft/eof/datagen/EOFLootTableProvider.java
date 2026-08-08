package net.asunacraft.eof.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class EOFLootTableProvider extends LootTableProvider {
    public EOFLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(EOFLootSubProvider::new, LootContextParamSets.BLOCK)));
    }
}
