package io.github.okanmenevseoglu.rpncalculator.operation.other;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;
import io.github.okanmenevseoglu.rpncalculator.operation.other.dto.NonMathOperatorResult;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This processor is used to undo the last input. The last input can be either be put directly or it can be the result
 * of an operation. If multiple operations are done in a single line, undo operation will still revert them one by one.
 */
public class UndoProcessor extends BaseNonMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.UNDO;
    }

    @Override
    public NonMathOperatorResult operate(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        popUndoStackIfCalculatorStackIsNotEmpty(calculatorStack, undoStack);

        calculatorStack = popCalculatorStackIfNoUndoIsLeftOrGetLastOperation(calculatorStack, undoStack);

        return new NonMathOperatorResult(calculatorStack, undoStack);
    }

    private void popUndoStackIfCalculatorStackIsNotEmpty(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        if (!calculatorStack.isEmpty()) {
            undoStack.pop();
        }
    }

    private Stack<BigDecimal> popCalculatorStackIfNoUndoIsLeftOrGetLastOperation(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        if (undoStack.isEmpty())
            calculatorStack.pop();
        else
            calculatorStack = undoStack.peek();

        return calculatorStack;
    }
}
