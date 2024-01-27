package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CustomHelpCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("help_custom").executes(CustomHelpCommand::run));
    }

    public static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        Text text = Text.of("""
                Commandy:
                Zamknutí mezerníku: /disable_jump <true/false>
                Zamknutí shiftu: /disable_shift <true/false>
                Povolení renderivání boss baru: /render_boss_bar <true/false>
                Náhodná generace: /random <šance v %>
                Zapnutí animace jumpscaru: /start_scare_anim_all
                Zapnutí animace peeku(pouze crawler): /start_crawler_peek_anim
                Zapnutí animace wake up(pouze stalker): /start_stalker_wake_up_anim

                                
                Custom NBT data:\s
                Pro nastavení jiné pozice, než výchozí pro redstone block: {CustomKillPos:[I;x,y,z]}
                """);


        if (serverCommandSourceCommandContext.getSource().getEntity() instanceof PlayerEntity player) {
            player.sendMessage(text, false);
        } else {
            serverCommandSourceCommandContext.getSource().sendFeedback(() -> text,false);
        }
        return 1;
    }
}
