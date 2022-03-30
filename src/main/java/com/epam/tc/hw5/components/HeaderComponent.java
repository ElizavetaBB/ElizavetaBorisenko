package com.epam.tc.hw5.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderComponent extends AbstractBaseComponent {

    @FindBy(css = ".nav > li")
    private List<WebElement> headerItems;

    @FindBy(css = ".nav > li.dropdown")
    private WebElement serviceLink;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/*[contains(text(),'Different elements')]")
    private WebElement differentElementsPageLink;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/*[contains(text(),'User Table')]")
    private WebElement userTablePageLink;

    private LoginComponent loginComponent;

    public HeaderComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        loginComponent = new LoginComponent(this.driver, this.wait);
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public List<WebElement> getHeaderItems() {
        return headerItems;
    }

    public List<String> getHeaderItemsTexts() {
        return headerItems
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickServiceLink() {
        serviceLink.click();
    }

    public void openDifferentElementsPage() {
        differentElementsPageLink.click();
    }

    public void openUserTablePage() {
        userTablePageLink.click();
    }
}
