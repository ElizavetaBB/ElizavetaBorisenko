package com.epam.tc.hw3.base;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseIndexPage extends AbstractBasePage {

    @FindBy(className = "benefit-icon")
    private List<WebElement> images;

    @FindBy(css = ".benefit > span")
    private List<WebElement> imagesTexts;

    @FindBy(tagName = "iframe")
    private List<WebElement> frames;

    @FindBy(id = "frame-button")
    private WebElement frameButton;

    public BaseIndexPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getImages() {
        return images;
    }

    public List<String> getTextsUnderImages() {
        return imagesTexts
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    protected void baseSwitchToFrame(WebElement frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    protected void baseSwitchToFrameWithButton() {
        for (WebElement frame : frames) {
            baseSwitchToFrame(frame);
            BaseIndexPage indexPage = new BaseIndexPage(this.driver, this.wait);
            if (indexPage.frameButton != null) {
                break;
            }
            baseSwitchToDefaultContent();
        }
    }

    protected void baseSwitchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public WebElement getFrameButton() {
        return frameButton;
    }

    protected void baseOpenDifferentElementsPage() {
        header().openDifferentElementsPage();
    }

}
