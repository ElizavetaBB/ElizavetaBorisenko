package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.providers.SubtractDataProvider;
import org.testng.annotations.Test;

public class SubtractTest {

    @Test(groups = {"subtract"},
            dataProviderClass = SubtractDataProvider.class, dataProvider = "correct long subtract data")
    public void subtractLongTest(long a, long b, long expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.sub(a, b)).isEqualTo(expected);
    }

    @Test(groups = {"subtract"},
            dataProviderClass = SubtractDataProvider.class, dataProvider = "correct double subtract data")
    public void subtractDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.sub(a, b)).isEqualTo(expected, offset(1E-5));
    }

}
