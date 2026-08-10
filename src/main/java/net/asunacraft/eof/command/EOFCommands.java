package net.asunacraft.eof.command;

import net.asunacraft.eof.EchoesOfFactoria;
import net.asunacraft.eof.block.entity.AbstractElectricMachineBlockEntity;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Dev commands. Usage:
 * {@code /eof debug energy fill} fills the machine you are looking at to
 * capacity; {@code /eof debug energy add <amount>} adds FE straight into its
 * buffer. Use {@code fill} for testing: a one-shot {@code add} may leave the
 * machine short of the energy needed to finish a job.
 */
@Mod.EventBusSubscriber(modid = EchoesOfFactoria.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class EOFCommands {
    private static final double REACH = 5.0D;

    private EOFCommands() {
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("eof")
                .then(Commands.literal("debug")
                        .then(Commands.literal("energy")
                                .then(Commands.literal("fill")
                                        .requires(source -> source.hasPermission(2))
                                        .executes(ctx -> fillEnergy(ctx)))
                                .then(Commands.literal("add")
                                        .requires(source -> source.hasPermission(2))
                                        .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                                .executes(ctx -> addEnergy(ctx,
                                                        IntegerArgumentType.getInteger(ctx, "amount"))))))));
    }

    private static int addEnergy(CommandContext<CommandSourceStack> ctx, int amount)
            throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        AbstractElectricMachineBlockEntity machine = lookAtMachine(source);
        if (machine == null) {
            return 0;
        }

        long added = machine.addDebugEnergy(amount);
        source.sendSuccess(() -> Component.literal("Added " + added + " FE (" + machine.getEnergyStored()
                + " / " + machine.getEnergyCapacity() + " FE)"), true);
        return 1;
    }

    private static int fillEnergy(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        AbstractElectricMachineBlockEntity machine = lookAtMachine(source);
        if (machine == null) {
            return 0;
        }

        long added = machine.fillDebugEnergy();
        source.sendSuccess(() -> Component.literal("Filled +" + added + " FE (" + machine.getEnergyStored()
                + " / " + machine.getEnergyCapacity() + " FE)"), true);
        return 1;
    }

    /** The electric machine the player is looking at, or null after a failure message. */
    private static AbstractElectricMachineBlockEntity lookAtMachine(CommandSourceStack source)
            throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        Level level = player.level();

        BlockPos pos = rayTarget(player);
        if (pos == null) {
            source.sendFailure(Component.literal("You are not looking at a block."));
            return null;
        }
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof AbstractElectricMachineBlockEntity machine)) {
            source.sendFailure(Component.literal("That is not an electric machine."));
            return null;
        }
        return machine;
    }

    /** Block the player is looking at, or null if they hit nothing. */
    private static BlockPos rayTarget(ServerPlayer player) {
        HitResult hit = player.level().clip(new ClipContext(
                player.getEyePosition(1.0F),
                player.getEyePosition(1.0F).add(player.getLookAngle().scale(REACH)),
                ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        return hit.getType() == HitResult.Type.BLOCK ? ((BlockHitResult) hit).getBlockPos() : null;
    }
}
