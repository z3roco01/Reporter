package z3roco01.reporter;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z3roco01.composed.file.ConfigFile;
import z3roco01.reporter.command.AdminCommands;
import z3roco01.reporter.discord.BotManager;

import java.io.IOException;

public class Reporter implements ModInitializer {
	public static final String MOD_ID = "reporter";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ReporterConfig config = new ReporterConfig();

	@Override
	public void onInitialize() {
		LOGGER.info("who up reporting it");

        try {
            ConfigFile.load("./config/reporter.conf", config);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        BotManager.create();

        CommandRegistrationCallback.EVENT.register(AdminCommands::register);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            BotManager.updateChannel(config.channelId);
            BotManager.sendMessage(config.startingMessage);
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            BotManager.sendMessage(config.stoppingMessage);
        });

    }

    /**
     * Easily sets the discord channel id in the config and bot and saves it
     */
    public static void setDiscordChannel(String id) {
        config.channelId = id;

        try {
            ConfigFile.store("./config/reporter.conf", config);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        BotManager.updateChannel(id);
    }
}