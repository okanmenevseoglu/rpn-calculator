package operation.math;

import operation.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * This processor is used to handle multiplication between two operands.
 */
public class MultiplicationProcessor extends BaseMathOperatorProcessor {

    @Override
    public Operator getOperator() {
        return Operator.MULTIPLICATION;
    }

    @Override
    public void operate(Stack<BigDecimal> calculatorStack, MathContext mc) {
        BigDecimal result = calculatorStack.pop().multiply(calculatorStack.pop(), mc);

        calculatorStack.push(result);
    }
}
