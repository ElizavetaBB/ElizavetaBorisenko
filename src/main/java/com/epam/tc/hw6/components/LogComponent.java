package com.epam.tc.hw6.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogComponent extends AbstractBaseComponent {

    @FindBy(css = ".panel-body-list.logs > li")
    private List<WebElement> logRows;

    public LogComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<String> getLogRowsByMatchString(String row) {
        return logRows
                .stream()
                .map(WebElement::getText)
                .filter(elem -> elem.matches(row))
                .collect(Collectors.toList());
    }

}
