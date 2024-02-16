package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
            LOGGER.info("Application properties loaded successfully.");
        }
        catch (Exception e) {
            LOGGER.error("Could not load application properties.", e);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
