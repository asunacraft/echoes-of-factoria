package net.asunacraft.eof.recipe;

import net.asunacraft.eof.registry.EOFRecipeSerializers;
import net.asunacraft.eof.registry.EOFRecipeTypes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

/** Recipe for the electric crusher: one item in, one dust out. */
public class CrushingRecipe extends SingleItemRecipe {
    public CrushingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(EOFRecipeTypes.CRUSHING.get(), EOFRecipeSerializers.CRUSHING.get(), id, group, ingredient, result);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EOFRecipeSerializers.CRUSHING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return EOFRecipeTypes.CRUSHING.get();
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    public static class Serializer implements RecipeSerializer<CrushingRecipe> {
        @Override
        public CrushingRecipe fromJson(ResourceLocation id, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");
            Ingredient ingredient;
            if (GsonHelper.isArrayNode(json, "ingredient")) {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "ingredient"), false);
            } else {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"), false);
            }
            String resultItem = GsonHelper.getAsString(json, "result");
            int count = GsonHelper.getAsInt(json, "count");
            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(resultItem)), count);
            return new CrushingRecipe(id, group, ingredient, result);
        }

        @Nullable
        @Override
        public CrushingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            String group = buf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            return new CrushingRecipe(id, group, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CrushingRecipe recipe) {
            buf.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buf);
            buf.writeItem(recipe.result);
        }
    }
}
