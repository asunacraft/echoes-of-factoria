package net.asunacraft.eof.datagen;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class EOFRecipeProvider extends RecipeProvider {
    public EOFRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        crack(writer, EOFItems.RAW_SPHALERITE, EOFItems.CRACKED_RAW_SPHALERITE);
        crack(writer, EOFItems.RAW_CASSITERITE, EOFItems.CRACKED_RAW_CASSITERITE);
        crack(writer, EOFItems.RAW_ACANTHITE, EOFItems.CRACKED_RAW_ACANTHITE);
        crack(writer, EOFItems.RAW_WOLFRAMITE, EOFItems.CRACKED_RAW_WOLFRAMITE);
        crack(writer, EOFItems.RAW_RUTILE, EOFItems.CRACKED_RAW_RUTILE);
        crack(writer, EOFItems.RAW_CHROMITE, EOFItems.CRACKED_RAW_CHROMITE);
        crack(writer, EOFItems.RAW_GALENA, EOFItems.CRACKED_RAW_GALENA);
        crack(writer, EOFItems.RAW_PYRITE, EOFItems.CRACKED_RAW_PYRITE);

        crush(writer, EOFItems.RAW_SPHALERITE, EOFItems.ZINC_DUST);
        crush(writer, EOFItems.RAW_CASSITERITE, EOFItems.TIN_DUST);
        crush(writer, EOFItems.RAW_ACANTHITE, EOFItems.SILVER_DUST);
        crush(writer, EOFItems.RAW_WOLFRAMITE, EOFItems.TUNGSTEN_DUST);
        crush(writer, EOFItems.RAW_RUTILE, EOFItems.TITANIUM_DUST);
        crush(writer, EOFItems.RAW_CHROMITE, EOFItems.CHROMIUM_DUST);
        crush(writer, EOFItems.RAW_GALENA, EOFItems.LEAD_DUST);
        crush(writer, EOFItems.RAW_PYRITE, EOFItems.IRON_DUST);

        crush(writer, EOFItems.CRACKED_RAW_SPHALERITE, EOFItems.ZINC_DUST);
        crush(writer, EOFItems.CRACKED_RAW_CASSITERITE, EOFItems.TIN_DUST);
        crush(writer, EOFItems.CRACKED_RAW_ACANTHITE, EOFItems.SILVER_DUST);
        crush(writer, EOFItems.CRACKED_RAW_WOLFRAMITE, EOFItems.TUNGSTEN_DUST);
        crush(writer, EOFItems.CRACKED_RAW_RUTILE, EOFItems.TITANIUM_DUST);
        crush(writer, EOFItems.CRACKED_RAW_CHROMITE, EOFItems.CHROMIUM_DUST);
        crush(writer, EOFItems.CRACKED_RAW_GALENA, EOFItems.LEAD_DUST);
        crush(writer, EOFItems.CRACKED_RAW_PYRITE, EOFItems.IRON_DUST);

        alloy(writer, EOFItems.BRASS_INGOT, Items.COPPER_INGOT, EOFItems.ZINC_INGOT, "zinc");
        alloy(writer, EOFItems.BRONZE_INGOT, Items.COPPER_INGOT, EOFItems.TIN_INGOT, "tin");

        steamBoiler(writer);

        electricFurnace(writer);
        electricCrusher(writer);
    }

    private void electricFurnace(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EOFBlocks.ELECTRIC_FURNACE.get())
                .define('F', Items.FURNACE)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .pattern("III")
                .pattern("IRI")
                .pattern("IFI")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(writer);
    }

    private void electricCrusher(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EOFBlocks.ELECTRIC_CRUSHER.get())
                .define('C', EOFBlocks.CASING_ULV.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .pattern("III")
                .pattern("IRI")
                .pattern("ICI")
                .unlockedBy("has_casing", has(EOFBlocks.CASING_ULV.get()))
                .save(writer);
    }

    private void steamBoiler(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EOFBlocks.STEAM_BOILER.get())
                .define('C', EOFBlocks.CASING_LV.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .pattern("III")
                .pattern("ICI")
                .pattern("IRI")
                .unlockedBy("has_casing_lv", has(EOFBlocks.CASING_LV.get()))
                .save(writer);
    }

    /** Raw ore roasted in a regular furnace to loosen the rock matrix. */
    private void crack(Consumer<FinishedRecipe> writer, RegistryObject<Item> raw, RegistryObject<Item> cracked) {
        String rawName = raw.getId().getPath();
        String crackedName = cracked.getId().getPath();

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(raw.get()), RecipeCategory.MISC, cracked.get(), 0.1f, 200)
                .unlockedBy("has_" + rawName, has(raw.get()))
                .save(writer, EchoesOfFactoria.id(crackedName + "_from_smelting_" + rawName));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(raw.get()), RecipeCategory.MISC, cracked.get(), 0.1f, 100)
                .unlockedBy("has_" + rawName, has(raw.get()))
                .save(writer, EchoesOfFactoria.id(crackedName + "_from_blasting_" + rawName));
    }

    /** Electric crusher turns raw or cracked ore into metal dust. */
    private void crush(Consumer<FinishedRecipe> writer, RegistryObject<Item> input, RegistryObject<Item> dust) {
        String inputName = input.getId().getPath();
        CrushingRecipeBuilder.crushing(input, dust, 1)
                .unlockedBy("has_" + inputName, has(input.get()))
                .save(writer, EchoesOfFactoria.id("crushing/" + inputName + "_to_" + dust.getId().getPath()));
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
