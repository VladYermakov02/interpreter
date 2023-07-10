package com.company.interpreter.lexicalAnalyzer.tokens;

import java.util.LinkedList;
import java.util.List;

public class Occurrence {
    private int occurrences = 0;
    private final List<Integer> occurrenceLines = new LinkedList<>();
    private final List<Integer> occurrencePositions = new LinkedList<>();

    Occurrence(int occurrenceLine, int occurrencePosition) {
        occurrenceLines.add(occurrenceLine);
        occurrencePositions.add(occurrencePosition);
        occurrences++;
    }

    public void addOccurrence(int occurrenceLine, int occurrencePosition) {
        occurrenceLines.add(occurrenceLine);
        occurrencePositions.add(occurrencePosition);
        occurrences++;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public String getOccurrenceLinesString() {
        StringBuilder lines = new StringBuilder();
        for (int ol : occurrenceLines) {
            lines.append(ol + 1);
            lines.append(" ");
        }
        return lines.toString();
    }

    public String getOccurrencePositionsString() {
        StringBuilder positions = new StringBuilder();
        for (int op : occurrencePositions) {
            positions.append(op + 1);
            positions.append(" ");
        }
        return positions.toString();
    }
}
