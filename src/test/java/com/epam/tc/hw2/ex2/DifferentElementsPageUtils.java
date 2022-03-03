package com.epam.tc.hw2.ex2;

import static com.epam.tc.hw2.ex2.DifferentElementsPageData.checkboxesLog;
import static com.epam.tc.hw2.ex2.DifferentElementsPageData.checkboxesNames;
import static com.epam.tc.hw2.ex2.DifferentElementsPageData.radioButtonName;

import com.epam.tc.hw2.BaseUtils;
import com.epam.tc.hw2.PropertyReader;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsPageUtils extends BaseUtils {

    public DifferentElementsPageUtils(WebDriver webDriver, WebDriverWait webDriverWait, PropertyReader propertyReader) {
        super(webDriver, webDriverWait, propertyReader);
    }

    public void openDifferentElementsPage(SoftAssertions softAssertions) {
        WebElement service = driver.findElement(By.cssSelector(".nav > li.dropdown"));
        service.click();

        WebElement link = driverWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@class='dropdown-menu']/li/*[contains(text(),'Different elements')]")));
        link.click();

        softAssertions.assertThat(driver.getTitle()).as("Different Elements Page Title")
                .isEqualTo(DifferentElementsPageData.differentElementsPageTitle);
    }

    public void selectCheckboxes(SoftAssertions softAssertions) {
        List<WebElement> checkBoxes = driver.findElements((By.className("label-checkbox")))
                .stream()
                .filter(x -> checkboxesNames.contains(x.getText()))
                .collect(Collectors.toList());
        for (WebElement checkBox : checkBoxes) {
            checkBox.click();
        }

        softAssertions.assertThat(checkBoxes).as("Checkboxes selected").allSatisfy(WebElement::isSelected);
    }

    public void selectRadioButtons(SoftAssertions softAssertions) {
        List<WebElement> radioButton = driver.findElements(By.className("label-radio"))
                .stream()
                .filter(x -> x.getText().contains(radioButtonName))
                .collect(Collectors.toList());
        radioButton.forEach(WebElement::click);

        softAssertions.assertThat(radioButton).as("Radio button selected").allSatisfy(WebElement::isSelected);
    }

    public void selectDropdown(SoftAssertions softAssertions) {
        Select objSelect = new Select(driver.findElement(By.cssSelector(".colors .uui-form-element")));
        objSelect.selectByVisibleText(DifferentElementsPageData.dropdownName);

        softAssertions.assertThat(objSelect.getFirstSelectedOption().getText())
                .as("Dropdown selected").isEqualTo(DifferentElementsPageData.dropdownName);
    }

    public void testCheckboxesLog(SoftAssertions softAssertions) {
        List<WebElement> log = driver.findElements(By.cssSelector(".panel-body-list.logs > li"));
        checkboxesLog.forEach(x -> {
            List<WebElement> filteredLog = log
                    .stream()
                    .filter(logString -> logString.getText().matches(x))
                    .collect(Collectors.toList());
            softAssertions.assertThat(filteredLog.size()).isNotZero();
        });
    }

    public void testRadioButtonLog(SoftAssertions softAssertions) {
        String radioLog = driver.findElement(By.xpath(String.format(
                "//ul[contains(@class,\"panel-body-list\")]/li[contains(.,\"%s\")]",
                radioButtonName)
        )).getText();

        softAssertions.assertThat(radioLog).as("Radio button log")
                .matches(x -> x.matches(DifferentElementsPageData.radioLog));
    }

    public void testDropdownLog(SoftAssertions softAssertions) {
        String dropdownLog = driver.findElement(By.xpath(String.format(
                "//ul[contains(@class,\"panel-body-list\")]/li[contains(.,\"%s\")]",
                DifferentElementsPageData.dropdownName)
        )).getText();

        softAssertions.assertThat(dropdownLog).as("Dropdown log")
                .matches(x -> x.matches(DifferentElementsPageData.dropdownLog));
    }
}
