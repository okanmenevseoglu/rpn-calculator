package validation;

import operation.Operator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Stack;

/**
 * This class is mainly used by RPNCalculator class to validate the input provided by the user.
 */
public class InputValidator {

    public static void validateIfInputIsHasLessOperands(Stack<BigDecimal> currentCalculatorStack, int position, String input) {
        if (currentCalculatorStack.size() < Objects.requireNonNull(Operator.fromString(input)).getRequiredOperandsCount())
            throw new IllegalArgumentException("Too many operators! The operation in position " + position + " has not enough operands: " + input);
    }

    public static void validateIfInputNotNull(String input) {
        if (input == null || input.equals(""))
            throw new IllegalStateException("Input can not be empty!");
    }

    public static boolean isNumeric(String strNum) {
        try {
            new BigDecimal(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public static boolean isOperator(String s) {
        return Operator.fromString(s) != null;
    }
}
