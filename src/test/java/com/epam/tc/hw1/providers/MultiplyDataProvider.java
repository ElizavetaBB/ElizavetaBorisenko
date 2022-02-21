package com.epam.tc.hw1.providers;

import org.testng.annotations.DataProvider;

public class MultiplyDataProvider {

    @DataProvider(name = "correct long multiply data")
    public static Object[][] getLongData() {
        return new Object[][] {
                {1, 0, 0},
                {3, -9, -27},
                {Long.MAX_VALUE, -1, Long.MIN_VALUE + 1},
                {-1, -27, 27},
                {3, 5, 15}
        };
    }

    @DataProvider(name = "correct double multiply data")
    public static Object[][] getDoubleData() {
        return new Object[][] {
                {1.1, 5, 5.0},
                {5.6, 0.2, 1.0},
                {1.5, -1, -2.0},
                {8.9, 0, 0},
        };
    }

    @DataProvider(name = "correct big double multiply data")
    public static Object[][] getBigDoubleData() {
        return new Object[][] {
                {1E10, 1E10, 1E20},
                {1E100, -1E100, -1E200},
                {-1E20, -1E80, 1E100}
        };
    }


}
