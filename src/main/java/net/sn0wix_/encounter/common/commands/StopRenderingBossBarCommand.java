package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.sn0wix_.encounter.common.networking.packets.s2c.BossBarRenderingS2CPacket;

public class StopRenderingBossBarCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("render_boss_bar").then(CommandManager.argument("allowRendering", BoolArgumentType.bool()).executes(StopRenderingBossBarCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        boolean allowRendering = BoolArgumentType.getBool(serverCommandSourceCommandContext, "allowRendering");
        serverCommandSourceCommandContext.getSource().getServer().getPlayerManager().getPlayerList().forEach(player -> BossBarRenderingS2CPacket.send(player, allowRendering));

        return 1;
    }
}
