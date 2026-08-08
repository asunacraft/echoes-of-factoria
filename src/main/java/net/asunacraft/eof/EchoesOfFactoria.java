package net.asunacraft.eof;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.asunacraft.eof.datagen.DataGenerators;
import net.asunacraft.eof.registry.EOFBlocks;
import net.asunacraft.eof.registry.EOFCreativeTabs;
import net.asunacraft.eof.registry.EOFFeatures;
import net.asunacraft.eof.registry.EOFItems;

@Mod(EchoesOfFactoria.MODID)
public class EchoesOfFactoria {
    public static final String MODID = "eof";
    public static final String MOD_NAME = "Echoes of Factoria";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public EchoesOfFactoria() {
        @SuppressWarnings("removal")
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EOFBlocks.BLOCKS.register(modEventBus);
        EOFItems.ITEMS.register(modEventBus);
        EOFFeatures.FEATURES.register(modEventBus);
        EOFCreativeTabs.CREATIVE_TABS.register(modEventBus);

        modEventBus.addListener(DataGenerators::gatherData);

        EchoesOfFactoria.init();
    }

    private static void init() {
        LOGGER.info("Initializing {}... :)", MOD_NAME);
    }
}