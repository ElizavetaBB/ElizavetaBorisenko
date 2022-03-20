package com.epam.tc.hw3.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginComponent extends AbstractBaseComponent {

    @FindBy(className = "profile-photo")
    private WebElement userIcon;

    @FindBy(id = "name")
    private WebElement loginInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "user-name")
    private WebElement username;

    public LoginComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String getDisplayedUsername() {
        return username.getText();
    }

}
