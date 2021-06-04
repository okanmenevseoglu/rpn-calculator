package operation.other;

import operation.Operator;
import operation.other.dto.NonMathOperatorResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A unit test class that contains the tests of {@link ClearProcessor} class.
 */
@ExtendWith(MockitoExtension.class)
class ClearProcessorTest {

    @InjectMocks
    private ClearProcessor clearProcessor;

    @Test
    void shouldGetClearOperator() {
        Operator operator = clearProcessor.getOperator();

        assertThat(operator).isEqualTo(Operator.CLEAR);
    }

    @Test
    void shouldProcessClear() {
        Operator operator = Operator.CLEAR;

        Stack<BigDecimal> calculatorStack = new Stack<>();
        calculatorStack.push(BigDecimal.valueOf(10));
        calculatorStack.push(BigDecimal.valueOf(100));
        calculatorStack.push(BigDecimal.valueOf(1000));
        calculatorStack.push(BigDecimal.valueOf(10000));

        Stack<BigDecimal> undoStackItem = new Stack<>();
        undoStackItem.push(BigDecimal.valueOf(10));
        undoStackItem.push(BigDecimal.valueOf(100));
        undoStackItem.push(BigDecimal.valueOf(1000));
        undoStackItem.push(BigDecimal.valueOf(10000));

        Stack<Stack<BigDecimal>> undoStack = new Stack<>();
        undoStack.push(undoStackItem);

        NonMathOperatorResult result = clearProcessor.process(operator, calculatorStack, undoStack);

        assertThat(result.getCalculatorStack()).isEqualTo(new Stack<>());
        assertThat(result.getUndoStack()).isEqualTo(undoStack);
    }

    @Test
    void shouldThrowExceptionForNullOperator() {
        try {
            clearProcessor.validate(null, null, null);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("The operator cannot be null!");
        }
    }
}
