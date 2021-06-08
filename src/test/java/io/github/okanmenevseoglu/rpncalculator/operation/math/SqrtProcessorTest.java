package io.github.okanmenevseoglu.rpncalculator.operation.math;

import io.github.okanmenevseoglu.rpncalculator.operation.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A unit test class that contains the tests of {@link SqrtProcessor} class.
 */
@ExtendWith(MockitoExtension.class)
class SqrtProcessorTest {

    @InjectMocks
    private SqrtProcessor sqrtProcessor;

    @Test
    void shouldGetSqrtOperator() {
        Operator operator = sqrtProcessor.getOperator();

        assertThat(operator).isEqualTo(Operator.SQUARE_ROOT);
    }

    @Test
    void shouldProcessSquareRoot() {
        Operator operator = Operator.SQUARE_ROOT;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(100));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));
        actualCalculatorStack.push(BigDecimal.valueOf(10000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(100));
        expectedCalculatorStack.push(BigDecimal.valueOf(1000));
        expectedCalculatorStack.push(BigDecimal.valueOf(100));

        sqrtProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldNotProcessSquareRoot() {
        Operator operator = Operator.SUBTRACTION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(1000));

        sqrtProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldThrowExceptionForProcessSquareRoot() {
        Operator operator = Operator.SQUARE_ROOT;

        Stack<BigDecimal> numbers = new Stack<>();

        try {
            sqrtProcessor.validate(operator, numbers);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("1 operand(s) are required!");
        }
    }
}
