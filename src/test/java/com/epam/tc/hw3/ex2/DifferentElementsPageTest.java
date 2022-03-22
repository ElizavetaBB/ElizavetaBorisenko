package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.BasePageTestInit;
import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.utils.BaseDifferentElementsPageUtils;
import com.epam.tc.hw3.utils.FluentDifferentElementsPageUtils;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends BasePageTestInit {

    @Test
    public void testDifferentElementsPageWithVoidPages() {
        BaseDifferentElementsPageUtils differentElementsPageUtils =
                new BaseDifferentElementsPageUtils(this.driver, this.wait, new PropertyReader(), this.softAssertions);
        differentElementsPageUtils.testDifferentElementsPage();
        softAssertions.assertAll();
    }

    @Test
    public void testDifferentElementsPageWithFluentPages() {
        FluentDifferentElementsPageUtils differentElementsPageUtils =
                new FluentDifferentElementsPageUtils(this.driver, this.wait, new PropertyReader(), this.softAssertions);
        differentElementsPageUtils.testDifferentElementsPage();
        softAssertions.assertAll();
    }
}
