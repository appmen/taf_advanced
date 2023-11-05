package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger LOG = LogManager.getRootLogger();
    private final Properties properties = new Properties();
    private final String filePath;

    public ConfigReader(String filePath) {
        this.filePath = filePath;
    }

    public Properties getProperties() {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            LOG.error("Properties were not loaded");
            throw new RuntimeException(e);
        }

        return properties;
    }
}
