package operation.math;

import operation.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A unit test class that contains the tests of {@link MultiplicationProcessor} class.
 */
@ExtendWith(MockitoExtension.class)
class MultiplicationProcessorTest {

    @InjectMocks
    private MultiplicationProcessor multiplicationProcessor;

    @Test
    void shouldGetMultiplicationOperator() {
        Operator operator = multiplicationProcessor.getOperator();

        assertThat(operator).isEqualTo(Operator.MULTIPLICATION);
    }

    @Test
    void shouldProcessMultiplication() {
        Operator operator = Operator.MULTIPLICATION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(100));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));
        actualCalculatorStack.push(BigDecimal.valueOf(10000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(100));
        expectedCalculatorStack.push(BigDecimal.valueOf(10_000_000));

        multiplicationProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldNotProcessMultiplication() {
        Operator operator = Operator.ADDITION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(1000));

        multiplicationProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldThrowExceptionForProcessMultiplication() {
        Operator operator = Operator.MULTIPLICATION;

        Stack<BigDecimal> numbers = new Stack<>();
        numbers.push(BigDecimal.valueOf(10));

        try {
            multiplicationProcessor.validate(operator, numbers);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("2 operand(s) are required!");
        }
    }
}
