package z3roco01.reporter;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z3roco01.composed.file.ConfigFile;

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

        LOGGER.info(config.token);
    }
}