package com.company.interpreter.lexicalAnalyzer.launch;

import com.company.interpreter.lexicalAnalyzer.tokens.Occurrence;
import com.company.interpreter.lexicalAnalyzer.tokens.Token;
import com.company.interpreter.lexicalAnalyzer.tokens.TokenDatabase;

import java.util.Map;

public class TokenOutput {
    TokenDatabase tokenDatabase;

    public TokenOutput(TokenDatabase tokenDatabase) {
        this.tokenDatabase = tokenDatabase;
    }

    public void print() {
        System.out.printf("%5s %15s %15s %15s %15s %15s %20s %15s", "Id", "Value", "Line", "Start Position", "Width", "Type Category", "Type Name", "Type Value");
        System.out.println();
        for (Token t : tokenDatabase.getTokens()) {
            System.out.printf("%5s %15s %15s %15s %15s %15s %20s %15s", t.getId(), t.getValue(), (t.getLine() + 1),
                    (t.getStartPosition() + 1), t.getWidth(), t.getType().getTypeCategory(), t.getType().getTypeName(), t.getType().getTypeValue());
            System.out.println();
        }
    }

    public void printIdentifiers() {
        System.out.print("\nIdentifiers");
        for (Map.Entry<String, Occurrence> entry : tokenDatabase.getIdentifierTokens().entrySet()) {
            System.out.println();
            System.out.println(entry.getKey() + "\n\tOccurrences: " + entry.getValue().getOccurrences()
                    + "\n\tOccurrence lines: " + entry.getValue().getOccurrenceLinesString()
                    + "\n\tOccurrence positions: " + entry.getValue().getOccurrencePositionsString());
        }
    }

    public void printLiterals() {
        System.out.print("\nLiterals");
        for (Map.Entry<String, Occurrence> entry : tokenDatabase.getLiteralTokens().entrySet()) {
            System.out.println();
            System.out.println(entry.getKey() + "\n\tOccurrences: " + entry.getValue().getOccurrences()
                    + "\n\tOccurrence lines: " + entry.getValue().getOccurrenceLinesString()
                    + "\n\tOccurrence positions: " + entry.getValue().getOccurrencePositionsString());
        }
        System.out.println("\n");
    }
}
