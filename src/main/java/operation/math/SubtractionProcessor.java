package operation.math;

import operation.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This processor is used to handle subtraction between two operands.
 */
public class SubtractionProcessor extends BaseMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.SUBTRACTION;
    }

    @Override
    public void operate(Stack<BigDecimal> calculatorStack, MathContext mc) {
        BigDecimal number2 = calculatorStack.pop();
        BigDecimal number1 = calculatorStack.pop();

        BigDecimal result = number1.subtract(number2, mc);

        calculatorStack.push(result);
    }
}
