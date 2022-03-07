package com.epam.tc.hw4;

import com.epam.tc.hw4.components.LoginComponent;
import com.epam.tc.hw4.voids.IndexPage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageUtils {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected IndexPage indexPage;

    protected BasePageUtils(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.driver = webDriver;
        this.driverWait = webDriverWait;
        indexPage = new IndexPage(driver, driverWait);
    }

    @Step("Open Home Page with url {url}")
    public void openSite(final String url) {
        driver.get(url);
    }

    @Step("Test opened page expected title {expectedTitle}")
    public void homePageTitleTest(SoftAssertions softAssertions, final String expectedTitle) {
        softAssertions.assertThat(indexPage.getTitle()).isEqualTo(expectedTitle);
    }

    @Step("Perform login {login} and password {password}")
    public void performLogin(final String login, final String password) {
        LoginComponent loginComponent = indexPage.header().getLoginComponent();
        loginComponent.login(login, password);
    }

    @Step("Test user logged as {expectedUsername}")
    public void usernameTest(SoftAssertions softAssertions, String expectedUsername) {
        softAssertions.assertThat(indexPage.header().getLoginComponent().getDisplayedUsername())
                .isEqualTo(expectedUsername);
    }
}
