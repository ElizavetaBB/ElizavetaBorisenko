package com.epam.tc.hw6.ex2;

import com.epam.tc.hw6.BasePageUtils;
import com.epam.tc.hw6.components.LogComponent;
import com.epam.tc.hw6.voids.DifferentElementsPage;
import io.qameta.allure.Step;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsPageUtils extends BasePageUtils {

    private DifferentElementsPage differentElementsPage;

    public DifferentElementsPageUtils(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Open Different Elements Page")
    public void openDifferentElementsPage() {
        indexPage.openDifferentElementsPage();
        differentElementsPage = new DifferentElementsPage(driver, driverWait);
    }

    @Step("Test Different Elements Page with expected title {expectedTitle}")
    public void differentElementsPageTitleTest(SoftAssertions softAssertions, final String expectedTitle) {
        softAssertions.assertThat(differentElementsPage.getTitle()).as("Different Elements Page Title")
                .isEqualTo(expectedTitle);
    }

    @Step("Select checkboxes {checkboxes}")
    public void selectCheckboxes(List<String> checkboxes) {
        differentElementsPage.clickCheckBoxes(checkboxes);
    }

    @Step("Test checkboxes {checkboxes} are selected")
    public void selectedCheckboxesTest(SoftAssertions softAssertions, List<String> checkboxes) {
        List<WebElement> checkBoxes = differentElementsPage.getCheckboxesByName(checkboxes);
        softAssertions.assertThat(checkBoxes).as("Checkboxes selected").allSatisfy(WebElement::isSelected);
    }

    @Step("Select radio button {radioButtonName}")
    public void selectRadioButton(final String radioButtonName) {
        differentElementsPage.clickRadioButton(radioButtonName);
    }

    @Step("Test radioButton {radioButtonName} is selected")
    public void selectedRadioButtonTest(SoftAssertions softAssertions, String radioButtonName) {
        WebElement radioButton = differentElementsPage.getRadioButtonByName(radioButtonName);
        softAssertions.assertThat(radioButton).as("Radio button selected").satisfies(WebElement::isSelected);
    }

    @Step("Select dropdown {dropdownName}")
    public void selectDropdown(String dropdownName) {
        differentElementsPage.selectDropdown(dropdownName);
    }

    @Step("Test dropdown {dropdownName} is selected")
    public void selectedDropdownTest(SoftAssertions softAssertions, String dropdownName) {
        softAssertions.assertThat(differentElementsPage.getSelectedDropdownText())
                .as("Dropdown selected").isEqualTo(dropdownName);
    }

    @Step("Test checkboxes, radio button and dropdown are in log")
    public void allElementLogTest(SoftAssertions softAssertions, List<String> checkboxesLog,
                                  final String radioButtonLog, final String dropdownLog) {
        LogComponent logComponent = differentElementsPage.logComponent();

        checkboxesLog.forEach(elem -> softAssertions.assertThat(
                logComponent.getLogRowsByMatchString(elem).size()).isNotZero());
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(radioButtonLog).size()).isNotZero();
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(dropdownLog).size()).isNotZero();
    }

}
