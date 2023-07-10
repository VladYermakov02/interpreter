package com.company.interpreter.lexicalAnalyzer.identifier;

import com.company.interpreter.lexicalAnalyzer.reserved.*;
import com.company.interpreter.lexicalAnalyzer.tokens.Token;
import com.company.interpreter.lexicalAnalyzer.tokens.TokenDatabase;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.lexicalAnalyzer.types.Values;

public class TypeIdentifier {
    private final TokenDatabase tokenDatabase;
    private final Dividers dividers = new Dividers();
    private final Operators operators = new Operators();
    private final Keywords keywords = new Keywords();
    private int index = 0;
    private final Identifier identifier = new Identifier();
    private final Literal literal = new Literal();

    public TypeIdentifier(TokenDatabase tokenDatabase) {
        this.tokenDatabase = tokenDatabase;
    }

    public void identify() {
        for (; index < tokenDatabase.getTokens().size(); index++) {
            Token token = tokenDatabase.getTokens().get(index);
            try {
                // Shows only the 1st Unexpected character
                if (identifyDivider(token) || identifyKeyword(token) || identifyOperator(token)) {

                } else if (isLiteral(token.getValue())) {
                    identifyLiteral(token);
                } else if (!isLiteral(token.getValue())) {
                    identifyIdentifier(token);
                } else {
                    throw new Exception("Type is unknown in this token: " + token.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private boolean identifyDivider(Token token) {
        Type valuedType = dividers.find(token.getValue(), dividers.getValuedTypes());
        if (valuedType != null) {
            tokenDatabase.getTokens().get(index).setType(valuedType);
            return true;
        }
        return false;
    }

    private boolean identifyKeyword(Token token) {
        Type valuedType = keywords.find(token.getValue(), keywords.getValuedTypes());
        if (valuedType != null) {
            tokenDatabase.getTokens().get(index).setType(valuedType);
            return true;
        }
        return false;
    }

    private boolean identifyOperator(Token token) {
        Type valuedType = operators.find(token.getValue(), operators.getValuedTypes());
        if (valuedType != null) {
            tokenDatabase.getTokens().get(index).setType(valuedType);
            return true;
        }
        return false;
    }

    private boolean identifyLiteral(Token token) {
        Type Type = literal.find(token.getValue(), literal.getTypes(), token.getLine(), token.getStartPosition());
        if (Type != null) {
            tokenDatabase.getTokens().get(index).setType(Type);
            tokenDatabase.addLiteralToken(token.getValue(), token.getLine(), token.getStartPosition());
            return true;
        }
        return false;
    }

    private boolean identifyIdentifier(Token token) {
        Type Type = identifier.find(token.getValue(), identifier.getTypes(), token.getLine(), token.getStartPosition());
        if (Type != null) {
            tokenDatabase.getTokens().get(index).setType(Type);
            tokenDatabase.addIdentifierToken(token.getValue(), token.getLine(), token.getStartPosition());
            return true;
        }
        return false;
    }

    private boolean isLiteral(String value) {
        return value.matches(Values.LITERAL) || value.contains(Values.STRING_SIGN);
    }
}
