package net.asunacraft.eof.datagen;

import net.asunacraft.eof.registry.EOFRecipeSerializers;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/** Builder for {@code eof:crushing} recipes written by datagen. */
public class CrushingRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStack result;
    private String group = "";

    private CrushingRecipeBuilder(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
    }

    public static CrushingRecipeBuilder crushing(Ingredient ingredient, ItemStack result) {
        return new CrushingRecipeBuilder(ingredient, result);
    }

    public static CrushingRecipeBuilder crushing(RegistryObject<Item> input, RegistryObject<Item> output, int count) {
        return crushing(Ingredient.of(input.get()), new ItemStack(output.get(), count));
    }

    @Override
    public CrushingRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterion) {
        return this;
    }

    @Override
    public CrushingRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> writer) {
        save(writer, RecipeBuilder.getDefaultRecipeId(result.getItem()));
    }

    @Override
    public void save(Consumer<FinishedRecipe> writer, ResourceLocation id) {
        writer.accept(new Result(id, group, ingredient, result));
    }

    private record Result(ResourceLocation id, String group, Ingredient ingredient, ItemStack result)
            implements FinishedRecipe {
        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!group.isEmpty()) {
                json.addProperty("group", group);
            }
            json.add("ingredient", ingredient.toJson());
            json.addProperty("result",
                    ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
            json.addProperty("count", result.getCount());
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return EOFRecipeSerializers.CRUSHING.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
