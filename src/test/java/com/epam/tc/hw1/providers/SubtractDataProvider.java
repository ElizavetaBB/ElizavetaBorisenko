package com.epam.tc.hw1.providers;

import org.testng.annotations.DataProvider;

public class SubtractDataProvider {

    @DataProvider(name = "correct long subtract data")
    public static Object[][] getLongData() {
        return new Object[][] {
                {Long.MIN_VALUE, 0, Long.MIN_VALUE},
                {0, Long.MAX_VALUE, Long.MIN_VALUE + 1},
                {Long.MIN_VALUE, Long.MIN_VALUE, 0},
                {15, -10, 25},
                {15, 30, -15},
        };
    }

    @DataProvider(name = "correct double subtract data")
    public static Object[][] getDoubleData() {
        return new Object[][] {
                {Double.MIN_VALUE, 0, Double.MIN_VALUE},
                {Double.MIN_VALUE, Double.MIN_VALUE, 0},
                {6.8, -3, 9.8},
                {8.6, 3, 5.6},
        };
    }
}
