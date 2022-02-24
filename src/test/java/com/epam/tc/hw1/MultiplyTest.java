package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.providers.MultiplyDataProvider;
import org.testng.annotations.Test;

public class MultiplyTest {

    @Test(groups = {"multiply"},
            dataProviderClass = MultiplyDataProvider.class, dataProvider = "correct long multiply data")
    public void multiplyLongTest(long a, long b, long expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.mult(a, b)).isEqualTo(expected);
    }

    @Test(groups = {"multiply"},
            dataProviderClass = MultiplyDataProvider.class, dataProvider = "correct double multiply data")
    public void multiplyDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.mult(a, b)).isEqualTo(expected, offset(1E-5));
    }

    @Test(groups = {"multiply"},
            dataProviderClass = MultiplyDataProvider.class, dataProvider = "correct big double multiply data")
    public void multiplyBigDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.mult(a, b)).isEqualTo(expected, offset(1E-5));
    }

}
