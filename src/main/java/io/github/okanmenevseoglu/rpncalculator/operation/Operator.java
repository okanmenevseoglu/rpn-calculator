package io.github.okanmenevseoglu.rpncalculator.operation;

/**
 * An enum class that symbolizes possible operations by the application. Operators also have corresponding symbols and
 * required operands count to be used by the processors.
 */
public enum Operator {

    ADDITION("+", 2),
    SUBTRACTION("-", 2),
    MULTIPLICATION("*", 2),
    DIVISION("/", 2),
    SQUARE_ROOT("sqrt", 1),
    UNDO("undo", 0),
    CLEAR("clear", 0);

    private final String symbol;

    private final Integer requiredOperandsCount;

    Operator(String symbol, Integer requiredOperandsCount) {
        this.symbol = symbol;
        this.requiredOperandsCount = requiredOperandsCount;
    }

    public static Operator fromString(String s) {
        for (Operator operator : Operator.values()) {
            if (operator.getSymbol().equals(s)) {
                return operator;
            }
        }

        return null;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getRequiredOperandsCount() {
        return requiredOperandsCount;
    }
}
