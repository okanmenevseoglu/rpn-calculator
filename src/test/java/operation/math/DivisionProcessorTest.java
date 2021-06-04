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
 * A unit test class that contains the tests of {@link DivisionProcessor} class.
 */
@ExtendWith(MockitoExtension.class)
class DivisionProcessorTest {

    @InjectMocks
    private DivisionProcessor divisionProcessor;

    @Test
    void shouldGetDivisionOperator() {
        Operator operator = divisionProcessor.getOperator();

        assertThat(operator).isEqualTo(Operator.DIVISION);
    }

    @Test
    void shouldProcessDivision() {
        Operator operator = Operator.DIVISION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(100));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));
        actualCalculatorStack.push(BigDecimal.valueOf(10000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(100));
        expectedCalculatorStack.push(BigDecimal.valueOf(0.1));

        divisionProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldNotProcessDivision() {
        Operator operator = Operator.SUBTRACTION;

        Stack<BigDecimal> actualCalculatorStack = new Stack<>();
        actualCalculatorStack.push(BigDecimal.valueOf(10));
        actualCalculatorStack.push(BigDecimal.valueOf(1000));

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(1000));


        divisionProcessor.process(operator, actualCalculatorStack, new Stack<>());

        assertThat(actualCalculatorStack).isEqualTo(expectedCalculatorStack);
    }

    @Test
    void shouldThrowExceptionForProcessDivision() {
        Operator operator = Operator.DIVISION;

        Stack<BigDecimal> numbers = new Stack<>();
        numbers.push(BigDecimal.valueOf(10));

        try {
            divisionProcessor.validate(operator, numbers);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("2 operand(s) are required!");
        }
    }
}
