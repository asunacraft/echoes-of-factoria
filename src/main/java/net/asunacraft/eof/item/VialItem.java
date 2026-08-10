package net.asunacraft.eof.item;

import net.asunacraft.eof.EchoesOfFactoria;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.registries.ForgeRegistries;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class VialItem extends Item {
    public static final int CAPACITY = 250;
    public static final int SIP_SIZE = 25;

    private static final String TAG_FLUID = "Fluid";

    public VialItem(Properties properties) {
        super(properties);
    }

    public static int getCapacity() {
        return CAPACITY;
    }

    public static boolean hasFluid(ItemStack stack) {
        return !getFluid(stack).isEmpty();
    }

    public static FluidStack getFluid(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_FLUID)) {
            return FluidStack.loadFluidStackFromNBT(tag.getCompound(TAG_FLUID));
        }
        return FluidStack.EMPTY;
    }

    public static void setFluid(ItemStack stack, FluidStack fluid) {
        if (fluid.isEmpty()) {
            if (stack.hasTag()) {
                stack.getTag().remove(TAG_FLUID);
            }
            return;
        }
        CompoundTag tag = stack.getOrCreateTag();
        tag.put(TAG_FLUID, fluid.writeToNBT(new CompoundTag()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!hasFluid(stack)) {
            return InteractionResultHolder.pass(stack);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        FluidStack fluid = getFluid(stack);
        if (!fluid.isEmpty()) {
            if (!level.isClientSide && entity instanceof Player player) {
                applyDrinkEffects(player, fluid);
            }
            setFluid(stack, new FluidStack(fluid.getFluid(), fluid.getAmount() - Math.min(SIP_SIZE, fluid.getAmount())));
        }
        if (entity instanceof Player player) {
            player.playSound(SoundEvents.GENERIC_DRINK, 0.5f, level.getRandom().nextFloat() * 0.1f + 0.9f);
        }
        return stack;
    }

    private static void applyDrinkEffects(Player player, FluidStack fluid) {
        ResourceLocation key = ForgeRegistries.FLUIDS.getKey(fluid.getFluid());
        if (key == null || !EchoesOfFactoria.MODID.equals(key.getNamespace())) {
            return;
        }
        String name = key.getPath();
        if (name.endsWith("_flowing")) {
            name = name.substring(0, name.length() - "_flowing".length());
        }
        int damage = 0;
        switch (name) {
            case "brine" -> player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300));

            case "sodium_hydroxide" -> {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 300));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300));
                damage = 2;
            }
            
            case "hydrochloric_acid" -> {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 1));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 1));
                damage = 4;
            }
            
            case "sulfuric_acid" -> {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 400, 1));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 400, 1));
                damage = 6;
            }
            
            default -> {
                return;
            }
        }
        if (damage > 0) {
            player.hurt(player.damageSources().magic(), damage);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        FluidStack fluid = getFluid(stack);
        if (!fluid.isEmpty()) {
            tooltip.add(Component.translatable(
                "item.eof.vial.contents",
                fluid.getFluid().getFluidType().getDescription(fluid),
                fluid.getAmount()));
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new VialCapabilityProvider(stack);
    }

    public static class VialCapabilityProvider implements ICapabilityProvider {
        private final ItemStack stack;

        public VialCapabilityProvider(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (cap == ForgeCapabilities.FLUID_HANDLER_ITEM) {
                return LazyOptional.of(() -> new VialFluidHandler(stack)).cast();
            }
            return LazyOptional.empty();
        }
    }

    public static class VialFluidHandler implements IFluidHandlerItem {
        private final ItemStack container;

        public VialFluidHandler(ItemStack container) {
            this.container = container;
        }

        @Override
        public ItemStack getContainer() {
            return container;
        }

        @Override
        public int getTanks() {
            return 1;
        }

        @Override
        public FluidStack getFluidInTank(int tank) {
            return VialItem.getFluid(container);
        }

        @Override
        public int getTankCapacity(int tank) {
            return VialItem.getCapacity();
        }

        @Override
        public boolean isFluidValid(int tank, FluidStack stack) {
            return true;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            if (resource.isEmpty()) {
                return 0;
            }
            FluidStack existing = VialItem.getFluid(container);
            if (!existing.isEmpty() && !existing.isFluidEqual(resource)) {
                return 0;
            }
            int space = VialItem.getCapacity() - existing.getAmount();
            int filled = Math.min(space, resource.getAmount());
            if (filled > 0 && action.execute()) {
                VialItem.setFluid(container, new FluidStack(resource.getFluid(), existing.getAmount() + filled));
            }
            return filled;
        }

        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            FluidStack existing = VialItem.getFluid(container);
            if (existing.isEmpty()) {
                return FluidStack.EMPTY;
            }
            return drain(new FluidStack(existing.getFluid(), maxDrain), action);
        }

        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            FluidStack existing = VialItem.getFluid(container);
            if (resource.isEmpty() || existing.isEmpty() || !existing.isFluidEqual(resource)) {
                return FluidStack.EMPTY;
            }

            int drained = Math.min(existing.getAmount(), resource.getAmount());
            FluidStack result = existing.copy();
            result.setAmount(drained);
            
            if (action.execute()) {
                VialItem.setFluid(container, new FluidStack(existing.getFluid(), existing.getAmount() - drained));
            }
            
            return result;
        }
    }
}
