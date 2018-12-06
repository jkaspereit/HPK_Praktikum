package util;

import java.util.Arrays;

public enum Functions {


    //trigonometric functions
    SIN("sin", 1),
    ASIN("asin", 1),
    ARCSIN("arcsin", 1),
    SINH("sinh", 1),

    COS("cos", 1),
    ACOS("acos", 1),
    ARCCOS("arccos", 1),
    COSH("cosh", 1),

    TAN("tan", 1),
    ATAN("atan", 1),
    ARCTAN("arctan", 1),
    TANH("tanh", 1),

    //essentials
    MIN("min", null),
    MAX("max", null),
    ABS("abs", 1),

    LOGE("loge", 1),
    LOGN("logn", 1),
    LOGLN("logln", 1),
    LOG10("log10", 1),
    LOG("log", 1),
    LOG2("log2", 1),
    LB("lb", 1),
    LD("ld", 1),
    LN("ln", 1),

    EUL("eul", 1),
    EXP("exp", 1),
    POW("pow", 2),
    SQRT("sqrt", 1);


    private final String name;
    private final Integer countVar;
    private final Function function;

    private Functions(String name, Integer varCount) {
        this.name = name;
        this.countVar = varCount;
        this.function = createFunction();
    }

    public String getName() {
        return name;
    }

    public Function getFunction() {
        return function;
    }

    private Function createFunction() {
        String fctName = name.toLowerCase();

        switch (fctName) {
            case "sin":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.sin(args[0]);
                };
            case "asin":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.asin(args[0]);
                };
            case "arcsin":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.asin(args[0]);
                };
            case "sinh":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.sinh(args[0]);
                };
            case "cos":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.cos(args[0]);
                };
            case "acos":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.acos(args[0]);
                };
            case "arccos":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.acos(args[0]);
                };
            case "cosh":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.cosh(args[0]);
                };
            case "tan":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.tan(args[0]);
                };
            case "atan":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.atan(args[0]);
                };
            case "arctan":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.atan(args[0]);
                };
            case "tanh":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.tanh(args[0]);
                };
            case "log":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log10(args[0]);
                };
            case "loge":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log(args[0]);
                };
            case "logn":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log(args[0]);
                };
            case "logln":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log(args[0]);
                };
            case "ln":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log(args[0]);
                };
            case "log10":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log10(args[0]);
                };
            case "log2":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log10(args[0]) / Math.log10(2);
                };
            case "ld":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log10(args[0]) / Math.log10(2);
                };
            case "lb":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.log10(args[0]) / Math.log10(2);
                };
            case "eul":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.pow(Math.E, args[0]);
                };
            case "exp":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.exp(args[0]);
                };
            case "pow":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.pow(args[0], args[1]);
                };
            case "sqrt":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.pow(args[0], 0.5);
                };
            case "abs":
                return (double... args) -> {
                    checkVarCount(args);
                    return Math.abs(args[0]);
                };
            case "min":
                return (double... args) -> {
                    Arrays.sort(args);
                    return args[0];
                };
            case "max":
                return (double... args) -> {
                    Arrays.sort(args);
                    return args[args.length - 1];
                };

            default:
                return null;
        }
    }

    private void checkVarCount(double... args) {
        if (args.length != countVar)
            throw new IllegalArgumentException("Wrong number of parameters in standard function");
    }

    public static Functions get(String name) {
        for (Functions c : Functions.values()) {
            if (c.getName().equals(name.toLowerCase())) {
                return c;
            }
        }
        return null;
    }

    /**
     * @return the countVar
     */
    public Integer getCountVar() {
        return countVar;
    }
}
