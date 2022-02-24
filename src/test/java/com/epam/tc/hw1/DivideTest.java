package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.providers.DivideDataProvider;
import org.testng.annotations.Test;

public class DivideTest {

    @Test(groups = {"divide"},
            dataProviderClass = DivideDataProvider.class, dataProvider = "correct long divide data")
    public void divideLongTest(long a, long b, long expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.div(a, b)).isEqualTo(expected);
    }

    @Test(groups = {"divide"},
            dataProviderClass = DivideDataProvider.class, dataProvider = "correct double divide data")
    public void divideDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.div(a, b)).isEqualTo(expected, offset(1E-5));
    }

    @Test(groups = {"divide"},
            dataProviderClass = DivideDataProvider.class, dataProvider = "correct big double divide data")
    public void divideBigDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.div(a, b)).isEqualTo(expected, offset(1E-5));
    }

    @Test(groups = {"divide"},
            dataProviderClass = DivideDataProvider.class, dataProvider = "divide by zero data")
    public void divideTestWithException(long a, long b, Exception expected) {
        Calculator calculator = new Calculator();
        assertThatThrownBy(
                () -> calculator.div(a, b)
        ).isInstanceOf(expected.getClass()).hasMessageContaining(expected.getMessage());
    }

}
