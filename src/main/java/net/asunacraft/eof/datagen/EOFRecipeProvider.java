package net.asunacraft.eof.datagen;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class EOFRecipeProvider extends RecipeProvider {
    public EOFRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        smeltToIngot(writer, EOFItems.RAW_SPHALERITE, EOFItems.ZINC_INGOT);
        smeltToIngot(writer, EOFItems.RAW_TIN, EOFItems.TIN_INGOT);
        smeltToIngot(writer, EOFItems.RAW_SILVER, EOFItems.SILVER_INGOT);
    }

    private void smeltToIngot(Consumer<FinishedRecipe> writer, RegistryObject<Item> raw, RegistryObject<Item> ingot) {
        String rawName = raw.getId().getPath();
        String ingotName = ingot.getId().getPath();

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(raw.get()), RecipeCategory.MISC, ingot.get(), 0.7f, 200)
                .unlockedBy("has_" + rawName, has(raw.get()))
                .save(writer, EchoesOfFactoria.MODID + ":" + ingotName + "_from_smelting_" + rawName);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(raw.get()), RecipeCategory.MISC, ingot.get(), 0.7f, 100)
                .unlockedBy("has_" + rawName, has(raw.get()))
                .save(writer, EchoesOfFactoria.MODID + ":" + ingotName + "_from_blasting_" + rawName);
    }
}
