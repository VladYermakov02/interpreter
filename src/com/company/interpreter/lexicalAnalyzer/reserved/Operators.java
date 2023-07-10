package com.company.interpreter.lexicalAnalyzer.reserved;

import com.company.interpreter.lexicalAnalyzer.identifier.TypeFinder;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.lexicalAnalyzer.types.TypeTags;

import java.util.ArrayList;

public class Operators extends TypeFinder {

    ArrayList<Type> valuedTypes = new ArrayList<>();

    public Operators() {
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Caret", "^"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Addition", "+"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Subtraction", "-"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Multiplication", "*"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Division", "/"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Assignment", "="));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Less", "<"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Greater", ">"));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "Equal", "=="));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "NotEqual", "!="));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "GreaterEqual", ">="));
        valuedTypes.add(new Type(TypeTags.OPERATOR, "LessEqual", "<="));
    }

    @Override
    public Type find(String value, ArrayList<Type> valuedTypes) {
        return super.find(value, valuedTypes);
    }

    public ArrayList<Type> getValuedTypes() {
        return valuedTypes;
    }
}
