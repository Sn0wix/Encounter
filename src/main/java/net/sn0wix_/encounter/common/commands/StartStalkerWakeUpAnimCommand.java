package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.sn0wix_.encounter.common.entity.custom.StalkerEntity;

public class StartStalkerWakeUpAnimCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("start_stalker_wake_up_anim").executes(StartStalkerWakeUpAnimCommand::run));
    }

    public static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        serverCommandSourceCommandContext.getSource().getWorld().iterateEntities().forEach(entity -> {
            if (entity instanceof StalkerEntity stalker) {
                stalker.startWakeUpAnim();
            }
        });

        return 0;
    }
}
