package com.epam.tc.hw2.ex2;

import com.epam.tc.hw2.BasePageTest;
import com.epam.tc.hw2.PropertyReader;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends BasePageTest {

    @Test
    public void testDifferentElementsPage() {
        SoftAssertions softAssertions = new SoftAssertions();
        DifferentElementsPageUtils utils = new DifferentElementsPageUtils(driver, driverWait,
                new PropertyReader(DifferentElementsPageData.propertiesPath));

        // 1. Open test site by URL
        utils.openSite();

        // 2. Assert Browser title
        utils.testBrowserTitle(softAssertions);

        // 3. Perform login
        utils.performLogin();

        // 4. Assert User name in the left-top side of screen that user is loggined
        utils.testUsername(softAssertions);

        // 5. Open through the header menu Service -> Different Elements Page
        utils.openDifferentElementsPage(softAssertions);

        // 6. Select checkboxes Water, Wind
        utils.selectCheckboxes(softAssertions);

        // 7. Select radio Selen
        utils.selectRadioButtons(softAssertions);

        // 8. Select in dropdown Yellow
        utils.selectDropdown(softAssertions);

        // 9. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        utils.testCheckboxesLog(softAssertions);

        // 9. Assert that for radio button there is a log row
        // and value is corresponded to the status of radio button
        utils.testRadioButtonLog(softAssertions);

        // 9. Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        utils.testDropdownLog(softAssertions);

        softAssertions.assertAll();
    }

}
