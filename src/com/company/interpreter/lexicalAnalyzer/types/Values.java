package com.company.interpreter.lexicalAnalyzer.types;

public class Values {
    public static final String DELIMITERS = "();{}[],:~' ";
    public static final String COMMENT_SIGN = "~";
    public static final String STRING_SIGN = "'";

    public static final String LITERAL = "[0-9-.]+";
    public static final char DOT = '.';
    public static final String LITERAL_AFTER_DOT = "[0-9]+";

    public static final String IDENTIFIER = "[a-zA-Z0-9_]+";
    public static final String IDENTIFIER_FIRST_LETTER = "[a-zA-Z_]+";
    //public static final String IDENTIFIER_WRONG = "[~!@#$%^&*=*/+,|\\'\":<>?]+";
}
