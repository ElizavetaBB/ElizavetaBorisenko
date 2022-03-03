package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.BasePageTest;
import com.epam.tc.hw2.PropertyReader;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class IndexPageTest extends BasePageTest {

    @Test
    public void testIndexPage() {
        SoftAssertions softAssertions = new SoftAssertions();
        IndexPageUtils indexPageUtils = new IndexPageUtils(driver, driverWait,
                new PropertyReader(IndexPageData.propertiesPath));

        // 1. Open test site by URL
        indexPageUtils.openSite();

        // 2. Assert Browser title
        indexPageUtils.testBrowserTitle(softAssertions);

        // 3. Perform login
        indexPageUtils.performLogin();

        // 4. Assert Username is loggined
        indexPageUtils.testUsername(softAssertions);

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        indexPageUtils.testHeaderSection(softAssertions);

        // 6. Assert that there are 4 images on the Index Page and they are displayed
        indexPageUtils.testImagesDisplayed(softAssertions);

        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        indexPageUtils.testIconsText(softAssertions);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        indexPageUtils.testFrameButton(softAssertions);

        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        indexPageUtils.testLeftSection(softAssertions);

        softAssertions.assertAll();
    }

}
