package com.epam.tc.hw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    protected void openSite() {
        driver.get(Utils.PAGE_URL);
    }

    protected void testBrowserTitle(SoftAssertions softAssertions) {
        softAssertions.assertThat(driver.getTitle()).as("Browser Title").isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    protected void performLogin() {
        WebElement photo = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("profile-photo")));
        photo.click();

        WebElement login = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        login.sendKeys(Utils.LOGIN);

        WebElement password = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys(Utils.PASSWORD);

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();
    }

    protected void testUsername(SoftAssertions softAssertions) {
        String userName = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).getText();
        softAssertions.assertThat(userName).as("User name").isEqualTo(Utils.USERNAME);
    }

}
