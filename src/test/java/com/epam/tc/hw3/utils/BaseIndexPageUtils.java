package com.epam.tc.hw3.utils;

import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.ex1.IndexPageData;
import com.epam.tc.hw3.voids.IndexPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseIndexPageUtils extends BasePageUtils {

    public BaseIndexPageUtils(WebDriver driver, WebDriverWait wait,
                              PropertyReader propertyReader, SoftAssertions softAssertions) {
        super(driver, wait, propertyReader, softAssertions);
    }

    public void testIndexPage() {
        // 1. Open test site by URL
        // 2. Assert Browser title
        // 3. Perform login
        // 4. Assert User name in the left-top side of screen that user is loggined
        testSiteAndLogin();
        testHeaderSection();
        testImages();
        testTextsUnderImages();
        testFrameWithFrameButtonExistence();
        testLeftSection();
    }

    // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
    public void testHeaderSection() {
        List<WebElement> items = indexPage.header().getHeaderItems();
        List<String> itemsTexts = indexPage.header().getHeaderItemsTexts();
        List<String> expectedItems = IndexPageData.HEADER_ITEMS;

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(expectedItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(itemsTexts).as("Header items text")
                .containsExactlyElementsOf(expectedItems);
    }

    // 6. Assert that there are 4 images on the Index Page and they are displayed
    public void testImages() {
        List<WebElement> images = indexPage.getImages();
        softAssertions.assertThat(images).as("Images number").hasSize(IndexPageData.imagesNumber);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);
    }

    // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
    public void testTextsUnderImages() {
        List<String> texts = indexPage.getTextsUnderImages();
        List<String> expectedTexts = IndexPageData.ICONS_TEXT;
        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(expectedTexts);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(expectedTexts);
    }

    // 8. Assert that there is the iframe with “Frame Button” exist
    // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
    // 10. Switch to original window back
    public void testFrameWithFrameButtonExistence() {
        indexPage.switchToFrameWithButton();
        IndexPage indexPage1 = new IndexPage(this.driver, this.driverWait);
        WebElement button = indexPage1.getFrameButton();
        softAssertions.assertThat(button.getAttribute("value")).isEqualTo(IndexPageData.FRAME_BUTTON);
        indexPage1.switchToDefaultContent();
    }

    // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
    public void testLeftSection() {
        List<WebElement> leftItems = indexPage.leftMenu().getItems();
        List<String> leftItemsTitles = indexPage.leftMenu().getItemsTitles();
        List<String> leftItemsExpected = IndexPageData.LEFT_SECTION_ITEMS;
        softAssertions.assertThat(leftItems).as("Left Section items number")
                .hasSameSizeAs(leftItemsExpected);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItemsTitles).as("Left Sections texts")
                .containsExactlyElementsOf(leftItemsExpected);
    }
}
