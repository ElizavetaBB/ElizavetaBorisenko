package com.epam.tc.hw1.providers;

import org.testng.annotations.DataProvider;

public class SumDataProvider {

    @DataProvider(name = "correct long sum data")
    public static Object[][] getLongData() {
        return new Object[][] {
                {Long.MIN_VALUE, 0, Long.MIN_VALUE},
                {Long.MIN_VALUE, Long.MAX_VALUE, -1},
                {0, 0, 0},
                {-1, 1, 0},
                {3, -8, -5},
                {Long.MIN_VALUE, -1, Long.MAX_VALUE},
                {Long.MAX_VALUE, 1, Long.MIN_VALUE}
        };
    }

    @DataProvider(name = "correct double sum data")
    public static Object[][] getDoubleData() {
        return new Object[][] {
                {1.5, -7, -5.5},
                {0, Double.MAX_VALUE, Double.MAX_VALUE},
                {0, Double.MIN_VALUE, Double.MIN_VALUE}
        };
    }

}
