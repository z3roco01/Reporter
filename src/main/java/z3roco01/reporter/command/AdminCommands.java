package z3roco01.reporter.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import z3roco01.reporter.discord.BotManager;

public class AdminCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("discordmessage")
                .requires(CommandManager.requirePermissionLevel(CommandManager.OWNERS_CHECK))
                .then(CommandManager.argument("message", StringArgumentType.greedyString())
                        .executes(ctx -> {
                            BotManager.sendMessage(StringArgumentType.getString(ctx, "message"));
                            return 1;
                        })));

    }
}
