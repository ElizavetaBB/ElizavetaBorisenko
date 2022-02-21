package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.providers.SumDataProvider;
import org.testng.annotations.Test;

@Test(groups = "sum")
public class SumTest {

    @Test(dataProviderClass = SumDataProvider.class, dataProvider = "correct long sum data")
    public void sumLongTest(long a, long b, long expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.sum(a, b)).isEqualTo(expected);
    }

    @Test(dataProviderClass = SumDataProvider.class, dataProvider = "correct double sum data")
    public void sumDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.sum(a, b)).isEqualTo(expected, offset(1E-5));
    }

}
