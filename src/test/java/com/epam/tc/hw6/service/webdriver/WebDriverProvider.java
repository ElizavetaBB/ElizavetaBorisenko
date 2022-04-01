package com.epam.tc.hw6.service.webdriver;

import java.util.Objects;
import org.openqa.selenium.WebDriver;

public final class WebDriverProvider {

    private static WebDriver driver;

    private WebDriverProvider() {}

    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            String launchType = System.getProperty("launchType", "local");
            String browserName = System.getProperty("browserName", "firefox");
            driver = WebDriverFactory.createDriver(launchType, browserName);
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
