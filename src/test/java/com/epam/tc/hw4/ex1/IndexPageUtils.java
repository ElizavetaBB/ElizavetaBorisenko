package com.epam.tc.hw4.ex1;

import com.epam.tc.hw4.BasePageUtils;
import com.epam.tc.hw4.voids.IndexPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPageUtils extends BasePageUtils {

    public IndexPageUtils(WebDriver driver, WebDriverWait wait, IndexPage indexPage) {
        super(driver, wait, indexPage);
    }

    @Step("Test the number of the header items and theirs texts")
    public void headerItemsTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Expected number of the header items", String.valueOf(IndexPageData.headerItems.size()));
        Allure.addAttachment("Expected texts of the header items", String.valueOf(IndexPageData.headerItems));

        List<WebElement> items = indexPage.header().getHeaderItems();
        List<String> itemsTexts = indexPage.header().getHeaderItemsTexts();
        List<String> expectedItems = IndexPageData.headerItems;

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(expectedItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(itemsTexts).as("Header items text")
                .containsExactlyElementsOf(expectedItems);
    }

    @Step("Test the number of the images and their displaying")
    public void imageItemsTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Expected number of the images", String.valueOf(IndexPageData.imagesNumber));

        List<WebElement> images = indexPage.getImages();
        softAssertions.assertThat(images).as("Images number").hasSize(IndexPageData.imagesNumber);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);
    }

    @Step("Test the number and content of texts under the images")
    public void imagesItemsTextTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Expected number of texts", String.valueOf(IndexPageData.iconsText.size()));
        Allure.addAttachment("Content of the texts under the images", String.valueOf(IndexPageData.iconsText));

        List<String> texts = indexPage.getTextsUnderImages();
        List<String> expectedTexts = IndexPageData.iconsText;
        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(expectedTexts);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(expectedTexts);
    }

    @Step("Test the existence of the frame with Frame Button")
    public void iframeTest(SoftAssertions softAssertions) {
        indexPage.switchToFrameWithButton();
        IndexPage indexPage1 = new IndexPage(this.driver, this.driverWait);
        WebElement button = indexPage1.getFrameButton();
        softAssertions.assertThat(button.getAttribute("value")).isEqualTo(IndexPageData.frameButton);
        indexPage1.switchToDefaultContent();
    }

    @Step("Test the number of the Left Section Items and their texts")
    public void leftSectionItemsTest(SoftAssertions softAssertions) {
        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        Allure.addAttachment("Number of Left Section Items", String.valueOf(IndexPageData.leftSectionItems.size()));
        Allure.addAttachment("Texts of the Left Section items", String.valueOf(IndexPageData.leftSectionItems));

        List<WebElement> leftItems = indexPage.leftMenu().getItems();
        List<String> leftItemsTitles = indexPage.leftMenu().getItemsTitles();
        List<String> leftItemsExpected = IndexPageData.leftSectionItems;
        softAssertions.assertThat(leftItems).as("Left Section items number")
                .hasSameSizeAs(leftItemsExpected);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItemsTitles).as("Left Sections texts")
                .containsExactlyElementsOf(leftItemsExpected);
    }

}