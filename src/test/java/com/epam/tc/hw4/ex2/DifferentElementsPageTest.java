package com.epam.tc.hw4.ex2;

import com.epam.tc.hw4.BasePageTest;
import com.epam.tc.hw4.PropertyReader;
import com.epam.tc.hw4.voids.IndexPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends BasePageTest {

    @Test(description = "Test Different Elements Page")
    @Description("Test login, opening Different Elements Page, checkboxes, "
            + "radio buttons, dropdown and their log")
    @Feature("Different Elements Page")
    @Story("User can login, open Different Elements Page and select checkboxes, "
            + "radio button and dropdown, see log of selected elements")
    public void testDifferentElementsPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        PropertyReader propertyReader = new PropertyReader(DifferentElementsPageData.PROPERTIES_PATH);
        IndexPage indexPage = new IndexPage(driver, wait);
        DifferentElementsPageUtils differentElementsPageUtils = new
                DifferentElementsPageUtils(this.driver, this.wait, indexPage);

        // 1. Open test site by URL
        differentElementsPageUtils.openSite(propertyReader.getPageUrl());

        // 2. Assert Browser title
        differentElementsPageUtils.homePageTitleTest(softAssertions, indexPage.getTitle());

        // 3. Perform login
        differentElementsPageUtils.performLogin(propertyReader.getLogin(), propertyReader.getPassword());

        // 4. Assert User name in the left-top side of screen that user is loggined
        differentElementsPageUtils.usernameTest(softAssertions, propertyReader.getUserName());

        // 5. Open through the header menu Service -> Different Elements Page
        differentElementsPageUtils.openDifferentElementsPage();
        differentElementsPageUtils.differentElementsPageTitleTest(softAssertions,
                DifferentElementsPageData.differentElementsPageTitle);

        // 6. Select checkboxes Water, Wind
        differentElementsPageUtils.selectCheckboxes(DifferentElementsPageData.checkboxesNames);
        differentElementsPageUtils.selectedCheckboxesTest(softAssertions, DifferentElementsPageData.checkboxesNames);

        // 7. Select radio Selen
        differentElementsPageUtils.selectRadioButton(DifferentElementsPageData.radioButtonName);
        differentElementsPageUtils.selectedRadioButtonTest(softAssertions, DifferentElementsPageData.radioButtonName);

        // 8. Select in dropdown Yellow
        differentElementsPageUtils.selectDropdown(DifferentElementsPageData.dropdownName);
        differentElementsPageUtils.selectedDropdownTest(softAssertions, DifferentElementsPageData.dropdownName);

        // 9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        // Assert that for radio button there is a log row
        // and value is corresponded to the status of radio button
        // Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        differentElementsPageUtils.allElementLogTest(softAssertions, DifferentElementsPageData.checkboxesLog,
                DifferentElementsPageData.radioLog, DifferentElementsPageData.dropdownLog);
        softAssertions.assertAll();
    }

}
