package com.epam.tc.hw4.ex2;

import com.epam.tc.hw4.BasePageTestInit;
import com.epam.tc.hw4.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends BasePageTestInit {

    @Test(description = "Test Different Elements Page")
    @Description("Test login, opening of the Different Elements Page, checkboxes, "
            + "radio buttons, dropdown and their log")
    @Feature("Different Elements Page")
    @Story("User can login, open Different Elements Page and select checkboxes, "
            + "radio button and dropdown, see log of the selected elements")
    public void testDifferentElementsPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        DifferentElementsPageUtils differentElementsPageUtils = new
                DifferentElementsPageUtils(this.driver, this.wait);

        // 1. Open test site by URL
        // 2. Assert Browser title
        // 3. Perform login
        // 4. Assert User name in the left-top side of screen that user is loggined
        differentElementsPageUtils.checkLoginAndUsername(new PropertyReader(), softAssertions);

        // 5. Open through the header menu Service -> Different Elements Page
        differentElementsPageUtils.openDifferentElementsPage();
        differentElementsPageUtils.differentElementsPageTitleTest(softAssertions,
                DifferentElementsPageData.DIFFERENT_ELEMENTS_PAGE_TITLE);

        // 6. Select checkboxes Water, Wind
        differentElementsPageUtils.selectCheckboxes(DifferentElementsPageData.CHECKBOXES_NAMES);
        differentElementsPageUtils.selectedCheckboxesTest(softAssertions, DifferentElementsPageData.CHECKBOXES_NAMES);

        // 7. Select radio Selen
        differentElementsPageUtils.selectRadioButton(DifferentElementsPageData.RADIO_BUTTON_NAME);
        differentElementsPageUtils.selectedRadioButtonTest(softAssertions, DifferentElementsPageData.RADIO_BUTTON_NAME);

        // 8. Select in dropdown Yellow
        differentElementsPageUtils.selectDropdown(DifferentElementsPageData.DROPDOWN_NAME);
        differentElementsPageUtils.selectedDropdownTest(softAssertions, DifferentElementsPageData.DROPDOWN_NAME);

        // 9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        // Assert that for radio button there is a log row
        // and value is corresponded to the status of radio button
        // Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        differentElementsPageUtils.allElementLogTest(softAssertions, DifferentElementsPageData.CHECKBOXES_LOG,
                DifferentElementsPageData.RADIO_LOG, DifferentElementsPageData.DROPDOWN_LOG);

        softAssertions.assertAll();
    }

}
