package com.epam.tc.hw4;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BasePageTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT_DURATION = 10;

    @BeforeClass
    protected void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_DURATION));
        driver.manage().window().maximize();
    }

    @AfterMethod
    protected void tearDown() {
        // 12. Close Browser
        driver.quit();
    }

}
