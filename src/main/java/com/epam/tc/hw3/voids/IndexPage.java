package com.epam.tc.hw3.voids;

import com.epam.tc.hw3.base.BaseIndexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage extends BaseIndexPage {

    public IndexPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void switchToFrameWithButton() {
        baseSwitchToFrameWithButton();
    }

    public void switchToDefaultContent() {
        baseSwitchToDefaultContent();
    }

    public void openDifferentElementsPage() {
        baseOpenDifferentElementsPage();
    }

}
