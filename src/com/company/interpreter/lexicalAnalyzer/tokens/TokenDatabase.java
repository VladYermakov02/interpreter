package com.company.interpreter.lexicalAnalyzer.tokens;

import java.util.*;

public class TokenDatabase {
    private final List<String> inputtedLines;
    private List<Token> tokens = new ArrayList<>();
    private final Map<String, Occurrence> identifierTokens = new HashMap<>();
    private final Map<String, Occurrence> literalTokens = new HashMap<>();

    public TokenDatabase(ArrayList<String> inputtedLines) {
        this.inputtedLines = inputtedLines;
    }

    public String getLine(int index) {
        return inputtedLines.get(index);
    }

    public List<String> getInputtedLines() {
        return inputtedLines;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public void addIdentifierToken(String value, int occurrenceLine, int occurrencePosition) {
        if (identifierTokens.containsKey(value)) {
            identifierTokens.get(value).addOccurrence(occurrenceLine, occurrencePosition);
        } else {
            identifierTokens.put(value, new Occurrence(occurrenceLine, occurrencePosition));
        }
    }

    public void addLiteralToken(String value, int occurrenceLine, int occurrencePosition) {
        if (literalTokens.containsKey(value)) {
            literalTokens.get(value).addOccurrence(occurrenceLine, occurrencePosition);
        } else {
            literalTokens.put(value, new Occurrence(occurrenceLine, occurrencePosition));
        }
    }

    public Map<String, Occurrence> getIdentifierTokens() {
        return identifierTokens;
    }

    public Map<String, Occurrence> getLiteralTokens() {
        return literalTokens;
    }
}
