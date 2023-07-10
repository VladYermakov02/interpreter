package com.company.interpreter.syntaxAnalyzer;

import com.company.compiler.FuncDescriptor;
import com.company.interpreter.lexicalAnalyzer.tokens.Token;
import com.company.interpreter.lexicalAnalyzer.tokens.TokenDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

// TODO: 02.06.2022 LL1GrammarFinal has issues so does state-machine. Write it again from the scratch
public class SyntaxAnalyzer {
    private final List<Token> tokens;
    private final List<State> states;
    private Integer tokenIndex, stateIndex;
    private final Stack<Integer> nextStateIndexes;
    private List<String> expectedTokensError;

    private LinkedList<FuncDescriptor> funcDescriptors;
    private FuncDescriptor currFuncDescriptor;

    public SyntaxAnalyzer(TokenDatabase tokenDatabase, LinkedList<FuncDescriptor> funcDescriptors) {
        this.tokens = tokenDatabase.getTokens();
        this.states = new LinkedList<>();
        this.tokenIndex = 0;
        this.stateIndex = 1;
        this.nextStateIndexes = new Stack<>();
        this.expectedTokensError = new LinkedList<>();
        this.funcDescriptors = funcDescriptors;
        fillStateMachine();
        this.currFuncDescriptor = new FuncDescriptor();
    }

    // returns index of the next condition
    public void analyze() {
        try {
            do {
                stateIndex = analyzeOneState(tokens);
            } while (stateIndex > 0);
            if (stateIndex == 0) {
                System.out.println("Syntax is correct");
            } else if (stateIndex == -1) {
                throw new Exception(errorConcat());
            } else {
                throw new Exception("Something went wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // adds new element to func descriptor list, its return type
    private void f1AddReturnType() {
        // if statement is true that means this is var declaration but not function
        if (Objects.equals(tokens.get(tokenIndex + 2).getType().getTypeValue(), "=")) {
            return;
        }
        currFuncDescriptor.setReturnType(tokens.get(tokenIndex).getType().getTypeValue());
    }

    // adds func name
    private void f2AddFuncName() {
        // if statement is true that means this is var declaration but not function
        if (Objects.equals(tokens.get(tokenIndex + 2).getType().getTypeValue(), "=")) {
            return;
        }
        currFuncDescriptor.setFuncName(tokens.get(tokenIndex).getValue());
    }

    // adds first token id in current block
    // called on the token: '{'
    private void f3AddFirstToken() {
        // if body is empty
        if (Objects.equals(tokens.get(tokenIndex + 1).getType().getTypeValue(), "}")) {
            currFuncDescriptor.setFirstTokenId(-1);
            return;
        }
        // next token is the first
        currFuncDescriptor.setFirstTokenId(tokenIndex + 1);
    }

    // adds last token id in current block
    // called on the token: '}'
    private void f4AddLastToken() {
        // if body is empty
        if (Objects.equals(tokens.get(tokenIndex - 1).getType().getTypeValue(), "{")) {
            currFuncDescriptor.setLastTokenId(-1);
            f5AddFuncDescriptor();
        }
        // previous token is the last
        currFuncDescriptor.setLastTokenId(tokenIndex - 1);

        // we're at function end, so we can create our filled descriptor
        f5AddFuncDescriptor();
    }

    private void f6AddArgument() {
        currFuncDescriptor.setFuncArg(tokens.get(tokenIndex).getValue(), tokens.get(tokenIndex + 1).getValue());
    }

    private void f5AddFuncDescriptor() {
        if (!currFuncDescriptor.containNulls()) {
            funcDescriptors.add(currFuncDescriptor);
        }
        currFuncDescriptor = new FuncDescriptor();
    }

    private int analyzeOneState(List<Token> tokens) {
        if (tokenIndex == tokens.size())
            return 0;
        State state = states.get(stateIndex);

        List<String> waitedTokens = state.getExpectedTokens();
        this.expectedTokensError = waitedTokens;

        if (!waitedTokens.isEmpty() && !waitedTokens.contains(tokens.get(tokenIndex).getType().getTypeValue())) {
            if (state.getSuppressError())
                return state.getStateIndex() + 1;
            return -1;
        }

        if (state.getCodeGenAction() != null) {
            state.getCodeGenAction().run();
        }

        int nextState = state.getNextState();
        int nextStep = state.getNextStep();

        if (nextState != 0)
            nextStateIndexes.push(nextState);
        if (state.getIsToken())
            tokenIndex++;
        if (nextStep == 0)
            return 0;
        if (nextStep > 0)
            return nextStep;

        // final transitionIndex
        return nextStateIndexes.pop();
    }

    private String errorConcat() {
        Token currToken = tokens.get(tokenIndex);
        StringBuilder error = new StringBuilder("Syntax error on: ");

        error.append(currToken.getValue())
                .append("\nOn line: ").append(currToken.getLine() + 1)
                .append("\nOn position: ").append(currToken.getStartPosition() + 1)
                .append("\nExpected tokens: ");

        if (this.expectedTokensError.isEmpty()) {
            return error.toString();
        }
        for (String wt : this.expectedTokensError) {
            error.append(wt).append(" | ");
        }
        error.delete(error.length() - 3, error.length());
        return error.toString();
    }

    // @formatter:off
    private void fillStateMachine() {
        List<String> empty = new LinkedList();
        List<String> stop = new LinkedList();
        stop.add("stop");
        List<String> strLiteral = new LinkedList();
        strLiteral.add("String Literal");
        List<String> digLiteral = new LinkedList();
        digLiteral.add("Number");
        List<String> id = new LinkedList();
        id.add("id");
        List<String> roundLB = new LinkedList();
        roundLB.add("(");
        List<String> roundRB = new LinkedList();
        roundRB.add(")");
        List<String> curlyLB = new LinkedList();
        curlyLB.add("{");
        List<String> curlyRB = new LinkedList();
        curlyRB.add("}");
        List<String> squareLB = new LinkedList();
        squareLB.add("[");
        List<String> squareRB = new LinkedList();
        squareRB.add("]");
        List<String> semicolon = new LinkedList();
        semicolon.add(";");
        List<String> coma = new LinkedList();
        coma.add(",");
        List<String> dot = new LinkedList();
        dot.add(".");
        List<String> colon = new LinkedList();
        colon.add(":");
        List<String> caret = new LinkedList();
        caret.add("^");
        List<String> addition = new LinkedList();
        addition.add("+");
        List<String> subtraction = new LinkedList();
        subtraction.add("-");
        List<String> multiplication = new LinkedList();
        multiplication.add("*");
        List<String> division = new LinkedList();
        division.add("/");
        List<String> assignment = new LinkedList();
        assignment.add("=");
        List<String> less = new LinkedList();
        less.add("<");
        List<String> greater = new LinkedList();
        greater.add(">");
        List<String> equal = new LinkedList();
        equal.add("==");
        List<String> notEqual = new LinkedList();
        notEqual.add("!=");
        List<String> greaterEqual = new LinkedList();
        greaterEqual.add(">=");
        List<String> lessEqual = new LinkedList();
        lessEqual.add("<=");
        List<String> _new = new LinkedList();
        _new.add("new");
        List<String> _for = new LinkedList();
        _for.add("for");
        List<String> _if = new LinkedList();
        _if.add("if");
        List<String> _else = new LinkedList();
        _else.add("else");
        List<String> _void = new LinkedList();
        _void.add("void");
        List<String> _int = new LinkedList();
        _int.add("int");
        List<String> _float = new LinkedList();
        _float.add("float");
        List<String> _string = new LinkedList();
        _string.add("string");
        List<String> _bool = new LinkedList();
        _bool.add("bool");
        List<String> _enum = new LinkedList();
        _enum.add("enum");
        List<String> _set = new LinkedList();
        _set.add("set");
        List<String> _return = new LinkedList();
        _return.add("return");
        List<String> _null = new LinkedList();
        _null.add("null");
        List<String> simpleTypes = new LinkedList();
        simpleTypes.add("int");
        simpleTypes.add("float");
        simpleTypes.add("string");
        simpleTypes.add("bool");
        List<String> varInit = new LinkedList();
        varInit.add("Number");
        varInit.add("String Literal");
        List<String> op1 = new LinkedList();
        op1.add("+");
        op1.add("-");
        List<String> op2 = new LinkedList();
        op2.add("*");
        op2.add("/");
        List<String> exp = new LinkedList();
        exp.add("(");
        exp.add("id");
        exp.add("Number");
        exp.add("String Literal");
        this.states.add(new State(0, empty, 0, false, 0, false));
        this.states.add(new State(1, empty, 3, false, 2, false));
        this.states.add(new State(2, stop, 0, true, 0, false));
        this.states.add(new State(3, empty, 6, false, 5, true));
        this.states.add(new State(4, stop, -1, false, 0, false));
        this.states.add(new State(5, empty, 3, false, 0, false));
        this.states.add(new State(6, _void, 9, true, 0, true, this::f1AddReturnType));
        this.states.add(new State(7, _enum, 11, true, 0, true));
        this.states.add(new State(8, simpleTypes, 23, false, 13, false, this::f1AddReturnType));
        this.states.add(new State(9, id, 10, true, 0, false, this::f2AddFuncName));
        this.states.add(new State(10, empty, 20, false, 0, false));
        this.states.add(new State(11, id, 12, true, 0, false));
        this.states.add(new State(12, empty, 22, false, 0, false));
        this.states.add(new State(13, id, 14, true, 0, false, this::f2AddFuncName));
        this.states.add(new State(14, empty, 15, false, 0, false));
        this.states.add(new State(15, assignment, 17, true, 0, true));
        this.states.add(new State(16, empty, 20, false, 0, false));
        this.states.add(new State(17, empty, 18, false, 0, false));
        this.states.add(new State(18, empty, 27, false, 19, false));
        this.states.add(new State(19, semicolon, 1, true, 0, false));
        this.states.add(new State(20, empty, 28, false, 21, false));
        this.states.add(new State(21, empty, 32, false, 0, false));
        this.states.add(new State(22, empty, 78, false, 0, false));
        this.states.add(new State(23, _int, -1, true, 0, true));
        this.states.add(new State(24, _float, -1, true, 0, true));
        this.states.add(new State(25, _string, -1, true, 0, true));
        this.states.add(new State(26, _bool, -1, true, 0, false));
        this.states.add(new State(27, empty, 35, false, 0, false));
        this.states.add(new State(28, roundLB, 30, true, 0, true));
        this.states.add(new State(29, empty, -1, false, 0, false));
        this.states.add(new State(30, empty, 58, false, 31, false));
        this.states.add(new State(31, roundRB, -1, true, 0, false));
        this.states.add(new State(32, curlyLB, 33, true, 0, false, this::f3AddFirstToken));
        this.states.add(new State(33, empty, 69, false, 34, false));
        this.states.add(new State(34, curlyRB, 1, true, 0, false, this::f4AddLastToken));
        this.states.add(new State(35, empty, 42, false, 36, false));
        this.states.add(new State(36, empty, 37, false, 0, false));
        this.states.add(new State(37, op1, 40, false, 39, true));
        this.states.add(new State(38, empty, -1, false, 0, false));
        this.states.add(new State(39, empty, 42, false, 0, false));
        this.states.add(new State(40, addition, -1, true, 0, true));
        this.states.add(new State(41, subtraction, -1, true, 0, false));
        this.states.add(new State(42, exp, 49, false, 43, false));
        this.states.add(new State(43, empty, 44, false, 0, false));
        this.states.add(new State(44, op2, 47, false, 46, true));
        this.states.add(new State(45, empty, -1, false, 0, false));
        this.states.add(new State(46, exp, 49, false, 0, false));
        this.states.add(new State(47, multiplication, -1, true, 0, true));
        this.states.add(new State(48, division, -1, true, 0, false));
        this.states.add(new State(49, roundLB, 53, true, 0, true));
        this.states.add(new State(50, id, 55, true, 0, true));
        this.states.add(new State(51, digLiteral, -1, true, 0, true));
        this.states.add(new State(52, strLiteral, -1, true, 0, false));
        this.states.add(new State(53, empty, 35, false, 54, false));
        this.states.add(new State(54, roundRB, -1, true, 0, false));
        this.states.add(new State(55, empty, 56, false, 0, false));
        this.states.add(new State(56, empty, 93, false, 0, true));
        this.states.add(new State(57, empty, -1, false, 0, false));
        this.states.add(new State(58, empty, 61, false, 60, true));
        this.states.add(new State(59, empty, -1, false, 0, false));
        this.states.add(new State(60, empty, 65, false, 0, false));
        this.states.add(new State(61, empty, 63, false, 62, false, this::f6AddArgument));
        this.states.add(new State(62, id, -1, true, 0, false));
        this.states.add(new State(63, simpleTypes, 23, false, 0, true));
        this.states.add(new State(64, _enum, -1, true, 0, false));
        this.states.add(new State(65, coma, 67, true, 0, true));
        this.states.add(new State(66, empty, -1, false, 0, false));
        this.states.add(new State(67, empty, 61, false, 68, false));
        this.states.add(new State(68, empty, 65, false, 0, false));
        this.states.add(new State(69, id, 71, true, 0, true));
        this.states.add(new State(70, empty, -1, false, 0, false));
        this.states.add(new State(71, empty, 74, false, 72, false));
        this.states.add(new State(72, semicolon, 73, true, 0, false));
        this.states.add(new State(73, empty, 69, false, 0, false));
        this.states.add(new State(74, assignment, 77, true, 0, true));
        this.states.add(new State(75, empty, 78, false, 0, true));
        this.states.add(new State(76, empty, 93, false, 0, false));
        this.states.add(new State(77, empty, 27, false, 0, false));
        this.states.add(new State(78, curlyLB, 79, true, 0, false));
        this.states.add(new State(79, empty, 81, false, 80, false));
        this.states.add(new State(80, curlyRB, -1, true, 0, false));
        this.states.add(new State(81, id, 83, true, 0, true));
        this.states.add(new State(82, empty, -1, false, 0, false));
        this.states.add(new State(83, empty, 84, false, 0, false));
        this.states.add(new State(84, empty, 86, false, 85, false));
        this.states.add(new State(85, empty, 89, false, 0, false));
        this.states.add(new State(86, colon, 88, true, 0, true));
        this.states.add(new State(87, empty, -1, false, 0, false));
        this.states.add(new State(88, digLiteral, -1, true, 0, false));
        this.states.add(new State(89, coma, 91, true, 0, true));
        this.states.add(new State(90, empty, -1, false, 0, false));
        this.states.add(new State(91, id, 92, true, 0, false));
        this.states.add(new State(92, empty, 84, false, 0, false));
        this.states.add(new State(93, roundLB, 95, true, 0, true));
        this.states.add(new State(94, empty, -1, false, 0, false));
        this.states.add(new State(95, empty, 97, false, 96, false));
        this.states.add(new State(96, roundRB, -1, true, 0, false));
        this.states.add(new State(97, id, 99, true, 0, true));
        this.states.add(new State(98, empty, -1, false, 0, false));
        this.states.add(new State(99, empty, 100, false, 0, false));
        this.states.add(new State(100, coma, 102, true, 0, true));
        this.states.add(new State(101, empty, -1, false, 0, false));
        this.states.add(new State(102, id, 103, true, 0, false));
        this.states.add(new State(103, empty, 100, false, 0, false));

        /*List<String> empty = new LinkedList<>();

        //literals
        List<String> stop = new LinkedList<>();
        stop.add("stop");

        //literals
        List<String> strLiteral = new LinkedList<>();
        strLiteral.add("String Literal");
        List<String> digLiteral = new LinkedList<>();
        digLiteral.add("Number");
        //id
        List<String> id = new LinkedList<>();
        id.add("id");
        //dividers
        List<String> roundLB = new LinkedList<>();
        roundLB.add("(");
        List<String> roundRB = new LinkedList<>();
        roundRB.add(")");
        List<String> curlyLB = new LinkedList<>();
        curlyLB.add("{");
        List<String> curlyRB = new LinkedList<>();
        curlyRB.add("}");
        List<String> squareLB = new LinkedList<>();
        squareLB.add("[");
        List<String> squareRB = new LinkedList<>();
        squareRB.add("]");
        List<String> semicolon = new LinkedList<>();
        semicolon.add(";");
        List<String> coma = new LinkedList<>();
        coma.add(",");
        List<String> dot = new LinkedList<>();
        dot.add(".");
        List<String> colon = new LinkedList<>();
        colon.add(":");
        //operators
        List<String> caret = new LinkedList<>();
        caret.add("^");
        List<String> addition = new LinkedList<>();
        addition.add("+");
        List<String> subtraction = new LinkedList<>();
        subtraction.add("-");
        List<String> multiplication = new LinkedList<>();
        multiplication.add("*");
        List<String> division = new LinkedList<>();
        division.add("/");
        List<String> assignment = new LinkedList<>();
        assignment.add("=");
        List<String> less = new LinkedList<>();
        less.add("<");
        List<String> greater = new LinkedList<>();
        greater.add(">");
        List<String> equal = new LinkedList<>();
        equal.add("==");
        List<String> notEqual = new LinkedList<>();
        notEqual.add("!=");
        List<String> greaterEqual = new LinkedList<>();
        greaterEqual.add(">=");
        List<String> lessEqual = new LinkedList<>();
        lessEqual.add("<=");
        //keywords
        List<String> _new = new LinkedList<>();
        _new.add("new");
        List<String> _for = new LinkedList<>();
        _for.add("for");
        List<String> _if = new LinkedList<>();
        _if.add("if");
        List<String> _else = new LinkedList<>();
        _else.add("else");
        List<String> _void = new LinkedList<>();
        _void.add("void");
        List<String> _int = new LinkedList<>();
        _int.add("int");
        List<String> _float = new LinkedList<>();
        _float.add("float");
        List<String> _string = new LinkedList<>();
        _string.add("string");
        List<String> _bool = new LinkedList<>();
        _bool.add("bool");
        List<String> _enum = new LinkedList<>();
        _enum.add("enum");
        List<String> _set = new LinkedList<>();
        _set.add("set");
        List<String> _return = new LinkedList<>();
        _return.add("return");
        List<String> _null = new LinkedList<>();
        _null.add("null");
        List<String> simpleTypes = new LinkedList<>();
        simpleTypes.add("int");
        simpleTypes.add("float");
        simpleTypes.add("string");
        simpleTypes.add("bool");
        List<String> varInit = new LinkedList<>();
        varInit.add("Number");
        varInit.add("String Literal");*/

        /*states.add(new State( 0,        empty,  0, false, 0, false));
        states.add(new State( 1,        empty,  3, false, 2, false));
        states.add(new State( 2,         stop,  0,  true, 0, false));
        states.add(new State( 3,        empty,  6, false, 5,  true));
        states.add(new State( 4,         stop, -1, false, 0, false));
        states.add(new State( 5,        empty,  3, false, 0, false));
        states.add(new State( 6,        _void,  9,  true, 0,  true, this::f1AddReturnType));
        states.add(new State( 7,        _enum, 11,  true, 0,  true));
        states.add(new State( 8,  simpleTypes, 23, false,13, false, this::f1AddReturnType));
        states.add(new State( 9,           id, 10,  true, 0, false, this::f2AddFuncName));
        states.add(new State(10,        empty, 20, false, 0, false));
        states.add(new State(11,           id, 12,  true, 0, false));
        states.add(new State(12,        empty, 22, false, 0, false));
        states.add(new State(13,           id, 14,  true, 0, false, this::f2AddFuncName));
        states.add(new State(14,        empty, 15, false, 0, false));
        states.add(new State(15,   assignment, 17,  true, 0,  true));
        states.add(new State(16,        empty, 20, false, 0, false));
        states.add(new State(17,        empty, 18, false, 0, false));
        states.add(new State(18,      varInit, 27, false,19, false));
        states.add(new State(19,    semicolon,  1,  true, 0, false));
        states.add(new State(20,        empty, 30, false,21, false));
        states.add(new State(21,        empty, 34, false, 0, false));
        states.add(new State(22,        empty, 34, false, 0, false));
        states.add(new State(23,         _int, -1,  true, 0,  true));
        states.add(new State(24,       _float, -1,  true, 0,  true));
        states.add(new State(25,      _string, -1,  true, 0,  true));
        states.add(new State(26,        _bool, -1,  true, 0, false));
        states.add(new State(27,   digLiteral, -1,  true, 0,  true));
        states.add(new State(28,   strLiteral, -1,  true, 0,  true));
        states.add(new State(29,        empty, 42, false, 0, false));
        states.add(new State(30,      roundLB, 32,  true, 0,  true));
        states.add(new State(31,        empty, -1, false, 0, false));
        states.add(new State(32,        empty, 37, false,33, false));
        states.add(new State(33,      roundRB, -1,  true, 0, false));
        states.add(new State(34,      curlyLB, 35,  true, 0, false));
        states.add(new State(35,        empty, 38, false,36, false));
        states.add(new State(36,      curlyRB,  1,  true, 0, false));
        states.add(new State(37,        empty, -1, false, 0, false));
        states.add(new State(38,        empty, 40, false, 0,  true));
        states.add(new State(39,        empty, 41, false, 0, false));
        states.add(new State(40,        empty, -1, false, 0, false, this::f3AddFirstToken));
        states.add(new State(41,        empty, -1, false, 0, false));
        states.add(new State(42,        empty, -1, false, 0, false));*/
    }
    // @formatter:on
}

// Simple finite-state machine for test
/*List<String> int_void = new LinkedList<>();
        int_void.add("int");
        int_void.add("void");
        states.add(new State( 0,    empty,  0, false, 0, false));
        states.add(new State( 1,    empty,  3, false, 2, false));
        states.add(new State( 2,     stop,  0,  true, 0, false));
        states.add(new State( 3,     stop, -1, false, 0,  true));
        states.add(new State( 4,    empty,  6, false, 5, false));
        states.add(new State( 5,    empty,  3, false, 0, false));
        states.add(new State( 6, int_void, 11, false, 7, false));
        states.add(new State( 7,       id,  8,  true, 0, false));
        states.add(new State( 8,  roundLB,  9,  true, 0, false));
        states.add(new State( 9,  roundRB, 10,  true, 0, false));
        states.add(new State(10,  curlyLB, 13, false, 0, false));
        states.add(new State(11,    _void, -1,  true, 0,  true));
        states.add(new State(12,     _int, -1,  true, 0, false));
        states.add(new State(13,  curlyLB, 14,  true, 0, false));
        states.add(new State(14,  curlyRB,  1,  true, 0, false));*/
