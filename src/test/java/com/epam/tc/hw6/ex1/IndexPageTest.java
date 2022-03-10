package com.epam.tc.hw6.ex1;

import com.epam.tc.hw6.BasePageTest;
import com.epam.tc.hw6.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class IndexPageTest extends BasePageTest {

    @Test(description = "Test Index Page")
    @Description("Test login, header items, images and their descriptions, iframes, left section items.")
    @Feature("Index Page")
    @Story("User can login and see header items, images and their descriptions, "
            + "iframe with Frame Button and left section items.")
    public void testIndexPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        PropertyReader propertyReader = new PropertyReader(IndexPageData.PROPERTIES_PATH);
        IndexPageUtils indexPageUtils = new IndexPageUtils(this.driver, this.wait);

        // 1. Open test site by URL
        indexPageUtils.openSite(propertyReader.getPageUrl());

        // 2. Assert Browser title
        indexPageUtils.homePageTitleTest(softAssertions, propertyReader.getHomePageTitle());

        // 3. Perform login
        indexPageUtils.performLogin(propertyReader.getLogin(), propertyReader.getPassword());

        // 4. Assert User name in the left-top side of screen that user is loggined
        indexPageUtils.usernameTest(softAssertions, propertyReader.getUserName());

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        indexPageUtils.headerItemsTest(softAssertions, IndexPageData.headerItems);

        // 6. Assert that there are 4 images on the Index Page and they are displayed
        indexPageUtils.imageItemsTest(softAssertions, IndexPageData.imagesNumber);

        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        indexPageUtils.imagesItemsTextTest(softAssertions, IndexPageData.iconsText);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        indexPageUtils.iframeTest(softAssertions, IndexPageData.frameButton);

        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        indexPageUtils.leftSectionItemsTest(softAssertions, IndexPageData.leftSectionItems);

        softAssertions.assertAll();
    }

}

