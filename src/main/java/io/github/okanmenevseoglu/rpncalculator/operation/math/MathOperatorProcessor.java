package io.github.okanmenevseoglu.rpncalculator.operation.math;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;
import io.github.okanmenevseoglu.rpncalculator.operation.OperatorProcessor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This is an interface that is used to implement math operators. i.e. +, -, *, /, sqrt
 */
public interface MathOperatorProcessor extends OperatorProcessor {

    void process(Operator operator, Stack<BigDecimal> numbers, Stack<Stack<BigDecimal>> undoStack);

    void operate(Stack<BigDecimal> numbers, MathContext mathContext);
}
