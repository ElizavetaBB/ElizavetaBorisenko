package com.epam.tc.hw3.fluent;

import com.epam.tc.hw3.AbstractBasePage;
import com.epam.tc.hw3.components.LogComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsFluentPage extends AbstractBasePage {

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxes;

    @FindBy(className = "label-radio")
    private List<WebElement> radioButtons;

    @FindBy(css = ".colors .uui-form-element")
    private WebElement dropdown;

    private Select select;
    private LogComponent logComponent;

    public DifferentElementsFluentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        logComponent = new LogComponent(this.driver, this.wait);
    }

    public DifferentElementsFluentPage clickCheckBoxes(List<String> names) {
        checkBoxes.forEach(elem -> {
            if (names.contains(elem.getText())) {
                elem.click();
            }
        });
        return this;
    }

    public List<WebElement> getCheckboxesByName(List<String> expected) {
        return checkBoxes
                .stream()
                .filter(elem -> expected.contains(elem.getText()))
                .collect(Collectors.toList());
    }

    public DifferentElementsFluentPage clickRadioButton(String name) {
        radioButtons.forEach(elem -> {
            if (elem.getText().contains(name)) {
                elem.click();
            }
        });
        return this;
    }

    public WebElement getRadioButtonByName(String expected) {
        int index = -1;
        for (WebElement radioButton : radioButtons) {
            if (radioButton.getText().contains(expected)) {
                index = radioButtons.indexOf(radioButton);
            }
        }
        return radioButtons.get(index);
    }

    public DifferentElementsFluentPage selectDropdown(String name) {
        select = new Select(dropdown);
        select.selectByVisibleText(name);
        return this;
    }

    public String getSelectedDropdownText() {
        return select.getFirstSelectedOption().getText();
    }

    public LogComponent logComponent() {
        return logComponent;
    }

}
