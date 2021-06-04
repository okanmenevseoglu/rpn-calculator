package calculation;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A unit test class that contains the tests of {@link RPNCalculator} class.
 */
class RPNCalculatorTest {

    @Test
    void shouldCalculate1() {
        String consoleInput = "5          2";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[5, 2]");
    }

    @Test
    void shouldCalculate2() {
        String consoleInput = "2 sqrt";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[1.4142135624]");

        consoleInput = "clear 9 sqrt";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[3]");
    }

    @Test
    void shouldCalculate3() {
        String consoleInput = "5 2 -";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[3]");

        consoleInput = "3 -";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[0]");

        consoleInput = "clear";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[]");
    }

    @Test
    void shouldCalculate4() {
        String consoleInput = "5 4 3 2";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[5, 4, 3, 2]");

        consoleInput = "undo undo *";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[20]");

        consoleInput = "5 *";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[100]");

        consoleInput = "undo";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[20, 5]");
    }

    @Test
    void shouldCalculate5() {
        String consoleInput = "7 12 2 /";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[7, 6]");

        consoleInput = "*";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[42]");

        consoleInput = "4 /";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[10.5]");
    }

    @Test
    void shouldCalculate6() {
        String consoleInput = "1 2 3 4 5";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[1, 2, 3, 4, 5]");

        consoleInput = "*";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[1, 2, 3, 20]");

        consoleInput = "clear 3 4 -";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[-1]");
    }

    @Test
    void shouldCalculate7() {
        String consoleInput = "1 2 3 4 5";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[1, 2, 3, 4, 5]");

        consoleInput = "* * * *";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[120]");
    }

    @Test
    void shouldCalculate8() {
        String consoleInput = "1 2 3 * 5 + * * 6 5";

        RPNCalculator rpnCalculator = new RPNCalculator();

        try {
            rpnCalculator.calculate(consoleInput);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Too many operators! The operation in position 8 has not enough operands: *");
            assertThat(rpnCalculator.getCalculatorStack().toString()).isEqualTo("[11]");
        }
    }

    @Test
    void shouldCalculate9() {
        String consoleInput = "3 5 6 72 + - /";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[-0.041095890411]");
    }

    @Test
    void shouldCalculate10() {
        String consoleInput = "5 4 3 2";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[5, 4, 3, 2]");

        consoleInput = "undo";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[5, 4, 3]");

        consoleInput = "undo";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[5, 4]");

        consoleInput = "*";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[20]");

        consoleInput = "5 *";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[100]");

        consoleInput = "undo";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[20, 5]");
    }

    @Test
    void shouldCalculate11() {
        String consoleInput = "72 15 5 6 + - /";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[18]");

        consoleInput = "clear";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[]");

        consoleInput = "undo";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[18]");

        consoleInput = "undo";

        result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[72, 4]");
    }

    @Test
    void shouldCalculate12() {
        String consoleInput = "72 15 5 9 sqrt 81 sqrt -1 sqrt";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[72, 15, 5, 3, 9, -1]");
    }

    @Test
    void shouldCalculate13() {
        String consoleInput = "-1 sqrt";

        RPNCalculator rpnCalculator = new RPNCalculator();

        Stack<BigDecimal> result = rpnCalculator.calculate(consoleInput);

        assertThat(result.toString()).isEqualTo("[-1]");
    }
}
