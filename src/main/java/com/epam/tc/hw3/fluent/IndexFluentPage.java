package com.epam.tc.hw3.fluent;

import com.epam.tc.hw3.base.BaseIndexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexFluentPage extends BaseIndexPage {

    public IndexFluentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public IndexFluentPage switchToFrameWithButton() {
        baseSwitchToFrameWithButton();
        return this;
    }

    public DifferentElementsFluentPage openDifferentElementsPage() {
        baseOpenDifferentElementsPage();
        return new DifferentElementsFluentPage(this.driver, this.wait);
    }

}
