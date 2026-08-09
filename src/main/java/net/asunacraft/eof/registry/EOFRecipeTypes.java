package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.recipe.CrushingRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EOFRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, EchoesOfFactoria.MODID);

    public static final RegistryObject<RecipeType<CrushingRecipe>> CRUSHING =
            RECIPE_TYPES.register("crushing", () -> RecipeType.simple(EchoesOfFactoria.id("crushing")));
}
