package operation.other.dto;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This is a DTO object that represents the response of non-math operator results.
 */
public class NonMathOperatorResult {

    private final Stack<BigDecimal> calculatorStack;

    private final Stack<Stack<BigDecimal>> undoStack;

    public NonMathOperatorResult(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack) {
        this.calculatorStack = calculatorStack;
        this.undoStack = undoStack;
    }

    public Stack<BigDecimal> getCalculatorStack() {
        return calculatorStack;
    }

    public Stack<Stack<BigDecimal>> getUndoStack() {
        return undoStack;
    }
}
