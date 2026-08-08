package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class EOFItemModelProvider extends ItemModelProvider {
    public EOFItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(EOFItems.RAW_SPHALERITE, "raw");
        basicItem(EOFItems.RAW_TIN, "raw");
        basicItem(EOFItems.RAW_SILVER, "raw");

        basicItem(EOFItems.ZINC_INGOT, "ingot");
        basicItem(EOFItems.TIN_INGOT, "ingot");
        basicItem(EOFItems.SILVER_INGOT, "ingot");
    }

    private void basicItem(RegistryObject<Item> item, String category) {
        String name = item.getId().getPath();
        ResourceLocation texture = modLoc("item/" + category + "/" + name);
        withExistingParent(name, "item/generated").texture("layer0", texture);
    }
}
