import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A unit test class that contains the tests of {@link Main} class.
 */
class MainTest {

    private final InputStream defaultStandardIn = System.in;

    private final PrintStream defaultStandardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(defaultStandardIn);
        System.setOut(defaultStandardOut);
    }

    @Test
    void shouldRunApplicationWithSeveralInputsUntilExit() {
        String consoleInput = """
                5 4 3
                +
                5 7
                6 3 -
                5 7 3
                ==
                wrong-text
                                
                undo
                6 8 / -
                undo
                clear
                5 4 3
                - -
                0 /
                eXiT""";

        InputStream in = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(in);

        Main.main(null);

        String expectedSystemOut = """
                Welcome to Reverse Polish Notation (RPN) calculator!
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 4, 3]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7, 5, 7]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7, 5, 7, 3]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7, 5, 7, 3, 5, 7, 3]
                Please enter an RPN (Type "exit" to exit):
                Error! The operation in position 1 is not supported: ==
                Stack: [5, 7, 5, 7, 3, 5, 7, 3]
                Please enter an RPN (Type "exit" to exit):
                Error! The operation in position 1 is not supported: wrong-text
                Stack: [5, 7, 5, 7, 3, 5, 7, 3]
                Please enter an RPN (Type "exit" to exit):
                Error! Input can not be empty!
                Stack: [5, 7, 5, 7, 3, 5, 7, 3]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7, 5, 7, 3, 5, 7]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7, 5, 7, 3, 5, 6.25]
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 7, 5, 7, 3, 5, 7, 0.75]
                Please enter an RPN (Type "exit" to exit):
                Stack: []
                Please enter an RPN (Type "exit" to exit):
                Stack: [5, 4, 3]
                Please enter an RPN (Type "exit" to exit):
                Stack: [4]
                Please enter an RPN (Type "exit" to exit):
                Error! Division by zero
                Stack: [4, 0]
                Please enter an RPN (Type "exit" to exit):
                Thank you for using RPN calculator!""";

        assertEquals(expectedSystemOut, outputStreamCaptor.toString().trim());
    }
}
