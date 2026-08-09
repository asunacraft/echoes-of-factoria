package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

        crackedOreItem(EOFItems.CRACKED_RAW_SPHALERITE);
        crackedOreItem(EOFItems.CRACKED_RAW_CASSITERITE);
        crackedOreItem(EOFItems.CRACKED_RAW_ACANTHITE);
        crackedOreItem(EOFItems.CRACKED_RAW_WOLFRAMITE);
        crackedOreItem(EOFItems.CRACKED_RAW_RUTILE);
        crackedOreItem(EOFItems.CRACKED_RAW_CHROMITE);
        crackedOreItem(EOFItems.CRACKED_RAW_GALENA);
        crackedOreItem(EOFItems.CRACKED_RAW_PYRITE);

        basicItem(EOFItems.ZINC_DUST, "dust");
        basicItem(EOFItems.TIN_DUST, "dust");
        basicItem(EOFItems.SILVER_DUST, "dust");
        basicItem(EOFItems.TUNGSTEN_DUST, "dust");
        basicItem(EOFItems.TITANIUM_DUST, "dust");
        basicItem(EOFItems.CHROMIUM_DUST, "dust");
        basicItem(EOFItems.LEAD_DUST, "dust");
        basicItem(EOFItems.IRON_DUST, "dust");

        basicItem(EOFItems.ZINC_INGOT, "ingot");
        basicItem(EOFItems.TIN_INGOT, "ingot");
        basicItem(EOFItems.SILVER_INGOT, "ingot");
        basicItem(EOFItems.TUNGSTEN_INGOT, "ingot");
        basicItem(EOFItems.TITANIUM_INGOT, "ingot");
        basicItem(EOFItems.CHROMIUM_INGOT, "ingot");
        basicItem(EOFItems.LEAD_INGOT, "ingot");
        basicItem(EOFItems.BRASS_INGOT, "ingot");
        basicItem(EOFItems.BRONZE_INGOT, "ingot");

        blockItem(EOFBlocks.CASING_ULV);
        blockItem(EOFBlocks.CASING_LV);
        blockItem(EOFBlocks.CASING_MV);
        blockItem(EOFBlocks.CASING_HV);
        blockItem(EOFBlocks.CASING_EV);
        blockItem(EOFBlocks.CASING_IV);

        blockItem(EOFBlocks.ELECTRIC_FURNACE);
        blockItem(EOFBlocks.ELECTRIC_CRUSHER);

        withExistingParent("vial", "item/generated")
            .texture("layer0", modLoc("item/vials/empty_vial"))
            .texture("layer1", modLoc("item/vials/vial_liquid"));
    }

    private void basicItem(RegistryObject<Item> item, String category) {
        String name = item.getId().getPath();
        ResourceLocation texture = modLoc("item/" + category + "/" + name);
        withExistingParent(name, "item/generated").texture("layer0", texture);
    }

    private void crackedOreItem(RegistryObject<Item> item) {
        String name = item.getId().getPath();
        withExistingParent(name, "item/generated")
                .texture("layer0", modLoc("item/cracked/stone_base"))
                .texture("layer1", modLoc("item/cracked/" + name + "_overlay"));
    }

    private void blockItem(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        withExistingParent(name, modLoc("block/" + name));
    }
}
