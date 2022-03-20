package com.epam.tc.hw3.voids;

import com.epam.tc.hw3.base.BaseDifferentElementsPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsPage extends BaseDifferentElementsPage {

    public DifferentElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickCheckBoxes(List<String> names) {
        baseClickCheckBoxes(names);
    }

    public void clickRadioButton(String name) {
        baseClickRadioButton(name);
    }

    public void selectDropdown(String name) {
        baseSelectDropdown(name);
    }

}
