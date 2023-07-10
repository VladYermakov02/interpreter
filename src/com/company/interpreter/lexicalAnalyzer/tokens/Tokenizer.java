package com.company.interpreter.lexicalAnalyzer.tokens;

import com.company.interpreter.lexicalAnalyzer.types.Values;

import java.util.*;

public class Tokenizer {
    private final TokenDatabase tokenDatabase;
    private final List<StringTokenizer> stringTokenizerArr = new ArrayList<>();

    public Tokenizer(TokenDatabase tokenDatabase) {
        this.tokenDatabase = tokenDatabase;
    }

    public List<Token> get() {
        tokenizeToString();
        makeTokens();
        return tokenDatabase.getTokens();
    }

    private void tokenizeToString() {
        int i = 0;
        try {
            int size = tokenDatabase.getInputtedLines().size();
            for (; i < size; i++) {
                stringTokenizerArr.add(new StringTokenizer(tokenDatabase.getLine(i), Values.DELIMITERS, true));
            }
        } catch (NullPointerException e) {
            System.out.println("Line is null: " + i);
            e.printStackTrace();
        }
    }

    // in tokenizeToString string was tokenized with Values.DELIMITERS but with saving delimiters as tokens
    // so here we need to put away spaces and concat comments and strings
    // because comments and strings need to be saved as one entity (words between the first and the last sign)
    private void makeTokens() {
        int tokenId = -1;
        int size = stringTokenizerArr.size(), tokenStartPos;
        boolean isComment = false, isString = false;
        StringBuilder strForConcat = new StringBuilder();
        for (int i = 0; i < size; i++) {
            tokenStartPos = 0;
            while (stringTokenizerArr.get(i).hasMoreTokens()) {
                String tmpStr = stringTokenizerArr.get(i).nextToken();
                // concatString
                if (Objects.equals(tmpStr, "'") || isString) {
                    if (Objects.equals(tmpStr, "'")) {
                        isString = !isString;
                    }
                    strForConcat.append(tmpStr);
                    tmpStr = "";
                }
                // concatComment
                if (Objects.equals(tmpStr, "~") || isComment) {
                    if (Objects.equals(tmpStr, "~")) {
                        isComment = !isComment;
                    }
                    tmpStr = " ";
                }
                if (!isComment && !isString) {
                    // skip if space
                    if (Objects.equals(tmpStr, "\n") || Objects.equals(tmpStr, " ") || Objects.equals(tmpStr, "\t")) {
                        tokenStartPos += tmpStr.length();
                        continue;
                    }
                    tmpStr += strForConcat;
                    strForConcat = new StringBuilder();
                    tokenDatabase.addToken(new Token(++tokenId, tmpStr, i, tokenStartPos, tmpStr.length()));
                    tokenStartPos += tmpStr.length();
                }
            }
        }
    }
}
