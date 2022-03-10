package com.epam.tc.hw6;

import com.epam.tc.hw6.service.webdriver.WebDriverProvider;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BasePageTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT_DURATION = 10;

    @BeforeMethod(description = "Prepare Chrome WebDriver")
    protected void setUp(ITestContext context) {
        driver = WebDriverProvider.getDriver();
        context.setAttribute("driver", driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_DURATION));
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "Close browser")
    protected void tearDown() {
        // 12. Close Browser
        WebDriverProvider.closeDriver();
    }

}
