package io.github.okanmenevseoglu.rpncalculator.operation.math;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This processor is used to handle division between two operands.
 */
public class DivisionProcessor extends BaseMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.DIVISION;
    }

    @Override
    public void operate(Stack<BigDecimal> calculatorStack, MathContext mc) {
        BigDecimal number2 = calculatorStack.pop();
        BigDecimal number1 = calculatorStack.pop();

        try {
            calculatorStack.push(number1.divide(number2, mc));
        } catch (ArithmeticException e) {
            calculatorStack.push(number1);
            calculatorStack.push(number2);
            throw e;
        }
    }
}
