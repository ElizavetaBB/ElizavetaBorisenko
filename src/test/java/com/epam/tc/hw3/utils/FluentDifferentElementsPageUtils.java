package com.epam.tc.hw3.utils;

import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.components.LogComponent;
import com.epam.tc.hw3.ex2.DifferentElementsPageData;
import com.epam.tc.hw3.fluent.DifferentElementsFluentPage;
import com.epam.tc.hw3.fluent.IndexFluentPage;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FluentDifferentElementsPageUtils extends BaseDifferentElementsPageUtils {

    private IndexFluentPage indexFluentPage;
    private DifferentElementsFluentPage differentElementsFluentPage;

    public FluentDifferentElementsPageUtils(WebDriver driver, WebDriverWait wait,
                                PropertyReader propertyReader, SoftAssertions softAssertions) {
        super(driver, wait, propertyReader, softAssertions);
        indexFluentPage = new IndexFluentPage(this.driver, this.driverWait);
    }

    @Override
    protected String openDifferentElementsPageAndReturnTitle() {
        differentElementsFluentPage = indexFluentPage.openDifferentElementsPage();
        return differentElementsFluentPage.getTitle();
    }

    @Override
    protected List<WebElement> clickAndReturnCheckboxes() {
        List<String> checkboxesNames = DifferentElementsPageData.checkboxesNames;

        return differentElementsFluentPage.clickCheckBoxes(checkboxesNames)
                .getCheckboxesByName(checkboxesNames);
    }

    @Override
    protected WebElement clickAndReturnRadioButton() {
        String radioButtonName = DifferentElementsPageData.radioButtonName;

        return differentElementsFluentPage.clickRadioButton(radioButtonName)
                .getRadioButtonByName(radioButtonName);
    }

    @Override
    protected void selectDropdownAndCheck() {
        String dropdownName = DifferentElementsPageData.dropdownName;

        softAssertions.assertThat(differentElementsFluentPage.selectDropdown(dropdownName).getSelectedDropdownText())
                .as("Dropdown selected").isEqualTo(dropdownName);
    }

    @Override
    protected LogComponent getLogComponent() {
        return differentElementsFluentPage.logComponent();
    }

}
