package com.company.interpreter.lexicalAnalyzer.reserved;

import com.company.interpreter.lexicalAnalyzer.identifier.TypeFinder;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.lexicalAnalyzer.types.TypeTags;
import com.company.interpreter.lexicalAnalyzer.types.Values;

import java.util.ArrayList;

public class Literal extends TypeFinder {

    ArrayList<Type> types = new ArrayList<>();

    public Literal() {
        /*types.add(new Type(TypeTags.LITERAL, "int"));
        types.add(new Type(TypeTags.LITERAL, "float"));
        types.add(new Type(TypeTags.LITERAL, "string"));
        types.add(new Type(TypeTags.LITERAL, "bool"));
        types.add(new Type(TypeTags.LITERAL, "enum"));
        types.add(new Type(TypeTags.LITERAL, "set"));*/
        types.add(new Type(TypeTags.LITERAL, "String Literal", "String Literal"));
        types.add(new Type(TypeTags.LITERAL, "Number", "Number"));
    }

    @Override
    public Type find(String value, ArrayList<Type> types, int line, int startPos) {
        if (value.contains(Values.STRING_SIGN)) {
            return types.get(0);
        } else {
            return containsNumber(value, types, line, startPos);
        }
    }

    // had to delete private type so I could do tests
    Type containsNumber(String value, ArrayList<Type> types, int line, int startPos) {
        try {
            boolean isAfterDot = false;
            char[] chars = value.toCharArray();
            StringBuilder errMessSpaces = new StringBuilder();

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == Values.DOT) {
                    isAfterDot = true;
                    continue;
                }
                errMessSpaces.append(" ");
                if (String.valueOf(chars[i]).matches(Values.IDENTIFIER_FIRST_LETTER)) {
                    return null;
                } else if (!isAfterDot && String.valueOf(chars[i]).matches(Values.LITERAL)) {

                } else if (isAfterDot && String.valueOf(chars[i]).matches(Values.LITERAL_AFTER_DOT)) {

                } else {
                    String errorMessage = value + "\n" + errMessSpaces + "^";
                    throw new Exception("Unexpected character\nLine: " + (line + 1) + "\nPosition: " + (startPos + i + 1) + "\n" + errorMessage);
                }
            }
            return types.get(1);
        } catch (NullPointerException e) {
            System.out.println("Exception appeared on this value: " + value);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.find(value, types, line, startPos);
    }

    public ArrayList<Type> getTypes() {
        return types;
    }
}
