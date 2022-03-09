package com.epam.tc.hw4.failed;

import com.epam.tc.hw4.ex1.IndexPageUtils;
import com.epam.tc.hw4.voids.IndexPage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPageFailedUtils extends IndexPageUtils {

    public IndexPageFailedUtils(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    @Override
    @Step("Test the existence of the frame with button {frameButtonName} with failure")
    public void iframeTest(SoftAssertions softAssertions, String frameButtonName) {
        indexPage.switchToFrameWithButton();
        IndexPage indexPage1 = new IndexPage(this.driver, this.driverWait);
        WebElement button = indexPage1.getFrameButton();
        softAssertions.assertThat(button.getAttribute("value")).isNotEqualTo(frameButtonName);
        indexPage1.switchToDefaultContent();
    }

}
