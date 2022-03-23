package com.epam.tc.hw4.failed;

import com.epam.tc.hw4.BasePageTestInit;
import com.epam.tc.hw4.PropertyReader;
import com.epam.tc.hw4.ex1.IndexPageData;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class IndexPageFailedTest extends BasePageTestInit {

    @Test(description = "Test Index Page with failure")
    @Description("Test login, header items, images and their descriptions, iframes, left section items.")
    @Feature("Index Page")
    @Story("User can login and see header items, images and their descriptions, "
            + "iframe with Frame Button and left section items.")
    public void testIndexPageWithVoidPages() {
        SoftAssertions softAssertions = new SoftAssertions();
        PropertyReader propertyReader = new PropertyReader();
        IndexPageFailedUtils indexPageUtils = new IndexPageFailedUtils(this.driver, this.wait);

        indexPageUtils.openSite(propertyReader.getPageUrl());

        indexPageUtils.homePageTitleTest(softAssertions, propertyReader.getHomePageTitle());

        indexPageUtils.performLogin(propertyReader.getLogin(), propertyReader.getPassword());

        indexPageUtils.usernameTest(softAssertions, propertyReader.getUserName());

        indexPageUtils.headerItemsTest(softAssertions, IndexPageData.HEADER_ITEMS);

        indexPageUtils.imageItemsTest(softAssertions, IndexPageData.imagesNumber);

        indexPageUtils.imagesItemsTextTest(softAssertions, IndexPageData.ICONS_TEXT);

        // Failed test
        indexPageUtils.iframeTest(softAssertions, IndexPageData.FRAME_BUTTON);

        indexPageUtils.leftSectionItemsTest(softAssertions, IndexPageData.LEFT_SECTION_ITEMS);

        softAssertions.assertAll();
    }

}
