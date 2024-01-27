package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.sn0wix_.encounter.common.entity.custom.JumpscaringEntity;

public class StartScareAnimCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("start_scare_anim_all").executes(StartScareAnimCommand::run));
    }

    public static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        serverCommandSourceCommandContext.getSource().getWorld().iterateEntities().forEach(entity -> {
            if (entity instanceof JumpscaringEntity jumpscaringEntity) {
                jumpscaringEntity.startScareAnim();
            }
        });

        return 0;
    }
}
