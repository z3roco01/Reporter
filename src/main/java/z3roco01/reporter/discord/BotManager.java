package z3roco01.reporter.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import z3roco01.reporter.Reporter;

import java.util.EnumSet;

/**
 * Handles the JDA library
 */
public class BotManager {
    public static JDA bot = null;
    public static TextChannel channel = null;

    public static void create() {
        // create a bot that can just send messages
        bot = JDABuilder.createLight(Reporter.config.token,
                EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGE_TYPING))
                .addEventListeners(new BotEventListener())
                .build();

        createCommands();
        updateChannel(Reporter.config.channelId);
    }

    private static void createCommands() {
        CommandListUpdateAction commands = bot.updateCommands();

        commands.addCommands(Commands.slash("server", "Gives info on the server's current status")
                .setContexts(InteractionContextType.GUILD)
                .setDefaultPermissions(DefaultMemberPermissions.ENABLED));
        commands.addCommands(Commands.slash("setchannel", "Sets the channel that the bot will send messages in")
                .addOptions(new OptionData(OptionType.CHANNEL, "channel", "the channel to start messaging in")
                        .setRequired(true)
                )
                .setContexts(InteractionContextType.GUILD)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
        );
        commands.queue();
    }

    /**
     * Changes the channel only in this object, not the config file
     */
    public static void updateChannel(String id) {
        channel = bot.getTextChannelById(id);

        if(channel == null)
            Reporter.LOGGER.error("Discord channel is set wrong...");
    }

    /**
     * Sends the message in the set channel
     */
    public static void sendMessage(String message) {
        if(channel != null)
            channel.sendMessage(message).queue();
    }
}
