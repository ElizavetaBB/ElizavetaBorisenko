package com.epam.tc.hw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BasePageTest {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    private static final long TIMEOUT_DURATION = 10;

    @BeforeSuite
    protected void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void setUp() {
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_DURATION));
        driver.manage().window().maximize();
    }

    @AfterMethod
    protected void tearDown() {
        driver.quit();
    }

}
