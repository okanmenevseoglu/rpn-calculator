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
 * A unit test class that contains the tests of {@link UndoProcessor} class.
 */
@ExtendWith(MockitoExtension.class)
class UndoProcessorTest {

    @InjectMocks
    private UndoProcessor undoProcessor;

    @Test
    void shouldGetUndoOperator() {
        Operator operator = undoProcessor.getOperator();

        assertThat(operator).isEqualTo(Operator.UNDO);
    }

    @Test
    void shouldProcessUndo() {
        Operator operator = Operator.UNDO;

        Stack<BigDecimal> calculatorStack = new Stack<>();
        calculatorStack.push(BigDecimal.valueOf(10));
        calculatorStack.push(BigDecimal.valueOf(100));
        calculatorStack.push(BigDecimal.valueOf(1000));
        calculatorStack.push(BigDecimal.valueOf(10000));

        Stack<BigDecimal> undoStackItem1 = new Stack<>();
        undoStackItem1.push(BigDecimal.valueOf(10));
        undoStackItem1.push(BigDecimal.valueOf(100));
        undoStackItem1.push(BigDecimal.valueOf(1000));

        Stack<BigDecimal> undoStackItem2 = new Stack<>();
        undoStackItem2.push(BigDecimal.valueOf(10));
        undoStackItem2.push(BigDecimal.valueOf(100));
        undoStackItem2.push(BigDecimal.valueOf(1000));
        undoStackItem2.push(BigDecimal.valueOf(10000));

        Stack<Stack<BigDecimal>> undoStack = new Stack<>();
        undoStack.push(undoStackItem1);
        undoStack.push(undoStackItem2);

        Stack<BigDecimal> expectedCalculatorStack = new Stack<>();
        expectedCalculatorStack.push(BigDecimal.valueOf(10));
        expectedCalculatorStack.push(BigDecimal.valueOf(100));
        expectedCalculatorStack.push(BigDecimal.valueOf(1000));

        Stack<BigDecimal> expectedUndoStackItem = new Stack<>();
        expectedUndoStackItem.push(BigDecimal.valueOf(10));
        expectedUndoStackItem.push(BigDecimal.valueOf(100));
        expectedUndoStackItem.push(BigDecimal.valueOf(1000));

        Stack<Stack<BigDecimal>> expectedUndoStack = new Stack<>();
        expectedUndoStack.add(expectedUndoStackItem);

        NonMathOperatorResult result = undoProcessor.process(operator, calculatorStack, undoStack);

        assertThat(result.getCalculatorStack()).isEqualTo(expectedCalculatorStack);
        assertThat(result.getUndoStack()).isEqualTo(expectedUndoStack);
    }

    @Test
    void shouldProcessUndoForOneItem() {
        Operator operator = Operator.UNDO;

        Stack<BigDecimal> calculatorStack = new Stack<>();
        calculatorStack.push(BigDecimal.valueOf(10));

        Stack<BigDecimal> undoStackItem1 = new Stack<>();
        undoStackItem1.push(BigDecimal.valueOf(10));

        Stack<Stack<BigDecimal>> undoStack = new Stack<>();
        undoStack.push(undoStackItem1);

        NonMathOperatorResult result = undoProcessor.process(operator, calculatorStack, undoStack);

        assertThat(result.getCalculatorStack()).isEqualTo(new Stack<>());
        assertThat(result.getUndoStack()).isEqualTo(new Stack<>());
    }

    @Test
    void shouldNotProcessUndoForEmptyCalculatorStack() {
        Operator operator = Operator.UNDO;

        try {
            undoProcessor.process(operator, new Stack<>(), new Stack<>());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("The calculator stack is already empty!");
        }
    }

    @Test
    void shouldThrowExceptionForNullOperator() {
        try {
            undoProcessor.validate(null, null, null);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("The operator cannot be null!");
        }
    }
}
