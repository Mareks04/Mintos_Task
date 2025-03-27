package utils;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;
    private static String DEFAULT_PROPERTIES = "app.properties";
    private static String CONFIG_PATH = "src" + File.separator
            + "test" + File.separator + "resources"
            + File.separator + "config" + File.separator;

    public static void getInstance() {
        properties = new Properties();
        try (InputStream fileInputStream = new FileInputStream(CONFIG_PATH  + DEFAULT_PROPERTIES)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + DEFAULT_PROPERTIES, e);
        }
    }

    public static String getProperty(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        } else if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return null;
    }
}
