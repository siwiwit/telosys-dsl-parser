package org.telosys.tools.dsl.parser.utils;

import org.telosys.tools.dsl.parser.EntityParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    private static Properties properties;

    public static String getProperty(String propertyName) {
        if (properties == null) {
            Utils.loadPropertiesFile();
        }

        return properties.getProperty(propertyName);
    }

    private static void loadPropertiesFile() {
        properties = new Properties();
        try {
            InputStream propertiesStream = Utils.class.getResourceAsStream("/config.properties");
            properties.load(propertiesStream);
        } catch (FileNotFoundException e) {
            throw new EntityParserException("Error while loading the properties file : " + e.getMessage());
        } catch (IOException e) {
            throw new EntityParserException("Error while loading the properties file : " + e.getMessage());
        }
    }
}
