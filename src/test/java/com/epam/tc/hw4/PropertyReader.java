package com.epam.tc.hw4;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private Properties property;

    public PropertyReader(final String path) {
        try (FileReader fileReader = new FileReader(path)) {
            property = new Properties();
            property.load(fileReader);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String getProperty(final String key) {
        return property.getProperty(key);
    }
}
