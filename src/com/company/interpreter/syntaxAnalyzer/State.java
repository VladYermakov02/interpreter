package com.company.interpreter.syntaxAnalyzer;

import java.util.List;

public class State {
    private final Integer stateIndex;
    // tokens expected by state. When to add
    // situation 1: current state is token
    // situation 2: suppressError - true. It means state has other rules below, so it needs to know needed tokens beforehand
    private final List<String> expectedTokens;
    // index of the next machine move
    private final Integer nextStep;
    // whether state is token (+ <end>)
    private final Boolean isToken;
    // next state index (if needed). Needed only if there is other state rule below current
    private final Integer nextState;
    // if state has other rules below
    private final Boolean suppressError;

    // link to method for code generation
    private Runnable codeGenAction;

    public State(Integer stateIndex, List<String> expectedTokens, Integer nextStep, Boolean isToken, Integer nextState, Boolean suppressError) {
        this.stateIndex = stateIndex;
        this.expectedTokens = expectedTokens;
        this.nextStep = nextStep;
        this.isToken = isToken;
        this.nextState = nextState;
        this.suppressError = suppressError;
    }

    public State(Integer stateIndex, List<String> expectedTokens, Integer nextStep, Boolean isToken, Integer nextState, Boolean suppressError, Runnable codeGenAction) {
        this.stateIndex = stateIndex;
        this.expectedTokens = expectedTokens;
        this.nextStep = nextStep;
        this.isToken = isToken;
        this.nextState = nextState;
        this.suppressError = suppressError;
        this.codeGenAction = codeGenAction;
    }

    public Integer getStateIndex() {
        return stateIndex;
    }

    public List<String> getExpectedTokens() {
        return expectedTokens;
    }

    public Integer getNextStep() {
        return nextStep;
    }

    public Boolean getIsToken() {
        return isToken;
    }

    public Integer getNextState() {
        return nextState;
    }

    public Boolean getSuppressError() {
        return suppressError;
    }

    public Runnable getCodeGenAction() {
        return codeGenAction;
    }
}
