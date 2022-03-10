package com.epam.tc.hw2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public PropertyReader(final String path) {
        try (FileReader fileReader = new FileReader(path)) {
            properties = new Properties();
            properties.load(fileReader);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
