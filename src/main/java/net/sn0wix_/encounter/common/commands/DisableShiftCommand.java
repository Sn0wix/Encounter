package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.sn0wix_.encounter.common.networking.packets.s2c.PlayerDisableShiftS2CPacket;

public class DisableShiftCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("disable_shift").then(CommandManager.argument("disable", BoolArgumentType.bool()).executes(DisableShiftCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        boolean disable = BoolArgumentType.getBool(serverCommandSourceCommandContext, "disable");

        if (disable) {
            serverCommandSourceCommandContext.getSource().getServer().getPlayerManager().getPlayerList().forEach(player -> {
                PlayerDisableShiftS2CPacket.send(player, true);
            });

            serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.of("Successfully disabled shift for all players."), false);
        } else {
            serverCommandSourceCommandContext.getSource().getServer().getPlayerManager().getPlayerList().forEach(player -> {
                PlayerDisableShiftS2CPacket.send(player, false);
            });

            serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.of("Successfully enabled shift for all players."), false);
        }

        return 0;
    }
}
