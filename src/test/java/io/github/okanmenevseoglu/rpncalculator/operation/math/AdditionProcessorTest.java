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
 * A unit test class that contains the tests of {@link AdditionProcessor} class.
 */
@ExtendWith(MockitoExtension.class)
class AdditionProcessorTest {

    @InjectMocks
    private AdditionProcessor additionProcessor;

    @Test
    void shouldGetAdditionOperator() {
        Operator operator = additionProcessor.getOperator();

        assertThat(operator).isEqualTo(Operator.ADDITION);
    }

    @Test
    void shouldProcessAddition() {
        Operator operator = Operator.ADDITION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(100));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));
        actualCalculatorStack.push(BigDecimal.valueOf(10000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(100));
        expectedCalculatorStack.push(BigDecimal.valueOf(11000));

        additionProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldNotProcessAddition() {
        Operator operator = Operator.SUBTRACTION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(1000));

        additionProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldThrowExceptionForProcessAddition() {
        Operator operator = Operator.ADDITION;

        Stack<BigDecimal> numbers = new Stack<>();
        numbers.push(BigDecimal.valueOf(10));

        try {
            additionProcessor.validate(operator, numbers);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("2 operand(s) are required!");
        }
    }

    @Test
    void shouldThrowExceptionForNullOperator() {
        Stack<BigDecimal> numbers = new Stack<>();
        numbers.push(BigDecimal.valueOf(10));
        numbers.push(BigDecimal.valueOf(100));

        try {
            additionProcessor.validate(null, numbers);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("The operator cannot be null");
        }
    }
}
