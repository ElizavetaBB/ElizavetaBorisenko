package com.epam.tc.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BasePageTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PropertyReader propertyReader;
    private static final int TIMEOUT_DURATION = 10;

    @BeforeSuite
    protected void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_DURATION));
        propertyReader = new PropertyReader(BaseData.PROPERTY_FILE_PATH);
        driver.manage().window().maximize();
        // 1. Open test site by URL
        driver.navigate().to(BaseData.PAGE_URL);
    }

    @AfterMethod
    protected void tearDown() {
        // 12. Close Browser
        driver.quit();
    }


}
