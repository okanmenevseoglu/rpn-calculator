package io.github.okanmenevseoglu.rpncalculator.operation.other;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;
import io.github.okanmenevseoglu.rpncalculator.operation.other.dto.NonMathOperatorResult;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This processor is used to clear the current calculator stack. The previous stack is still held in the undo stack and
 * can be reverted with the undo operation. This feature can be changed very easily if not wanted by modifying the passed
 * undo stack parameter and changing it into a new stack.
 */
public class ClearProcessor extends BaseNonMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.CLEAR;
    }

    @Override
    public NonMathOperatorResult operate(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        return new NonMathOperatorResult(new Stack<>(), undoStack);
    }
}
