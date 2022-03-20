package com.epam.tc.hw3.utils;

import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.components.LogComponent;
import com.epam.tc.hw3.ex2.DifferentElementsPageData;
import com.epam.tc.hw3.voids.DifferentElementsPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDifferentElementsPageUtils extends BasePageUtils {

    private DifferentElementsPage differentElementsPage;

    public BaseDifferentElementsPageUtils(WebDriver webDriver, WebDriverWait webDriverWait,
                                          PropertyReader propertyReader, SoftAssertions softAssertions) {
        super(webDriver, webDriverWait, propertyReader, softAssertions);
    }

    public void testDifferentElementsPage() {
        testSiteAndLogin();
        openDifferentElementsPageAndCheck();
        selectCheckboxesAndCheck();
        selectRadioButtonAndCheck();
        selectDropdownAndCheck();
        checkLogForAllElements();
    }

    protected String openDifferentElementsPageAndReturnTitle() {
        indexPage.openDifferentElementsPage();
        differentElementsPage = new DifferentElementsPage(driver, driverWait);
        return differentElementsPage.getTitle();
    }

    // 5. Open through the header menu Service -> Different Elements Page
    protected void openDifferentElementsPageAndCheck() {
        String title = openDifferentElementsPageAndReturnTitle();
        String expectedPageTitle = DifferentElementsPageData.differentElementsPageTitle;

        softAssertions.assertThat(title).as("Different Elements Page Title")
                .isEqualTo(expectedPageTitle);
    }

    protected List<WebElement> clickAndReturnCheckboxes() {
        List<String> checkboxesNames = DifferentElementsPageData.checkboxesNames;
        differentElementsPage.clickCheckBoxes(checkboxesNames);

        return differentElementsPage.getCheckboxesByName(checkboxesNames);
    }

    // 6. Select checkboxes Water, Wind
    protected void selectCheckboxesAndCheck() {
        List<WebElement> checkBoxes = clickAndReturnCheckboxes();
        softAssertions.assertThat(checkBoxes).as("Checkboxes selected").allSatisfy(WebElement::isSelected);
    }

    protected WebElement clickAndReturnRadioButton() {
        String radioButtonName = DifferentElementsPageData.radioButtonName;
        differentElementsPage.clickRadioButton(radioButtonName);

        return differentElementsPage.getRadioButtonByName(radioButtonName);
    }

    // 7. Select radio Selen
    protected void selectRadioButtonAndCheck() {
        WebElement radioButton = clickAndReturnRadioButton();
        softAssertions.assertThat(radioButton).as("Radio button selected").satisfies(WebElement::isSelected);
    }

    // 8. Select in dropdown Yellow
    protected void selectDropdownAndCheck() {
        String dropdownName = DifferentElementsPageData.dropdownName;
        differentElementsPage.selectDropdown(dropdownName);

        softAssertions.assertThat(differentElementsPage.getSelectedDropdownText())
                .as("Dropdown selected").isEqualTo(dropdownName);
    }

    protected LogComponent getLogComponent() {
        return differentElementsPage.logComponent();
    }

    // 9. Assert that for each checkbox there is an individual log row
    // and value is corresponded to the status of checkbox
    // Assert that for radio button there is a log row
    // and value is corresponded to the status of radio button
    // Assert that for dropdown there is a log row
    // and value is corresponded to the selected value.
    protected void checkLogForAllElements() {
        LogComponent logComponent = getLogComponent();

        List<String> checkboxesLog = DifferentElementsPageData.checkboxesLog;
        checkboxesLog.forEach(elem -> softAssertions.assertThat(
                logComponent.getLogRowsByMatchString(elem).size()).isNotZero());

        String radioLog = DifferentElementsPageData.radioLog;
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(radioLog).size()).isNotZero();

        String dropdownLog = DifferentElementsPageData.dropdownLog;
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(dropdownLog).size()).isNotZero();
    }
}
