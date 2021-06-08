package calculation;

import operation.Operator;
import operation.math.*;
import operation.other.ClearProcessor;
import operation.other.NonMathOperatorProcessor;
import operation.other.UndoProcessor;
import operation.other.dto.NonMathOperatorResult;
import validation.InputValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class is the representation of the RPN calculator. To explain it in short; it collects the math operator
 * processors and non-math operator processors in the constructor. For calculation, it gets a String RPN formatted input
 * and traverses through every input and applies the correct operation that needs to be performed. After the processing
 * is done, the stack is returned with updated elements.
 */
public class RPNCalculator {

    private Stack<BigDecimal> calculatorStack;

    private Stack<Stack<BigDecimal>> undoStack;

    private final HashMap<Operator, MathOperatorProcessor> mathOperatorProcessors;

    private final HashMap<Operator, NonMathOperatorProcessor> nonMathOperatorProcessors;

    public RPNCalculator() {
        calculatorStack = new Stack<>();

        undoStack = new Stack<>();

        mathOperatorProcessors = new HashMap<>();

        mathOperatorProcessors.put(Operator.ADDITION, new AdditionProcessor());
        mathOperatorProcessors.put(Operator.SUBTRACTION, new SubtractionProcessor());
        mathOperatorProcessors.put(Operator.MULTIPLICATION, new MultiplicationProcessor());
        mathOperatorProcessors.put(Operator.DIVISION, new DivisionProcessor());
        mathOperatorProcessors.put(Operator.SQUARE_ROOT, new SqrtProcessor());

        nonMathOperatorProcessors = new HashMap<>();

        nonMathOperatorProcessors.put(Operator.CLEAR, new ClearProcessor());
        nonMathOperatorProcessors.put(Operator.UNDO, new UndoProcessor());
    }

    public Stack<BigDecimal> calculate(String rpnInput) {
        String[] inputs = convertInputToArray(rpnInput);

        int position = 1;

        for (String input : inputs) {

            InputValidator.validateIfInputNotNull(input);

            if (InputValidator.isNumeric(input)) {
                convertAndAddToStacks(input);
            } else if (InputValidator.isOperator(input)) {
                InputValidator.validateIfInputIsHasLessOperands(calculatorStack, position, input);

                processMathOperators(input);
                processNonMathOperators(input);
            } else {
                throw new IllegalArgumentException("The operation in position " + position + " is not supported: " + input);
            }

            position++;
        }

        return calculatorStack;
    }

    @SuppressWarnings("unchecked")
    private void convertAndAddToStacks(String input) {
        calculatorStack.push(new BigDecimal(input));
        undoStack.push((Stack<BigDecimal>) calculatorStack.clone());
    }

    private String[] convertInputToArray(String input) {
        return input.trim()
                .replaceAll(" +", " ")
                .split(" ");
    }

    private void processMathOperators(String input) {
        MathOperatorProcessor processor = mathOperatorProcessors.get(Operator.fromString(input));
        if (processor != null)
            processor.process(Operator.fromString(input), calculatorStack, undoStack);
    }

    private void processNonMathOperators(String input) {
        NonMathOperatorProcessor processor = nonMathOperatorProcessors.get(Operator.fromString(input));

        if (processor != null) {
            NonMathOperatorResult nonMathOperatorResult = processor.process(Operator.fromString(input), calculatorStack, undoStack);
            calculatorStack = nonMathOperatorResult.getCalculatorStack();
            undoStack = nonMathOperatorResult.getUndoStack();
        }
    }

    public Stack<BigDecimal> getCalculatorStack() {
        return calculatorStack;
    }
}
