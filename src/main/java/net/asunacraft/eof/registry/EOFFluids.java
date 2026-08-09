package net.asunacraft.eof.registry;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.fluid.BaseFluidType;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EOFFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, EchoesOfFactoria.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, EchoesOfFactoria.MODID);
    public static final DeferredRegister<Block> LIQUID_BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EchoesOfFactoria.MODID);

    public static final RegistryObject<FluidType> BRINE_TYPE = fluidType("brine", 0xFFC9DEE9, 1150, 1600, 290);
    public static final RegistryObject<LiquidBlock> BRINE_BLOCK =
            liquidBlock("brine", () -> (FlowingFluid) EOFFluids.BRINE.get());
    public static final RegistryObject<Fluid> BRINE =
            FLUIDS.register("brine", () -> new ForgeFlowingFluid.Source(EOFFluids.BRINE_PROPERTIES));
    public static final RegistryObject<Fluid> BRINE_FLOWING =
            FLUIDS.register("brine_flowing", () -> new ForgeFlowingFluid.Flowing(EOFFluids.BRINE_PROPERTIES));
    private static final ForgeFlowingFluid.Properties BRINE_PROPERTIES =
            fluidProps(EOFFluids.BRINE_TYPE, EOFFluids.BRINE, EOFFluids.BRINE_FLOWING, EOFFluids.BRINE_BLOCK);

    public static final RegistryObject<FluidType> SODIUM_HYDROXIDE_TYPE =
            fluidType("sodium_hydroxide", 0xFFE8F2F8, 1330, 2500, 300);
    public static final RegistryObject<LiquidBlock> SODIUM_HYDROXIDE_BLOCK =
            liquidBlock("sodium_hydroxide", () -> (FlowingFluid) EOFFluids.SODIUM_HYDROXIDE.get());
    public static final RegistryObject<Fluid> SODIUM_HYDROXIDE =
            FLUIDS.register("sodium_hydroxide",
                    () -> new ForgeFlowingFluid.Source(EOFFluids.SODIUM_HYDROXIDE_PROPERTIES));
    public static final RegistryObject<Fluid> SODIUM_HYDROXIDE_FLOWING =
            FLUIDS.register("sodium_hydroxide_flowing",
                    () -> new ForgeFlowingFluid.Flowing(EOFFluids.SODIUM_HYDROXIDE_PROPERTIES));
    private static final ForgeFlowingFluid.Properties SODIUM_HYDROXIDE_PROPERTIES =
            fluidProps(EOFFluids.SODIUM_HYDROXIDE_TYPE, EOFFluids.SODIUM_HYDROXIDE, EOFFluids.SODIUM_HYDROXIDE_FLOWING,
                    EOFFluids.SODIUM_HYDROXIDE_BLOCK);

    public static final RegistryObject<FluidType> SULFURIC_ACID_TYPE =
            fluidType("sulfuric_acid", 0xFFF0E8BE, 1840, 25000, 300);
    public static final RegistryObject<LiquidBlock> SULFURIC_ACID_BLOCK =
            liquidBlock("sulfuric_acid", () -> (FlowingFluid) EOFFluids.SULFURIC_ACID.get());
    public static final RegistryObject<Fluid> SULFURIC_ACID =
            FLUIDS.register("sulfuric_acid",
                    () -> new ForgeFlowingFluid.Source(EOFFluids.SULFURIC_ACID_PROPERTIES));
    public static final RegistryObject<Fluid> SULFURIC_ACID_FLOWING =
            FLUIDS.register("sulfuric_acid_flowing",
                    () -> new ForgeFlowingFluid.Flowing(EOFFluids.SULFURIC_ACID_PROPERTIES));
    private static final ForgeFlowingFluid.Properties SULFURIC_ACID_PROPERTIES =
            fluidProps(EOFFluids.SULFURIC_ACID_TYPE, EOFFluids.SULFURIC_ACID, EOFFluids.SULFURIC_ACID_FLOWING,
                    EOFFluids.SULFURIC_ACID_BLOCK);

    public static final RegistryObject<FluidType> HYDROCHLORIC_ACID_TYPE =
            fluidType("hydrochloric_acid", 0xFFF4F0D0, 1190, 1200, 290);
    public static final RegistryObject<LiquidBlock> HYDROCHLORIC_ACID_BLOCK =
            liquidBlock("hydrochloric_acid", () -> (FlowingFluid) EOFFluids.HYDROCHLORIC_ACID.get());
    public static final RegistryObject<Fluid> HYDROCHLORIC_ACID =
            FLUIDS.register("hydrochloric_acid",
                    () -> new ForgeFlowingFluid.Source(EOFFluids.HYDROCHLORIC_ACID_PROPERTIES));
    public static final RegistryObject<Fluid> HYDROCHLORIC_ACID_FLOWING =
            FLUIDS.register("hydrochloric_acid_flowing",
                    () -> new ForgeFlowingFluid.Flowing(EOFFluids.HYDROCHLORIC_ACID_PROPERTIES));
    private static final ForgeFlowingFluid.Properties HYDROCHLORIC_ACID_PROPERTIES =
            fluidProps(EOFFluids.HYDROCHLORIC_ACID_TYPE, EOFFluids.HYDROCHLORIC_ACID, EOFFluids.HYDROCHLORIC_ACID_FLOWING,
                    EOFFluids.HYDROCHLORIC_ACID_BLOCK);

    private static RegistryObject<FluidType> fluidType(String name, int tint, int density, int viscosity,
            int temperature) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(
                FluidType.Properties.create()
                        .descriptionId("fluid.eof." + name)
                        .density(density)
                        .temperature(temperature)
                        .viscosity(viscosity),
                modLoc("block/fluids/" + name + "_still"),
                modLoc("block/fluids/" + name + "_flowing"),
                tint));
    }

    private static RegistryObject<LiquidBlock> liquidBlock(String name, Supplier<FlowingFluid> fluid) {
        return LIQUID_BLOCKS.register(name, () -> new LiquidBlock(fluid, BlockBehaviour.Properties.copy(Blocks.WATER)));
    }

    private static ForgeFlowingFluid.Properties fluidProps(RegistryObject<FluidType> type,
            Supplier<? extends Fluid> still, Supplier<? extends Fluid> flowing, Supplier<? extends LiquidBlock> block) {
        return new ForgeFlowingFluid.Properties(() -> type.get(), still, flowing)
                .block(block)
                .explosionResistance(100f)
                .slopeFindDistance(3)
                .levelDecreasePerBlock(1)
                .tickRate(20);
    }

    private static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(EchoesOfFactoria.MODID, path);
    }
}
