package com.company.interpreter.lexicalAnalyzer.reserved;

import com.company.interpreter.lexicalAnalyzer.identifier.TypeFinder;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.lexicalAnalyzer.types.TypeTags;
import com.company.interpreter.lexicalAnalyzer.types.Values;

import java.util.ArrayList;

public class Identifier extends TypeFinder {

    ArrayList<Type> types = new ArrayList<>();

    public Identifier() {
        /*types.add(new Type(TypeTags.IDENTIFIER, "int"));
        types.add(new Type(TypeTags.IDENTIFIER, "float"));
        types.add(new Type(TypeTags.IDENTIFIER, "string"));
        types.add(new Type(TypeTags.IDENTIFIER, "bool"));
        types.add(new Type(TypeTags.IDENTIFIER, "enum"));
        types.add(new Type(TypeTags.IDENTIFIER, "set"));*/
        types.add(new Type(TypeTags.IDENTIFIER, "id", "id"));
    }

    @Override
    public Type find(String value, ArrayList<Type> types, int line, int startPos) {
        try {
            char[] chars = value.toCharArray();
            StringBuilder errMessSpaces = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (i == 0 && String.valueOf(chars[i]).matches(Values.IDENTIFIER_FIRST_LETTER)) {

                } else if (String.valueOf(chars[i]).matches(Values.IDENTIFIER)) {

                } else {
                    String errorMessage = value + "\n" + errMessSpaces + "^";
                    throw new Exception("Unexpected character\nLine: " + (line + 1) + "\nPosition: " + (startPos + i + 1) + "\n" + errorMessage);
                }
                errMessSpaces.append(" ");
            }
            return types.get(0);
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
