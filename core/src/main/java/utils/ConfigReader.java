package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private static final Logger LOG = LogManager.getRootLogger();
    private final Properties properties = new Properties();
    private final String filePath;
    private final Map<String, String> config = new HashMap<>();

    public ConfigReader(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, String> getConfig() {
        updateProperties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            config.put((String) entry.getKey(), (String) entry.getValue());
        }
        return config;
    }

    private void updateProperties() {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            LOG.error("Properties were not loaded");
            throw new RuntimeException(e);
        }

    }


}
