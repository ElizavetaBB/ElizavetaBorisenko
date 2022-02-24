package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.BasePageTest;
import com.epam.tc.hw2.Utils;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class IndexPageTest extends BasePageTest {

    @Test
    public void testIndexPage() {
        SoftAssertions softAssertions = new SoftAssertions();

        // 1. Open test site by URL
        openSite();

        // 2. Assert Browser title
        testBrowserTitle(softAssertions);

        // 3. Perform login
        performLogin();

        // 4. Assert Username is loggined
        testUsername(softAssertions);

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        testHeaderSection(softAssertions);

        // 6. Assert that there are 4 images on the Index Page and they are displayed
        testImagesDisplayed(softAssertions);

        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        testIconsText(softAssertions);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        testFrameButton(softAssertions);

        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        testLeftSection(softAssertions);

        softAssertions.assertAll();
    }

    private void testHeaderSection(SoftAssertions softAssertions) {
        List<WebElement> items = driver.findElements(By.cssSelector(".nav > li"));

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(Utils.headerItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(items)
                .extracting(WebElement::getText).as("Header items text")
                .containsExactlyElementsOf(Utils.headerItems);
    }

    private void testImagesDisplayed(SoftAssertions softAssertions) {
        List<WebElement> images = driver.findElements(By.className("benefit-icon"));

        softAssertions.assertThat(images).as("Images number").hasSize(Utils.IMAGES_NUMBER);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);
    }

    private void testIconsText(SoftAssertions softAssertions) {
        List<String> texts = driver.findElements(By.cssSelector(".benefit > span"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(Utils.iconsText);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(Utils.iconsText);
    }

    private void testFrameButton(SoftAssertions softAssertions) {
        WebElement frameButton = null;
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        for (WebElement frame : frames) {
            driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
            try {
                frameButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("frame-button")));
                if (frameButton != null) {
                    break;
                }
            } catch (TimeoutException timeoutException) {
                timeoutException.printStackTrace();
            } finally {
                driver.switchTo().defaultContent();
            }
        }

        softAssertions.assertThat(frameButton).isNotNull();
        softAssertions.assertThat(frameButton.getText()).isEqualTo(Utils.FRAME_BUTTON);
    }

    private void testLeftSection(SoftAssertions softAssertions) {
        List<WebElement> leftItems = driver.findElements(By.cssSelector(".sidebar-menu > li"));

        softAssertions.assertThat(leftItems).as("Left Section items number").hasSameSizeAs(Utils.leftSectionItems);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItems)
                .extracting(WebElement::getText).as("Left Sections texts")
                .containsExactlyElementsOf(Utils.leftSectionItems);
    }
}
