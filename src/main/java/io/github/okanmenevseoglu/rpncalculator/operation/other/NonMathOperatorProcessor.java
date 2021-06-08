package io.github.okanmenevseoglu.rpncalculator.operation.other;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;
import io.github.okanmenevseoglu.rpncalculator.operation.OperatorProcessor;
import io.github.okanmenevseoglu.rpncalculator.operation.other.dto.NonMathOperatorResult;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This is an interface that is used to implement non-math operators. i.e. clear, undo
 */
public interface NonMathOperatorProcessor extends OperatorProcessor {

    NonMathOperatorResult process(Operator operator, Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack);

    NonMathOperatorResult operate(Stack<BigDecimal> calculatorStack, Stack<Stack<BigDecimal>> undoStack);
}
