package com.epam.tc.hw4;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private Properties propertyReader;

    public PropertyReader() {
        try (FileReader fileReader = new FileReader("src/test/resources/hw4/test.properties")) {
            propertyReader = new Properties();
            propertyReader.load(fileReader);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String getUserName() {
        return propertyReader.getProperty("username");
    }

    public String getPageUrl() {
        return propertyReader.getProperty("page_url");
    }

    public String getHomePageTitle() {
        return propertyReader.getProperty("home_page_title");
    }

    public String getLogin() {
        return propertyReader.getProperty("login");
    }

    public String getPassword() {
        return propertyReader.getProperty("password");
    }
}
