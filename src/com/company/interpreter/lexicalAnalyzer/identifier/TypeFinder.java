package com.company.interpreter.lexicalAnalyzer.identifier;

import com.company.interpreter.lexicalAnalyzer.types.Type;

import java.util.ArrayList;
import java.util.Objects;

public class TypeFinder {

    public Type find(String value, ArrayList<Type> typeValues) {
        for (Type vt : typeValues) {
            if (Objects.equals(value, vt.getTypeValue())) {
                return vt;
            }
        }
        return null;
    }

    public Type find(String value, ArrayList<Type> types, int line, int startPos) {
        return null;
    }
}
