package com.epam.tc.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BasePageTestInit {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssertions softAssertions;
    private static final int TIMEOUT_DURATION = 10;

    @BeforeClass
    protected void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_DURATION));
        softAssertions = new SoftAssertions();
        driver.manage().window().maximize();
    }

    @AfterMethod
    protected void tearDown() {
        // 12. Close Browser
        driver.quit();
    }

}
