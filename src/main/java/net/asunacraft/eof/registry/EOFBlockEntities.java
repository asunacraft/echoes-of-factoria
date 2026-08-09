package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.block.entity.ElectricCrusherBlockEntity;
import net.asunacraft.eof.block.entity.ElectricFurnaceBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOFBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EchoesOfFactoria.MODID);

    public static final RegistryObject<BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE =
            BLOCK_ENTITIES.register("electric_furnace",
                    () -> BlockEntityType.Builder.of(ElectricFurnaceBlockEntity::new, EOFBlocks.ELECTRIC_FURNACE.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ElectricCrusherBlockEntity>> ELECTRIC_CRUSHER =
            BLOCK_ENTITIES.register("electric_crusher",
                    () -> BlockEntityType.Builder.of(ElectricCrusherBlockEntity::new, EOFBlocks.ELECTRIC_CRUSHER.get())
                            .build(null));
}
