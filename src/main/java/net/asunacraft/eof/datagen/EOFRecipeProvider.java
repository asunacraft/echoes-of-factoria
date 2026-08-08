package net.asunacraft.eof.datagen;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class EOFRecipeProvider extends RecipeProvider {
    public EOFRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        smeltToIngot(writer, EOFItems.RAW_SPHALERITE, EOFItems.ZINC_INGOT);
        smeltToIngot(writer, EOFItems.RAW_CASSITERITE, EOFItems.TIN_INGOT);
        smeltToIngot(writer, EOFItems.RAW_ACANTHITE, EOFItems.SILVER_INGOT);
        smeltToIngot(writer, EOFItems.RAW_WOLFRAMITE, EOFItems.TUNGSTEN_INGOT);
        smeltToIngot(writer, EOFItems.RAW_RUTILE, EOFItems.TITANIUM_INGOT);
        smeltToIngot(writer, EOFItems.RAW_CHROMITE, EOFItems.CHROMIUM_INGOT);
        smeltToIngot(writer, EOFItems.RAW_GALENA, EOFItems.LEAD_INGOT);
        smeltToIngot(writer, EOFItems.RAW_PYRITE, Items.IRON_INGOT);

        alloy(writer, EOFItems.BRASS_INGOT, Items.COPPER_INGOT, EOFItems.ZINC_INGOT, "zinc");
        alloy(writer, EOFItems.BRONZE_INGOT, Items.COPPER_INGOT, EOFItems.TIN_INGOT, "tin");
    }

    private void smeltToIngot(Consumer<FinishedRecipe> writer, RegistryObject<Item> raw, RegistryObject<Item> ingot) {
        smeltToIngot(writer, raw, ingot.get());
    }

    private void smeltToIngot(Consumer<FinishedRecipe> writer, RegistryObject<Item> raw, Item ingot) {
        String rawName = raw.getId().getPath();
        String ingotName = ForgeRegistries.ITEMS.getKey(ingot).getPath();

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(raw.get()), RecipeCategory.MISC, ingot, 0.7f, 200)
                .unlockedBy("has_" + rawName, has(raw.get()))
                .save(writer, EchoesOfFactoria.MODID + ":" + ingotName + "_from_smelting_" + rawName);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(raw.get()), RecipeCategory.MISC, ingot, 0.7f, 100)
                .unlockedBy("has_" + rawName, has(raw.get()))
                .save(writer, EchoesOfFactoria.MODID + ":" + ingotName + "_from_blasting_" + rawName);
    }

    private void alloy(Consumer<FinishedRecipe> writer, RegistryObject<Item> result,
                       Item base, RegistryObject<Item> alloyWith, String alloyName) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result.get())
                .requires(Ingredient.of(base))
                .requires(Ingredient.of(alloyWith.get()))
                .unlockedBy("has_" + alloyName, has(alloyWith.get()))
                .save(writer);
    }
}
