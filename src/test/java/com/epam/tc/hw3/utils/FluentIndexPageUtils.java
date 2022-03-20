package com.epam.tc.hw3.utils;

import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.ex1.IndexPageData;
import com.epam.tc.hw3.fluent.IndexFluentPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FluentIndexPageUtils extends BaseIndexPageUtils {

    private IndexFluentPage indexFluentPage;

    public FluentIndexPageUtils(WebDriver driver, WebDriverWait wait,
                                PropertyReader reader, SoftAssertions softAssertions) {
        super(driver, wait, reader, softAssertions);
        indexFluentPage = new IndexFluentPage(this.driver, this.driverWait);
    }

    // 8. Assert that there is the iframe with “Frame Button” exist
    // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
    // 10. Switch to original window back
    @Override
    public void testFrameWithFrameButtonExistence() {
        softAssertions.assertThat(indexFluentPage.switchToFrameWithButton().getFrameButton()
                .getAttribute("value")).isEqualTo(IndexPageData.FRAME_BUTTON);
        indexPage.switchToDefaultContent();
    }
}
