package com.epam.tc.hw6.ex1;

import com.epam.tc.hw6.BasePageUtils;
import com.epam.tc.hw6.voids.IndexPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPageUtils extends BasePageUtils {

    public IndexPageUtils(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Test the number of the header items and theirs texts")
    public void headerItemsTest(SoftAssertions softAssertions, List<String> expectedHeaderItems) {
        Allure.addAttachment("Expected number of the header items", String.valueOf(expectedHeaderItems.size()));
        Allure.addAttachment("Expected texts of the header items", String.valueOf(expectedHeaderItems));

        List<WebElement> items = indexPage.header().getHeaderItems();
        List<String> itemsTexts = indexPage.header().getHeaderItemsTexts();

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(expectedHeaderItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(itemsTexts).as("Header items text")
                .containsExactlyElementsOf(expectedHeaderItems);
    }

    @Step("Test the number of the images and their displaying")
    public void imageItemsTest(SoftAssertions softAssertions, int imagesNumber) {
        Allure.addAttachment("Expected number of the images", String.valueOf(imagesNumber));

        List<WebElement> images = indexPage.getImages();
        softAssertions.assertThat(images).as("Images number").hasSize(imagesNumber);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);
    }

    @Step("Test the number and content of texts under the images")
    public void imagesItemsTextTest(SoftAssertions softAssertions, List<String> expectedIconsTexts) {
        Allure.addAttachment("Expected number of texts", String.valueOf(expectedIconsTexts.size()));
        Allure.addAttachment("Content of the texts under the images", String.valueOf(expectedIconsTexts));

        List<String> texts = indexPage.getTextsUnderImages();
        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(expectedIconsTexts);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(expectedIconsTexts);
    }

    @Step("Test the existence of the frame with button {frameButtonName}")
    public void iframeTest(SoftAssertions softAssertions, String frameButtonName) {
        indexPage.switchToFrameWithButton();
        IndexPage indexPage1 = new IndexPage(this.driver, this.driverWait);
        WebElement button = indexPage1.getFrameButton();
        softAssertions.assertThat(button.getAttribute("value")).isEqualTo(frameButtonName);
        indexPage1.switchToDefaultContent();
    }

    @Step("Test the number of the Left Section Items and their texts")
    public void leftSectionItemsTest(SoftAssertions softAssertions, List<String> expectedLeftSectionItems) {
        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        Allure.addAttachment("Number of Left Section Items", String.valueOf(expectedLeftSectionItems.size()));
        Allure.addAttachment("Texts of the Left Section items", String.valueOf(expectedLeftSectionItems));

        List<WebElement> leftItems = indexPage.leftMenu().getItems();
        List<String> leftItemsTitles = indexPage.leftMenu().getItemsTitles();
        softAssertions.assertThat(leftItems).as("Left Section items number")
                .hasSameSizeAs(expectedLeftSectionItems);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItemsTitles).as("Left Sections texts")
                .containsExactlyElementsOf(expectedLeftSectionItems);
    }

}
