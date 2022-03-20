package com.epam.tc.hw3.fluent;

import com.epam.tc.hw3.base.BaseDifferentElementsPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsFluentPage extends BaseDifferentElementsPage {

    public DifferentElementsFluentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public DifferentElementsFluentPage clickCheckBoxes(List<String> names) {
        baseClickCheckBoxes(names);
        return this;
    }

    public DifferentElementsFluentPage clickRadioButton(String name) {
        baseClickRadioButton(name);
        return this;
    }

    public DifferentElementsFluentPage selectDropdown(String name) {
        baseSelectDropdown(name);
        return this;
    }

}
