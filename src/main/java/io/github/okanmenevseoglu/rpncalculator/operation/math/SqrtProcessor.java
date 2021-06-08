package io.github.okanmenevseoglu.rpncalculator.operation.math;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This processor is used to handle square root operation of an operand.
 */
public class SqrtProcessor extends BaseMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.SQUARE_ROOT;
    }

    @Override
    public void operate(Stack<BigDecimal> calculatorStack, MathContext mc) {
        BigDecimal numberToSqrt = calculatorStack.pop();

        try {
            calculatorStack.push(numberToSqrt.sqrt(mc));
        } catch (ArithmeticException e) {
            calculatorStack.push(numberToSqrt);
            throw e;
        }
    }
}
