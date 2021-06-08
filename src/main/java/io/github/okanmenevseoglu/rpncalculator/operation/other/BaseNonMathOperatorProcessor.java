package io.github.okanmenevseoglu.rpncalculator.operation.other;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;
import io.github.okanmenevseoglu.rpncalculator.operation.other.dto.NonMathOperatorResult;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This is an abstract class for base non-math operations. It extends MonMathOperatorProcessor class and implements a generic
 * process method that is shared between all non-math operators. It shares a common validation to check for operators.
 * Moreover, the process method returns a NonMathOperatorResult that contains the current calculator and undo stacks.
 * The reason is mainly fo the clear stack is replaced by a new stack, it is referencing to a new object that
 * needs to be assigned to the original object in RPNCalculator class.
 */
public abstract class BaseNonMathOperatorProcessor implements NonMathOperatorProcessor {

    @Override
    public NonMathOperatorResult process(Operator operator, Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        if (operator.equals(getOperator())) {
            validate(operator, calculatorStack, undoStack);

            return operate(calculatorStack, undoStack);
        }

        return new NonMathOperatorResult(calculatorStack, undoStack);
    }

    void validate(Operator operator, Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        if (operator == null)
            throw new IllegalStateException("The operator cannot be null!");

        if (calculatorStack.isEmpty() && undoStack.isEmpty())
            throw new IllegalArgumentException("The calculator stack is already empty!");
    }

    public abstract Operator getOperator();

    public abstract NonMathOperatorResult operate(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack);
}
