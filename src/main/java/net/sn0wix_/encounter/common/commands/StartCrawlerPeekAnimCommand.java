package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.sn0wix_.encounter.common.entity.custom.CrawlerEntity;

public class StartCrawlerPeekAnimCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("start_crawler_peek_anim").executes(StartCrawlerPeekAnimCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        serverCommandSourceCommandContext.getSource().getWorld().iterateEntities().forEach(entity -> {
            if (entity instanceof CrawlerEntity crawler) {
                crawler.startPeekAnim();
            }
        });

        return 0;
    }
}
