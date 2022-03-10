package com.epam.tc.hw6.voids;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage extends AbstractBasePage {

    @FindBy(className = "benefit-icon")
    private List<WebElement> images;

    @FindBy(css = ".benefit > span")
    private List<WebElement> imagesTexts;

    @FindBy(tagName = "iframe")
    private List<WebElement> frames;

    @FindBy(id = "frame-button")
    private WebElement frameButton;

    public IndexPage(WebDriver driver, WebDriverWait wait) {
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

    public void switchToFrame(WebElement frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void switchToFrameWithButton() {
        for (WebElement frame : frames) {
            switchToFrame(frame);
            IndexPage indexPage = new IndexPage(this.driver, this.wait);
            if (indexPage.frameButton != null) {
                break;
            }
            switchToDefaultContent();
        }
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public WebElement getFrameButton() {
        return frameButton;
    }

    public void openDifferentElementsPage() {
        header().openDifferentElementsPage();
    }

}

