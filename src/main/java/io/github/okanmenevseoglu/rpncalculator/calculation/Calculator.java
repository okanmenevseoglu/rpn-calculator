package io.github.okanmenevseoglu.rpncalculator.calculation;

import java.math.BigDecimal;
import java.util.Stack;

public interface Calculator {

    Stack<BigDecimal> calculate(String input);

    Stack<BigDecimal> getCalculatorStack();
}
