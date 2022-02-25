package com.epam.tc.hw2;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public BaseUtils(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.driver = webDriver;
        this.driverWait = webDriverWait;
    }

    public void openSite() {
        driver.get(BaseData.PAGE_URL);
    }

    public void testBrowserTitle(SoftAssertions softAssertions) {
        softAssertions.assertThat(driver.getTitle()).as("Browser Title").isEqualTo(BaseData.HOME_PAGE_TITLE);
    }

    public void performLogin() {
        WebElement photo = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("profile-photo")));
        photo.click();

        WebElement login = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        login.sendKeys(BaseData.LOGIN);

        WebElement password = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys(BaseData.PASSWORD);

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();
    }

    public void testUsername(SoftAssertions softAssertions) {
        String userName = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).getText();
        softAssertions.assertThat(userName).as("User name").isEqualTo(BaseData.USERNAME);
    }

}
