package com.epam.tc.hw4.fluent;

import com.epam.tc.hw4.AbstractBasePage;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexFluentPage extends AbstractBasePage {

    @FindBy(className = "benefit-icon")
    private List<WebElement> images;

    @FindBy(css = ".benefit > span")
    private List<WebElement> imagesTexts;

    @FindBy(tagName = "iframe")
    private List<WebElement> frames;

    @FindBy(id = "frame-button")
    private WebElement frameButton;

    public IndexFluentPage(WebDriver driver, WebDriverWait wait) {
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

    public IndexFluentPage switchToFrame(WebElement frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
        return new IndexFluentPage(this.driver, this.wait);
    }

    public IndexFluentPage switchToFrameWithButton() {
        for (WebElement frame : frames) {
            IndexFluentPage indexPage = switchToFrame(frame);
            if (indexPage.frameButton != null) {
                break;
            }
            switchToDefaultContent();
        }
        return new IndexFluentPage(this.driver, this.wait);
    }

    public IndexFluentPage switchToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }

    public WebElement getFrameButton() {
        return frameButton;
    }

    public DifferentElementsFluentPage openDifferentElementsPage() {
        header().openDifferentElementsPage();
        return new DifferentElementsFluentPage(this.driver, this.wait);
    }

}
