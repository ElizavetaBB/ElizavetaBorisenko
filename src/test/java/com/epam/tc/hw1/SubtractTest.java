package com.epam.tc.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.providers.SubtractDataProvider;
import org.testng.annotations.Test;

@Test(groups = "subtract")
public class SubtractTest {

    @Test(dataProviderClass = SubtractDataProvider.class, dataProvider = "correct long subtract data")
    public void subtractLongTest(long a, long b, long expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.sub(a, b)).isEqualTo(expected);
    }

    @Test(dataProviderClass = SubtractDataProvider.class, dataProvider = "correct double subtract data")
    public void subtractDoubleTest(double a, double b, double expected) {
        Calculator calculator = new Calculator();
        assertThat(calculator.sub(a, b)).isEqualTo(expected, offset(1E-5));
    }

}
