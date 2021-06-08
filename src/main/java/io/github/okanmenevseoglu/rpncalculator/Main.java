package io.github.okanmenevseoglu.rpncalculator;

import io.github.okanmenevseoglu.rpncalculator.calculation.Calculator;
import io.github.okanmenevseoglu.rpncalculator.calculation.RPNCalculator;
import io.github.okanmenevseoglu.rpncalculator.operation.math.*;
import io.github.okanmenevseoglu.rpncalculator.operation.other.ClearProcessor;
import io.github.okanmenevseoglu.rpncalculator.operation.other.UndoProcessor;

import java.util.Scanner;

/**
 * This is the main class to run the Reverse Polish Notation (RPN) application.
 * More information can be found in the <a href="{@docRoot}/README.md"> file
 */
public class Main {

    /**
     * Main method to start the program.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        Calculator rpnCalculator = new RPNCalculator(
                new AdditionProcessor(), new SubtractionProcessor(), new MultiplicationProcessor(),
                new DivisionProcessor(), new SqrtProcessor(), new ClearProcessor(), new UndoProcessor());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Reverse Polish Notation (RPN) calculator!");

        while (true) {
            System.out.println("Please enter an RPN (Type \"exit\" to exit):");
            String consoleInput = scanner.nextLine();

            if (consoleInput.trim().equalsIgnoreCase("exit"))
                break;

            try {
                rpnCalculator.calculate(consoleInput);
            } catch (RuntimeException e) {
                System.out.println("Error! " + e.getMessage());
            }

            System.out.println("Stack: " + rpnCalculator.getCalculatorStack());
        }

        System.out.println("Thank you for using RPN calculator!");
    }
}
