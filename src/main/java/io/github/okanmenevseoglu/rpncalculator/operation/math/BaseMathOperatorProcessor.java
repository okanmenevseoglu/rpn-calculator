package io.github.okanmenevseoglu.rpncalculator.operation.math;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This is an abstract class for base math operations. It extends MathOperatorProcessor class and implements a generic
 * process method that is shared between all math operators. It shares a common validation and a math context to define
 * precision of BigDecimal numbers. Moreover, it updates the undo stack by creating a clone of the current stack.
 */
public abstract class BaseMathOperatorProcessor implements MathOperatorProcessor {

    public static final int MATH_CONTEXT_PRECISION = 11;

    private final MathContext mathContext = new MathContext(MATH_CONTEXT_PRECISION);

    @SuppressWarnings("unchecked")
    @Override
    public void process(Operator operator, Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        if (operator.equals(getOperator())) {
            validate(operator, calculatorStack);

            operate(calculatorStack, mathContext);

            undoStack.push((Stack<BigDecimal>) calculatorStack.clone());
        }
    }

    public void validate(Operator operator, Stack<BigDecimal> calculatorStack) {
        if (operator == null)
            throw new IllegalStateException("The operator cannot be null");

        if (calculatorStack.size() < operator.getRequiredOperandsCount())
            throw new IllegalStateException(operator.getRequiredOperandsCount() + " operand(s) are required!");
    }

    public abstract Operator getOperator();

    public abstract void operate(Stack<BigDecimal> calculatorStack, MathContext mc);
}
