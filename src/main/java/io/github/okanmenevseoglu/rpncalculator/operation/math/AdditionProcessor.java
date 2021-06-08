package io.github.okanmenevseoglu.rpncalculator.operation.math;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This processor is used to handle addition between two operands.
 */
public class AdditionProcessor extends BaseMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.ADDITION;
    }

    @Override
    public void operate(Stack<BigDecimal> calculatorStack, MathContext mc) {
        BigDecimal result = calculatorStack.pop().add(calculatorStack.pop(), mc);

        calculatorStack.push(result);
    }
}
