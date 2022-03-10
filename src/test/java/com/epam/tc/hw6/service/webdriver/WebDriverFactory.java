package com.epam.tc.hw6.service.webdriver;

import com.epam.tc.hw6.service.exception.IllegalBrowserNameException;
import com.epam.tc.hw6.service.exception.WebDriverLaunchTypeException;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class WebDriverFactory {

    private static final String LOCAL_LAUNCH_TYPE = "local";
    private static final String REMOTE_LAUNCH_TYPE = "remote";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private WebDriverFactory() {}

    public static WebDriver createDriver(final String launchType, final String browser) {
        WebDriver driver;
        if (LOCAL_LAUNCH_TYPE.equalsIgnoreCase(launchType)) {
            driver = createLocalDriver(browser);
        } else if (REMOTE_LAUNCH_TYPE.equalsIgnoreCase(launchType)) {
            driver = createRemoteDriver(browser);
        } else {
            throw new WebDriverLaunchTypeException(String.format("Unsupported driver type: %s", launchType));
        }
        return driver;
    }

    private static WebDriver createLocalDriver(final String browser) {
        WebDriver driver;
        switch (browser.toLowerCase(Locale.ROOT)) {
            case CHROME:
                driver = createLocalChromeDriver();
                break;
            case FIREFOX:
                driver = createLocalFirefoxDriver();
                break;
            default:
                throw new IllegalBrowserNameException(String.format("Unsupported browser: %s", browser));
        }

        return driver;
    }

    private static WebDriver createLocalChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver createLocalFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver createRemoteDriver(final String browser) {
        Capabilities capabilities;
        switch (browser.toLowerCase(Locale.ROOT)) {
            case CHROME:
                capabilities = createRemoteChromeCapabilities();
                break;
            case FIREFOX:
                capabilities = createRemoteFirefoxCapabilities();
                break;
            default:
                throw new IllegalBrowserNameException(String.format("Unsupported browser: %s", browser));
        }
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Capabilities createRemoteChromeCapabilities() {
        return new ChromeOptions();
    }

    private static Capabilities createRemoteFirefoxCapabilities() {
        return new FirefoxOptions();
    }
}
