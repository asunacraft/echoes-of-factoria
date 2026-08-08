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
        basicItem(EOFItems.RAW_CASSITERITE, "raw");
        basicItem(EOFItems.RAW_ACANTHITE, "raw");
        basicItem(EOFItems.RAW_WOLFRAMITE, "raw");
        basicItem(EOFItems.RAW_RUTILE, "raw");
        basicItem(EOFItems.RAW_CHROMITE, "raw");
        basicItem(EOFItems.RAW_GALENA, "raw");
        basicItem(EOFItems.RAW_PYRITE, "raw");

        basicItem(EOFItems.ZINC_INGOT, "ingot");
        basicItem(EOFItems.TIN_INGOT, "ingot");
        basicItem(EOFItems.SILVER_INGOT, "ingot");
        basicItem(EOFItems.TUNGSTEN_INGOT, "ingot");
        basicItem(EOFItems.TITANIUM_INGOT, "ingot");
        basicItem(EOFItems.CHROMIUM_INGOT, "ingot");
        basicItem(EOFItems.LEAD_INGOT, "ingot");
        basicItem(EOFItems.BRASS_INGOT, "ingot");
        basicItem(EOFItems.BRONZE_INGOT, "ingot");
    }

    private void basicItem(RegistryObject<Item> item, String category) {
        String name = item.getId().getPath();
        ResourceLocation texture = modLoc("item/" + category + "/" + name);
        withExistingParent(name, "item/generated").texture("layer0", texture);
    }
}
