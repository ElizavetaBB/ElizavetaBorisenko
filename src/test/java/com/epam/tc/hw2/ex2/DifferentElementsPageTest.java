package com.epam.tc.hw2.ex2;

import com.epam.tc.hw2.BasePageTest;
import com.epam.tc.hw2.Utils;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends BasePageTest {

    @Test
    public void testDifferentElementsPage() {
        SoftAssertions softAssertions = new SoftAssertions();

        // 1. Open test site by URL
        openSite();

        // 2. Assert Browser title
        testBrowserTitle(softAssertions);

        // 3. Perform login
        performLogin();

        // 4. Assert User name in the left-top side of screen that user is loggined
        testUsername(softAssertions);

        // 5. Open through the header menu Service -> Different Elements Page
        openDifferentElementsPage(softAssertions);

        // 6. Select checkboxes Water, Wind
        selectCheckboxes(softAssertions);

        // 7. Select radio Selen
        selectRadioButtons(softAssertions);

        // 8. Select in dropdown Yellow
        selectDropdown(softAssertions);

        // 9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        testCheckboxesLog(softAssertions);

        // 9. Assert that for radio button there is a log row
        // and value is corresponded to the status of radio button
        testRadioButtonLog(softAssertions);

        // 9. Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        testDropdownLog(softAssertions);

        softAssertions.assertAll();
    }

    private void openDifferentElementsPage(SoftAssertions softAssertions) {
        WebElement service = driver.findElement(By.cssSelector(".nav > li.dropdown"));
        service.click();

        WebElement link = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                String.format(
                        "//ul[@class=\"dropdown-menu\"]/li/*[contains(text(),\"%s\")]",
                        Utils.DIFFERENT_ELEMENTS_LINK_NAME))));
        link.click();

        softAssertions.assertThat(driver.getTitle()).as("Different Elements Page Title")
                .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_TITLE);
    }

    private void selectCheckboxes(SoftAssertions softAssertions) {
        List<WebElement> checkBoxes = driver.findElements((By.className("label-checkbox")))
                .stream()
                .filter(x -> Utils.CHECKBOXES_NAMES.contains(x.getText()))
                .collect(Collectors.toList());
        for (WebElement checkBox : checkBoxes) {
            checkBox.click();
        }

        softAssertions.assertThat(checkBoxes).as("Checkboxes selected").allSatisfy(WebElement::isSelected);
    }

    private void selectRadioButtons(SoftAssertions softAssertions) {
        WebElement radioButton = driver.findElement(By.xpath(
                String.format("//label[contains(., \"%s\")]", Utils.RADIOBUTTON_NAME)));
        radioButton.click();

        softAssertions.assertThat(radioButton).as("Radio button selected").satisfies(WebElement::isSelected);
    }

    private void selectDropdown(SoftAssertions softAssertions) {
        Select objSelect = new Select(driver.findElement(By.cssSelector(".colors .uui-form-element")));
        objSelect.selectByVisibleText(Utils.DROPDOWN_NAME);

        softAssertions.assertThat(objSelect.getFirstSelectedOption().getText())
                .as("Dropdown selected").isEqualTo(Utils.DROPDOWN_NAME);
    }

    private void testCheckboxesLog(SoftAssertions softAssertions) {
        for (int i = 0; i < Utils.CHECKBOXES_NAMES.size(); i++) {
            String checkBoxesLog = driver.findElement(By.xpath(
                    String.format("//ul[contains(@class,\"panel-body-list\")]/li[contains(.,\"%s\")]",
                                    Utils.CHECKBOXES_NAMES.get(i))))
                    .getText();

            softAssertions.assertThat(checkBoxesLog).as("Checkboxes log").matches(x -> {
                for (int j = 0; j < Utils.CHECKBOXES_NAMES.size(); j++) {
                    if (x.matches(Utils.CHECKBOXES_LOG.get(j))) {
                        return true;
                    }
                }
                return false;
            });
        }
    }

    private void testRadioButtonLog(SoftAssertions softAssertions) {
        String radioLog = driver.findElement(By.xpath(String.format(
                        "//ul[contains(@class,\"panel-body-list\")]/li[contains(.,\"%s\")]", Utils.RADIOBUTTON_NAME)
                )).getText();

        softAssertions.assertThat(radioLog).as("Radio button log")
                .matches(x -> x.matches(Utils.RADIO_LOG));
    }

    private void testDropdownLog(SoftAssertions softAssertions) {
        String dropdownLog = driver.findElement(By.xpath(String.format(
                        "//ul[contains(@class,\"panel-body-list\")]/li[contains(.,\"%s\")]", Utils.DROPDOWN_NAME)
                )).getText();

        softAssertions.assertThat(dropdownLog).as("Dropdown log")
                .matches(x -> x.matches(Utils.DROPDOWN_LOG));
    }
}
