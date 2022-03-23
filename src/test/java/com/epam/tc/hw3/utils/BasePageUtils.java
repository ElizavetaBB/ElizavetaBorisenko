package com.epam.tc.hw3.utils;

import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.voids.IndexPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageUtils {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected PropertyReader propertyReader;
    protected SoftAssertions softAssertions;
    protected IndexPage indexPage;

    protected BasePageUtils(WebDriver webDriver, WebDriverWait webDriverWait,
                         PropertyReader propertyReader, SoftAssertions softAssertions) {
        this.driver = webDriver;
        this.driverWait = webDriverWait;
        this.propertyReader = propertyReader;
        this.softAssertions = softAssertions;
        indexPage = new IndexPage(this.driver, this.driverWait);
    }

    // 1. Open test site by URL
    protected void openSite() {
        driver.navigate().to(propertyReader.getProperty("page_url"));
    }

    // 2. Assert Browser title
    protected void testBrowserTitle() {
        softAssertions.assertThat(indexPage.getTitle()).isEqualTo(
                propertyReader.getProperty("home_page_title"));
    }

    // 3. Perform login
    protected void performLogin() {
        indexPage.header().getLoginComponent()
                .login(propertyReader.getProperty("login"), propertyReader.getProperty("password"));
    }

    // 4. Assert User name in the left-top side of screen that user is loggined
    protected void testUsername() {
        softAssertions.assertThat(indexPage.header().getLoginComponent().getDisplayedUsername())
                .isEqualTo(propertyReader.getProperty("username"));
    }

    protected void testSiteAndLogin() {
        openSite();
        testBrowserTitle();
        performLogin();
        testUsername();
    }

}
