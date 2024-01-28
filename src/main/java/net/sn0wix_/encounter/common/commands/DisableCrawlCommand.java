package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.sn0wix_.encounter.common.keyMaps.ServerKeyHashMap;
import net.sn0wix_.encounter.common.networking.packets.s2c.PlayerDisableCrawlS2CPacket;

import java.util.Map;
import java.util.UUID;

public class DisableCrawlCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("disable_crawl").then(CommandManager.argument("disable", BoolArgumentType.bool()).executes(DisableCrawlCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        boolean disable = BoolArgumentType.getBool(serverCommandSourceCommandContext, "disable");

        if (disable) {
            serverCommandSourceCommandContext.getSource().getServer().getPlayerManager().getPlayerList().forEach(player -> {
                PlayerDisableCrawlS2CPacket.send(player, true);
            });

            for (Map.Entry<UUID, Boolean> entry : ServerKeyHashMap.CRAWL_KEYS.entrySet()) {
                entry.setValue(false);
            }

            serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.of("Successfully disabled crawl for all players."), false);
        } else {
            serverCommandSourceCommandContext.getSource().getServer().getPlayerManager().getPlayerList().forEach(player -> {
                PlayerDisableCrawlS2CPacket.send(player, false);
            });

            serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.of("Successfully enabled crawl for all players."), false);
        }

        return 0;
    }
}
