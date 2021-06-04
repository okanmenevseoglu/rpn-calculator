package operation.other;

import operation.Operator;
import operation.OperatorProcessor;
import operation.other.dto.NonMathOperatorResult;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This is an interface that is used to implement non-math operators. i.e. clear, undo
 */
public interface NonMathOperatorProcessor extends OperatorProcessor {

    NonMathOperatorResult process(Operator operator, Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack);

    NonMathOperatorResult operate(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack);
}
