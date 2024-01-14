package net.sn0wix_.encounter.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class RandomCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("random").then(CommandManager.argument("chance", IntegerArgumentType.integer(0, 100)).executes(RandomCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int chance = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "chance");
        Random random = new Random();
        int i = random.nextInt(101);

        if (chance >= i) {
            Vec3d vecPos = serverCommandSourceCommandContext.getSource().getPosition();

            serverCommandSourceCommandContext.getSource().getWorld().setBlockState(new BlockPos((int) vecPos.x, (int) vecPos.y + 2, (int) vecPos.z), Blocks.REDSTONE_BLOCK.getDefaultState());
        }

        return 0;
    }
}
