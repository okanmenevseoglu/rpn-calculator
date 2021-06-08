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
public class RPNCalculator implements Calculator {

    private final AdditionProcessor additionProcessor;
    private final SubtractionProcessor subtractionProcessor;
    private final MultiplicationProcessor multiplicationProcessor;
    private final DivisionProcessor divisionProcessor;
    private final SqrtProcessor sqrtProcessor;
    private final ClearProcessor clearProcessor;
    private final UndoProcessor undoProcessor;

    private final HashMap<Operator, MathOperatorProcessor> mathOperatorProcessors;
    private final HashMap<Operator, NonMathOperatorProcessor> nonMathOperatorProcessors;

    private Stack<BigDecimal> calculatorStack;
    private Stack<Stack<BigDecimal>> undoStack;

    public RPNCalculator(AdditionProcessor additionProcessor, SubtractionProcessor subtractionProcessor,
                         MultiplicationProcessor multiplicationProcessor, DivisionProcessor divisionProcessor,
                         SqrtProcessor sqrtProcessor, ClearProcessor clearProcessor, UndoProcessor undoProcessor) {
        this.additionProcessor = additionProcessor;
        this.subtractionProcessor = subtractionProcessor;
        this.multiplicationProcessor = multiplicationProcessor;
        this.divisionProcessor = divisionProcessor;
        this.sqrtProcessor = sqrtProcessor;
        this.clearProcessor = clearProcessor;
        this.undoProcessor = undoProcessor;

        mathOperatorProcessors = new HashMap<>();
        nonMathOperatorProcessors = new HashMap<>();

        calculatorStack = new Stack<>();
        undoStack = new Stack<>();

        initProcessors();
    }

    private void initProcessors() {
        mathOperatorProcessors.put(Operator.ADDITION, additionProcessor);
        mathOperatorProcessors.put(Operator.SUBTRACTION, subtractionProcessor);
        mathOperatorProcessors.put(Operator.MULTIPLICATION, multiplicationProcessor);
        mathOperatorProcessors.put(Operator.DIVISION, divisionProcessor);
        mathOperatorProcessors.put(Operator.SQUARE_ROOT, sqrtProcessor);

        nonMathOperatorProcessors.put(Operator.CLEAR, clearProcessor);
        nonMathOperatorProcessors.put(Operator.UNDO, undoProcessor);
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

                Operator operator = Operator.fromString(input);

                processMathOperators(operator);
                processNonMathOperators(operator);
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

    private void processMathOperators(Operator operator) {
        MathOperatorProcessor processor = mathOperatorProcessors.get(operator);

        if (processor != null)
            processor.process(operator, calculatorStack, undoStack);
    }

    private void processNonMathOperators(Operator operator) {
        NonMathOperatorProcessor processor = nonMathOperatorProcessors.get(operator);

        if (processor != null) {
            NonMathOperatorResult nonMathOperatorResult = processor.process(operator, calculatorStack, undoStack);
            calculatorStack = nonMathOperatorResult.getCalculatorStack();
            undoStack = nonMathOperatorResult.getUndoStack();
        }
    }

    public Stack<BigDecimal> getCalculatorStack() {
        return calculatorStack;
    }
}
