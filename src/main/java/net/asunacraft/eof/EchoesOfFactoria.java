package net.asunacraft.eof;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.asunacraft.eof.datagen.DataGenerators;
import net.asunacraft.eof.registry.EOFBlockEntities;
import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFCreativeTabs;
import net.asunacraft.eof.registry.EOFFeatures;
import net.asunacraft.eof.registry.EOFFluids;
import net.asunacraft.eof.registry.EOFItems;
import net.asunacraft.eof.registry.EOFMenuTypes;
import net.asunacraft.eof.registry.EOFRecipeSerializers;
import net.asunacraft.eof.registry.EOFRecipeTypes;

@Mod(EchoesOfFactoria.MODID)
public class EchoesOfFactoria {
    public static final String MODID = "eof";
    public static final String MOD_NAME = "Echoes of Factoria";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public EchoesOfFactoria() {
        @SuppressWarnings("removal")
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LOGGER.info("Registering components for {}...", MOD_NAME);

        EOFBlocks.BLOCKS.register(modEventBus);
        EOFItems.ITEMS.register(modEventBus);
        EOFFluids.FLUID_TYPES.register(modEventBus);
        EOFFluids.FLUIDS.register(modEventBus);
        EOFFluids.LIQUID_BLOCKS.register(modEventBus);
        EOFBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        EOFMenuTypes.MENUS.register(modEventBus);
        EOFFeatures.FEATURES.register(modEventBus);
        EOFCreativeTabs.CREATIVE_TABS.register(modEventBus);
        EOFRecipeTypes.RECIPE_TYPES.register(modEventBus);
        EOFRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(DataGenerators::gatherData);

        LOGGER.info("Finished registering components for {}! :)", MOD_NAME);
    }
}