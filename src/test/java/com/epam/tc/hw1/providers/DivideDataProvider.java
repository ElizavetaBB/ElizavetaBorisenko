package com.epam.tc.hw1.providers;

import org.testng.annotations.DataProvider;

public class DivideDataProvider {

    @DataProvider(name = "correct long divide data")
    public static Object[][] getLongData() {
        return new Object[][] {
                {27, 1, 27},
                {33, 7, 4},
                {21, -3, -7},
                {-21, -3, 7},
                {Long.MAX_VALUE, 1, Long.MAX_VALUE}
        };
    }

    @DataProvider(name = "correct double divide data")
    public static Object[][] getDoubleData() {
        return new Object[][]{
                {8.7, 2, 4.35},
                {8.5, 0.5, 17},
                {1.5, 300, 0.005},
                {34, 3, 11.33333333}
        };
    }

    @DataProvider(name = "correct big double divide data")
    public static Object[][] getBitDoubleData() {
        return new Object[][]{
                {1E200, 1E100, 1E100},
                {1E20, 1E30, 1E-10},
                {1E-20, 1E-30, 1E10},
                {-1E20, 1E10, -1E10},
                {-1E20, -1E10, 1E10},
                {Double.MAX_VALUE, 1, Double.MAX_VALUE}
        };
    }

    @DataProvider(name = "divide by zero data")
    public static Object[][] getZeroDivideData() {
        return new Object[][] {
                {27, 0, new NumberFormatException("Attempt to divide by zero")},
                {0, 0, new NumberFormatException("Attempt to divide by zero")}
        };
    }

}
