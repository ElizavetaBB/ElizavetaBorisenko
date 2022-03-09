package com.epam.tc.hw5.voids;

import com.epam.tc.hw5.components.LogComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsPage extends AbstractBasePage {

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxes;

    @FindBy(className = "label-radio")
    private List<WebElement> radioButtons;

    @FindBy(css = ".colors .uui-form-element")
    private WebElement dropdown;

    private Select select;
    private LogComponent logComponent;

    public DifferentElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        logComponent = new LogComponent(this.driver, this.wait);
    }

    public void clickCheckBoxes(List<String> names) {
        checkBoxes.forEach(elem -> {
            if (names.contains(elem.getText())) {
                elem.click();
            }
        });
    }

    public List<WebElement> getCheckboxesByName(List<String> expected) {
        return checkBoxes
                .stream()
                .filter(elem -> expected.contains(elem.getText()))
                .collect(Collectors.toList());
    }

    public void clickRadioButton(String name) {
        radioButtons.forEach(elem -> {
            if (elem.getText().contains(name)) {
                elem.click();
            }
        });
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

    public void selectDropdown(String name) {
        select = new Select(dropdown);
        select.selectByVisibleText(name);
    }

    public String getSelectedDropdownText() {
        return select.getFirstSelectedOption().getText();
    }

    public LogComponent logComponent() {
        return logComponent;
    }

}
