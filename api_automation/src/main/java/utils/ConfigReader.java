package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String DEFAULT_PROPERTIES = "config.properties";
    private static final String CONFIG_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH  + DEFAULT_PROPERTIES)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + DEFAULT_PROPERTIES, e);
        }
    }

    public String getProperty(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        } else if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return null;
    }
}
