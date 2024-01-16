package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CustomHelpCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("help_custom").executes(CustomHelpCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.of("""
                Commandy:
                Zamknutí mezerníku: d/isable_jump <true/false>
                Zamknutí shiftu: /disable_shift <true/false>
                Náhodná generace: /random <šance v %>
                Zapnutí animace jumpscaru: /start_scare_anim_all
                Zapnutí animace peeku(pouze crawler): /start_crawler_peek_anim
                
                Custom NBT data: 
                Pro nastavení jiné pozice, než výchozí pro redstone block: {CustomKillPos:[I;x,y,-z]}
                """), false);

        return 1;
    }
}
