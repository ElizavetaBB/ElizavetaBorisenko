package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.BasePageTest;
import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.components.LoginComponent;
import com.epam.tc.hw3.fluent.IndexFluentPage;
import com.epam.tc.hw3.voids.IndexPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class IndexPageTest extends BasePageTest {

    @Test
    public void testIndexPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        PropertyReader propertyReader = new PropertyReader(IndexPageData.PROPERTIES_PATH);
        IndexPage indexPage = new IndexPage(driver, wait);

        // 1. Open test site by URL
        driver.navigate().to(propertyReader.getProperty("page_url"));

        // 2. Assert Browser title
        softAssertions.assertThat(indexPage.getTitle()).isEqualTo(
                propertyReader.getProperty("home_page_title"));

        // 3. Perform login
        LoginComponent loginComponent = indexPage.header().getLoginComponent();
        loginComponent.login(propertyReader.getProperty("login"), propertyReader.getProperty("password"));

        // 4. Assert User name in the left-top side of screen that user is loggined
        softAssertions.assertThat(loginComponent.getDisplayedUsername())
                .isEqualTo(propertyReader.getProperty("username"));

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> items = indexPage.header().getHeaderItems();
        List<String> itemsTexts = indexPage.header().getHeaderItemsTexts();
        List<String> expectedItems = IndexPageData.headerItems;

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(expectedItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(itemsTexts).as("Header items text")
                .containsExactlyElementsOf(expectedItems);

        // 6. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = indexPage.getImages();
        softAssertions.assertThat(images).as("Images number").hasSize(IndexPageData.imagesNumber);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);

        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<String> texts = indexPage.getTextsUnderImages();
        List<String> expectedTexts = IndexPageData.iconsText;
        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(expectedTexts);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(expectedTexts);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        indexPage.switchToFrameWithButton();
        IndexPage indexPage1 = new IndexPage(this.driver, this.wait);
        WebElement button = indexPage1.getFrameButton();
        softAssertions.assertThat(button.getAttribute("value")).isEqualTo(IndexPageData.frameButton);

        // 10. Switch to original window back
        indexPage1.switchToDefaultContent();

        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        List<WebElement> leftItems = indexPage.leftMenu().getItems();
        List<String> leftItemsTitles = indexPage.leftMenu().getItemsTitles();
        List<String> leftItemsExpected = IndexPageData.leftSectionItems;
        softAssertions.assertThat(leftItems).as("Left Section items number")
                .hasSameSizeAs(leftItemsExpected);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItemsTitles).as("Left Sections texts")
                .containsExactlyElementsOf(leftItemsExpected);

        softAssertions.assertAll();
    }

    @Test
    public void testIndexPageWithFluentPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        PropertyReader propertyReader = new PropertyReader(IndexPageData.PROPERTIES_PATH);
        IndexFluentPage indexPage = new IndexFluentPage(driver, wait);

        // 1. Open test site by URL
        driver.navigate().to(propertyReader.getProperty("page_url"));

        // 2. Assert Browser title
        softAssertions.assertThat(indexPage.getTitle()).isEqualTo(
                propertyReader.getProperty("home_page_title"));

        // 3. Perform login
        LoginComponent loginComponent = indexPage.header().getLoginComponent();
        loginComponent.login(propertyReader.getProperty("login"), propertyReader.getProperty("password"));

        // 4. Assert User name in the left-top side of screen that user is loggined
        softAssertions.assertThat(loginComponent.getDisplayedUsername())
                .isEqualTo(propertyReader.getProperty("username"));

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> items = indexPage.header().getHeaderItems();
        List<String> itemsTexts = indexPage.header().getHeaderItemsTexts();
        List<String> expectedItems = IndexPageData.headerItems;

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(expectedItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(itemsTexts).as("Header items text")
                .containsExactlyElementsOf(expectedItems);

        // 6. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> images = indexPage.getImages();
        softAssertions.assertThat(images).as("Images number").hasSize(IndexPageData.imagesNumber);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);

        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<String> texts = indexPage.getTextsUnderImages();
        List<String> expectedTexts = IndexPageData.iconsText;
        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(expectedTexts);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(expectedTexts);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // IndexPage is opened by the method
        softAssertions.assertThat(indexPage.switchToFrameWithButton().getFrameButton()
                .getAttribute("value")).isEqualTo(IndexPageData.frameButton);

        // 10. Switch to original window back
        indexPage.switchToDefaultContent();

        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        List<WebElement> leftItems = indexPage.leftMenu().getItems();
        List<String> leftItemsTitles = indexPage.leftMenu().getItemsTitles();
        List<String> leftItemsExpected = IndexPageData.leftSectionItems;
        softAssertions.assertThat(leftItems).as("Left Section items number")
                .hasSameSizeAs(leftItemsExpected);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItemsTitles).as("Left Sections texts")
                .containsExactlyElementsOf(leftItemsExpected);

        softAssertions.assertAll();
    }

}
