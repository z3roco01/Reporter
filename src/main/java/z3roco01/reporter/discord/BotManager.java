package z3roco01.reporter.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import z3roco01.reporter.Reporter;

import java.util.EnumSet;

/**
 * Handles the JDA library
 */
public class BotManager {
    public static JDA bot = null;

    public static void register() {
        // create a bot that can just send messages
        bot = JDABuilder.createLight(Reporter.config.token,
                EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGE_REACTIONS))
                .addEventListeners(new BotEventListener())
                .build();
    }
}
