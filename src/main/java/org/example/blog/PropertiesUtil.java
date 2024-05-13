package org.example.blog;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    private PropertiesUtil() {

    }

    private static final Properties PROPERTIES = new Properties();

    static {
        String propertiesFile = System.getProperty("propertiesFile");
        if (propertiesFile == null) {
            propertiesFile = "db.properties"; // По умолчанию используется db.properties
        }
        loadProperties(propertiesFile);
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties(String fileName) {
        try (var stream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            PROPERTIES.load(stream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
