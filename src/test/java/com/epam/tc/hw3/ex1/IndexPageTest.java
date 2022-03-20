package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.BasePageTestInit;
import com.epam.tc.hw3.PropertyReader;
import com.epam.tc.hw3.utils.BaseIndexPageUtils;
import com.epam.tc.hw3.utils.FluentIndexPageUtils;
import org.testng.annotations.Test;

public class IndexPageTest extends BasePageTestInit {

    @Test
    public void testIndexPageWithVoidPages() {
        BaseIndexPageUtils indexPageUtils = new BaseIndexPageUtils(this.driver, this.wait,
                new PropertyReader(), this.softAssertions);
        indexPageUtils.testIndexPage();
        softAssertions.assertAll();
    }

    @Test
    public void testIndexPageWithFluentPages() {
        FluentIndexPageUtils fluentIndexPageUtils = new FluentIndexPageUtils(this.driver, this.wait,
                new PropertyReader(), this.softAssertions);
        fluentIndexPageUtils.testIndexPage();
        softAssertions.assertAll();
    }

}
