package validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A unit test class that contains the tests of {@link InputValidator} class.
 */
@ExtendWith(MockitoExtension.class)
class InputValidatorTest {

    @Test
    void shouldValidateInputIsNotNull() {
        try {
            InputValidator.validateIfInputNotNull("*");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldValidateInputIsNull() {
        try {
            InputValidator.validateIfInputNotNull(null);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Input can not be empty!");
        }
    }

    @Test
    void shouldNotValidateInputStringDueToMoreOperatorsThanNumbers() {
        try {
            InputValidator.validateIfInputIsHasLessOperands(new Stack<>(), 8, "*");
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Too many operators! The operation in position 8 has not enough operands: *");
        }
    }
}
