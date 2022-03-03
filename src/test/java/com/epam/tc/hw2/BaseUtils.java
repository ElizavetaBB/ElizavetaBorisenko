package com.epam.tc.hw2;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected PropertyReader propertyReader;

    public BaseUtils(WebDriver webDriver, WebDriverWait webDriverWait, PropertyReader propertyReader) {
        this.driver = webDriver;
        this.driverWait = webDriverWait;
        this.propertyReader = propertyReader;
    }

    public void openSite() {
        driver.get(propertyReader.getProperty("page_url"));
    }

    public void testBrowserTitle(SoftAssertions softAssertions) {
        softAssertions.assertThat(driver.getTitle()).as("Browser Title")
                .isEqualTo(propertyReader.getProperty("home_page_title"));
    }

    public void performLogin() {
        WebElement photo = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("profile-photo")));
        photo.click();

        WebElement login = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        login.sendKeys(propertyReader.getProperty("login"));

        WebElement password = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys(propertyReader.getProperty("password"));

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();
    }

    public void testUsername(SoftAssertions softAssertions) {
        String userName = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).getText();
        softAssertions.assertThat(userName).as("User name")
                .isEqualTo(propertyReader.getProperty("username"));
    }

}
