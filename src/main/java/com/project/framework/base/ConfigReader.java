package com.project.framework.base;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props;

    public static Properties loadProperties() {
        if (props == null) {
            props = new Properties();
            try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
                props.load(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    public static String get(String key) {
        return loadProperties().getProperty(key);
    }
}
