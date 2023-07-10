package com.company.interpreter.lexicalAnalyzer.reserved;

import com.company.interpreter.lexicalAnalyzer.identifier.TypeFinder;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.lexicalAnalyzer.types.TypeTags;

import java.util.ArrayList;

public class Keywords extends TypeFinder {
    ArrayList<Type> valuedTypes = new ArrayList<>();

    public Keywords() {
        valuedTypes.add(new Type(TypeTags.KEYWORD, "new", "new"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "for", "for"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "if", "if"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "else", "else"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "void", "void"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "int", "int"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "float", "float"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "string", "string"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "bool", "bool"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "enum", "enum"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "set", "set"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "return", "return"));
        valuedTypes.add(new Type(TypeTags.KEYWORD, "null", "null"));
    }

    @Override
    public Type find(String value, ArrayList<Type> valuedTypes) {
        return super.find(value, valuedTypes);
    }

    public ArrayList<Type> getValuedTypes() {
        return valuedTypes;
    }
}
