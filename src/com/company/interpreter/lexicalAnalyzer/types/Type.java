package com.company.interpreter.lexicalAnalyzer.types;

public class Type {
    private final String typeCategory;
    private final String typeName;
    private final String typeValue;

    public Type(String typeCategory, String typeName, String typeValue) {
        this.typeCategory = typeCategory;
        this.typeName = typeName;
        this.typeValue = typeValue;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeValue() {
        return typeValue;
    }
}
