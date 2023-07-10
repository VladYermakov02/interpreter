package com.company.interpreter.lexicalAnalyzer.reserved;

import com.company.interpreter.lexicalAnalyzer.identifier.TypeFinder;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.lexicalAnalyzer.types.TypeTags;

import java.util.*;

public class Dividers extends TypeFinder {

    ArrayList<Type> valuedTypes = new ArrayList<>();

    public Dividers() {
        valuedTypes.add(new Type(TypeTags.DIVIDER, "LeftRoundBracket", "("));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "RightRoundBracket", ")"));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "LeftCurlyBracket", "{"));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "RightCurlyBracket", "}"));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "LeftSquareBracket", "["));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "RightSquareBracket", "]"));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "Semicolon", ";"));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "Comma", ","));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "Dot", "."));
        valuedTypes.add(new Type(TypeTags.DIVIDER, "Colon", ":"));
        /*valuedTypes.add(new ValuedType(TypeTags.DIVIDER, "TextSign", "'"));
        valuedTypes.add(new ValuedType(TypeTags.DIVIDER, "CommentSign", "~"));*/
    }

    @Override
    public Type find(String value, ArrayList<Type> valuedTypes) {
        return super.find(value, valuedTypes);
    }

    public ArrayList<Type> getValuedTypes() {
        return valuedTypes;
    }
}
