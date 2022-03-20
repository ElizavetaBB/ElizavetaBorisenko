package com.epam.tc.hw3.base;

import com.epam.tc.hw3.components.LogComponent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDifferentElementsPage extends AbstractBasePage {

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxes;

    @FindBy(className = "label-radio")
    private List<WebElement> radioButtons;

    @FindBy(css = ".colors .uui-form-element")
    private WebElement dropdown;

    private Select select;

    private LogComponent logComponent;

    public BaseDifferentElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        logComponent = new LogComponent(this.driver, this.wait);
    }


    public List<WebElement> getCheckboxesByName(List<String> expected) {
        return checkBoxes
                .stream()
                .filter(elem -> expected.contains(elem.getText()))
                .collect(Collectors.toList());
    }

    public WebElement getRadioButtonByName(String expected) {
        Optional<WebElement> expectedRadioButton = radioButtons.stream()
                .filter(radioButton -> radioButton.getText().contains(expected))
                .findFirst();
        if (expectedRadioButton.isPresent()) {
            return expectedRadioButton.get();
        } else {
            throw new IllegalArgumentException("Expected radio button " + expected + " doesn't exist");
        }
    }

    public String getSelectedDropdownText() {
        return select.getFirstSelectedOption().getText();
    }

    public LogComponent logComponent() {
        return logComponent;
    }

    protected void baseClickCheckBoxes(List<String> names) {
        checkBoxes.forEach(elem -> {
            if (names.contains(elem.getText())) {
                elem.click();
            }
        });
    }

    protected void baseClickRadioButton(String name) {
        radioButtons.forEach(elem -> {
            if (elem.getText().contains(name)) {
                elem.click();
            }
        });
    }

    protected void baseSelectDropdown(String name) {
        select = new Select(dropdown);
        select.selectByVisibleText(name);
    }

}
