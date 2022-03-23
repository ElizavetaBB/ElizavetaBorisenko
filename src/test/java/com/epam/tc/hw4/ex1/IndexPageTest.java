package com.epam.tc.hw4.ex1;

import com.epam.tc.hw4.BasePageTestInit;
import com.epam.tc.hw4.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class IndexPageTest extends BasePageTestInit {

    @Test(description = "Test Index Page")
    @Description("Test login, header items, images and their descriptions, iframes, left section items.")
    @Feature("Index Page")
    @Story("User can login and see header items, images and their descriptions, "
            + "iframe with Frame Button and left section items.")
    public void testIndexPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        IndexPageUtils indexPageUtils = new IndexPageUtils(this.driver, this.wait);

        // 1. Open test site by URL
        // 2. Assert Browser title
        // 3. Perform login
        // 4. Assert User name in the left-top side of screen that user is loggined
        indexPageUtils.checkLoginAndUsername(new PropertyReader(), softAssertions);

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        indexPageUtils.headerItemsTest(softAssertions, IndexPageData.HEADER_ITEMS);

        // 6. Assert that there are 4 images on the Index Page and they are displayed
        indexPageUtils.imageItemsTest(softAssertions, IndexPageData.imagesNumber);

        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        indexPageUtils.imagesItemsTextTest(softAssertions, IndexPageData.ICONS_TEXT);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        indexPageUtils.iframeTest(softAssertions, IndexPageData.FRAME_BUTTON);

        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        indexPageUtils.leftSectionItemsTest(softAssertions, IndexPageData.LEFT_SECTION_ITEMS);

        softAssertions.assertAll();
    }

}
