package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.BaseData;
import com.epam.tc.hw3.BasePageTest;
import com.epam.tc.hw3.components.LogComponent;
import com.epam.tc.hw3.components.LoginComponent;
import com.epam.tc.hw3.fluent.DifferentElementsFluentPage;
import com.epam.tc.hw3.fluent.IndexFluentPage;
import com.epam.tc.hw3.voids.DifferentElementsPage;
import com.epam.tc.hw3.voids.IndexPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends BasePageTest {

    @Test
    public void testDifferentElementsPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        IndexPage indexPage = new IndexPage(driver, wait);

        // 2. Assert Browser title
        softAssertions.assertThat(indexPage.getTitle()).isEqualTo(BaseData.HOME_PAGE_TITLE);

        // 3. Perform login
        LoginComponent loginComponent = indexPage.header().getLoginComponent();
        loginComponent.login(propertyReader.getProperty("login"), propertyReader.getProperty("password"));

        // 4. Assert User name in the left-top side of screen that user is loggined
        softAssertions.assertThat(loginComponent.getDisplayedUsername())
                .isEqualTo(propertyReader.getProperty("username"));

        // 5. Open through the header menu Service -> Different Elements Page
        indexPage.openDifferentElementsPage();
        DifferentElementsPage differentElementsPage = new DifferentElementsPage(driver, wait);
        String expectedPageTitle = DifferentElementsPageData.differentElementsPageTitle;

        softAssertions.assertThat(differentElementsPage.getTitle()).as("Different Elements Page Title")
                .isEqualTo(expectedPageTitle);

        // 6. Select checkboxes Water, Wind
        List<String> checkboxesNames = DifferentElementsPageData.checkboxesNames;
        differentElementsPage.clickCheckBoxes(checkboxesNames);

        List<WebElement> checkBoxes = differentElementsPage.getCheckboxesByName(checkboxesNames);
        softAssertions.assertThat(checkBoxes).as("Checkboxes selected").allSatisfy(WebElement::isSelected);

        // 7. Select radio Selen
        String radioButtonName = DifferentElementsPageData.radioButtonName;
        differentElementsPage.clickRadioButton(radioButtonName);

        WebElement radioButton = differentElementsPage.getRadioButtonByName(radioButtonName);
        softAssertions.assertThat(radioButton).as("Radio button selected").satisfies(WebElement::isSelected);

        // 8. Select in dropdown Yellow
        String dropdownName = DifferentElementsPageData.dropdownName;
        differentElementsPage.selectDropdown(dropdownName);

        softAssertions.assertThat(differentElementsPage.getSelectedDropdownText())
                .as("Dropdown selected").isEqualTo(dropdownName);

        // 9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        LogComponent logComponent = differentElementsPage.logComponent();

        List<String> checkboxesLog = DifferentElementsPageData.checkboxesLog;
        checkboxesLog.forEach(elem -> softAssertions.assertThat(
                logComponent.getLogRowsByMatchString(elem).size()).isNotZero());

        // 9. Assert that for radio button there is a log row
        // and value is corresponded to the status of radio button
        String radioLog = DifferentElementsPageData.radioLog;
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(radioLog).size()).isNotZero();

        // 9. Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        String dropdownLog = DifferentElementsPageData.dropdownLog;
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(dropdownLog).size()).isNotZero();

        softAssertions.assertAll();
    }

    @Test
    public void testDifferentElementsPageWithFluentPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        IndexFluentPage indexPage = new IndexFluentPage(driver, wait);

        // 2. Assert Browser title
        softAssertions.assertThat(indexPage.getTitle()).isEqualTo(BaseData.HOME_PAGE_TITLE);

        // 3. Perform login
        LoginComponent loginComponent = indexPage.header().getLoginComponent();
        loginComponent.login(propertyReader.getProperty("login"),
                propertyReader.getProperty("password"));

        // 4. Assert User name in the left-top side of screen that user is loggined
        softAssertions.assertThat(loginComponent.getDisplayedUsername())
                .isEqualTo(propertyReader.getProperty("username"));

        // 5. Open through the header menu Service -> Different Elements Page
        // DifferentElementsPage is opened by the method
        DifferentElementsFluentPage differentElementsPage = indexPage.openDifferentElementsPage();
        String expectedPageTitle = DifferentElementsPageData.differentElementsPageTitle;

        softAssertions.assertThat(differentElementsPage.getTitle()).as("Different Elements Page Title")
                .isEqualTo(expectedPageTitle);

        // 6. Select checkboxes Water, Wind
        // After clicking checkboxes DifferentElementsPage is returned from the method
        List<String> checkboxesNames = DifferentElementsPageData.checkboxesNames;

        List<WebElement> checkBoxes = differentElementsPage.clickCheckBoxes(checkboxesNames)
                .getCheckboxesByName(checkboxesNames);
        softAssertions.assertThat(checkBoxes).as("Checkboxes selected")
                .allSatisfy(WebElement::isSelected);

        // 7. Select radio Selen
        // After clicking on a radio button DifferentElementsPage is returned from the method
        String radioButtonName = DifferentElementsPageData.radioButtonName;

        WebElement radioButton = differentElementsPage.clickRadioButton(radioButtonName)
                .getRadioButtonByName(radioButtonName);
        softAssertions.assertThat(radioButton).as("Radio button selected")
                .satisfies(WebElement::isSelected);

        // 8. Select in dropdown Yellow
        // After selecting of a dropdown DifferentElementsPage is returned from the method
        String dropdownName = DifferentElementsPageData.dropdownName;

        softAssertions.assertThat(differentElementsPage.selectDropdown(dropdownName).getSelectedDropdownText())
                .as("Dropdown selected").isEqualTo(dropdownName);

        // 9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        LogComponent logComponent = differentElementsPage.logComponent();

        List<String> checkboxesLog = DifferentElementsPageData.checkboxesLog;
        checkboxesLog.forEach(elem -> softAssertions.assertThat(
                logComponent.getLogRowsByMatchString(elem).size()).isNotZero());

        // 9. Assert that for radio button there is a log row
        // and value is corresponded to the status of radio button
        String radioLog = DifferentElementsPageData.radioLog;
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(radioLog).size()).isNotZero();

        // 9. Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        String dropdownLog = DifferentElementsPageData.dropdownLog;
        softAssertions.assertThat(logComponent.getLogRowsByMatchString(dropdownLog).size()).isNotZero();

        softAssertions.assertAll();
    }
}
