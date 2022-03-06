package com.epam.tc.hw4;

import com.epam.tc.hw4.components.HeaderComponent;
import com.epam.tc.hw4.components.LeftMenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected HeaderComponent header;
    protected LeftMenuComponent leftMenu;

    protected AbstractBasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
        header = new HeaderComponent(this.driver, this.wait);
        leftMenu = new LeftMenuComponent(this.driver, this.wait);
    }

    public HeaderComponent header() {
        return header;
    }

    public LeftMenuComponent leftMenu() {
        return leftMenu;
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
