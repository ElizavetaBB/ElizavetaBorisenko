package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.BaseUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPageUtils extends BaseUtils {

    public IndexPageUtils(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    protected void testHeaderSection(SoftAssertions softAssertions) {
        List<WebElement> items = driver.findElements(By.cssSelector(".nav > li"));

        softAssertions.assertThat(items).as("Header items number").hasSameSizeAs(IndexPageData.headerItems);
        softAssertions.assertThat(items).as("Header items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(items)
                .extracting(WebElement::getText).as("Header items text")
                .containsExactlyElementsOf(IndexPageData.headerItems);
    }

    protected void testImagesDisplayed(SoftAssertions softAssertions) {
        List<WebElement> images = driver.findElements(By.className("benefit-icon"));

        softAssertions.assertThat(images).as("Images number").hasSize(IndexPageData.imagesNumber);
        softAssertions.assertThat(images).as("Images display").allSatisfy(WebElement::isDisplayed);
    }

    protected void testIconsText(SoftAssertions softAssertions) {
        List<String> texts = driver.findElements(By.cssSelector(".benefit > span"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        softAssertions.assertThat(texts).as("Number of texts under icons").hasSameSizeAs(IndexPageData.iconsText);
        softAssertions.assertThat(texts).as("Texts under icons").containsExactlyElementsOf(IndexPageData.iconsText);
    }

    protected void testFrameButton(SoftAssertions softAssertions) {
        WebElement frameButton = null;
        String frameButtonText = "";
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        for (WebElement frame : frames) {
            driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
            try {
                frameButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("frame-button")));
                if (frameButton != null) {
                    frameButtonText = frameButton.getAttribute("value");
                    break;
                }
            } catch (TimeoutException timeoutException) {
                timeoutException.printStackTrace();
            } finally {
                driver.switchTo().defaultContent();
            }
        }

        softAssertions.assertThat(frameButton).as("Frame Button exists").isNotNull();
        softAssertions.assertThat(frameButtonText).as("Frame Button text").isEqualTo(IndexPageData.frameButton);
    }

    protected void testLeftSection(SoftAssertions softAssertions) {
        List<WebElement> leftItems = driver.findElements(By.cssSelector(".sidebar-menu > li"));

        softAssertions.assertThat(leftItems).as("Left Section items number")
                .hasSameSizeAs(IndexPageData.leftSectionItems);
        softAssertions.assertThat(leftItems).as("Left Section items display").allSatisfy(WebElement::isDisplayed);
        softAssertions.assertThat(leftItems)
                .extracting(WebElement::getText).as("Left Sections texts")
                .containsExactlyElementsOf(IndexPageData.leftSectionItems);
    }
}
