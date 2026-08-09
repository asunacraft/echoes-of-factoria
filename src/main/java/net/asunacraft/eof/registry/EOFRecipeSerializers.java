package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.recipe.CrushingRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOFRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EchoesOfFactoria.MODID);

    public static final RegistryObject<RecipeSerializer<CrushingRecipe>> CRUSHING =
            RECIPE_SERIALIZERS.register("crushing", CrushingRecipe.Serializer::new);
}
