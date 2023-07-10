package com.company.interpreter.lexicalAnalyzer.tokens;

import com.company.interpreter.lexicalAnalyzer.types.Type;

public class Token {
    private final int id;
    private final String value;
    private final int line;
    private final int startPosition;
    private final int width;
    private Type type;

    public Token(int id, String value, int line, int startPosition, int width) {
        this.id = id;
        this.value = value;
        this.line = line;
        this.startPosition = startPosition;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getWidth() {
        return width;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
