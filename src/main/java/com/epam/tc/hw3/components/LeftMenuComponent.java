package com.epam.tc.hw3.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftMenuComponent extends AbstractBaseComponent {

    @FindBy(css = ".sidebar-menu > li")
    private List<WebElement> items;

    public LeftMenuComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getItems() {
        return items;
    }

    public List<String> getItemsTitles() {
        return items
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
