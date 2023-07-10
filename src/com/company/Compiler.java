package com.company;

import com.company.compiler.DescriptorsOutput;
import com.company.compiler.FuncDescriptor;
import com.company.interpreter.lexicalAnalyzer.identifier.TypeIdentifier;
import com.company.interpreter.lexicalAnalyzer.launch.File;
import com.company.interpreter.lexicalAnalyzer.launch.TokenOutput;
import com.company.interpreter.lexicalAnalyzer.reserved.Literal;
import com.company.interpreter.lexicalAnalyzer.tokens.TokenDatabase;
import com.company.interpreter.lexicalAnalyzer.tokens.Tokenizer;
import com.company.interpreter.lexicalAnalyzer.types.Type;
import com.company.interpreter.syntaxAnalyzer.SyntaxAnalyzer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Compiler {

    // white box testing UnitTesting lab1
    private static final Literal literal = new Literal();

    public static void main(String[] args) {
        TokenDatabase tokenDatabase = analyzeLexis();
        analyzeSyntax(tokenDatabase);

        // white box testing UnitTesting lab1
        /*
        Type Type;
        Type = literal.find("1.607e", literal.getTypes(), 0, 0);
        System.out.println(Type.getTypeName());
        Type = literal.find("1", literal.getTypes(), 0, 0);
        System.out.println(Type.getTypeName());
        Type = literal.find(".@", literal.getTypes(), 0, 0);
        System.out.println(Type.getTypeName());
        Type = literal.find("@", literal.getTypes(), 0, 0);
        System.out.println(Type.getTypeName());
        */
    }

    private static void analyzeSyntax(TokenDatabase tokenDatabase) {
        LinkedList<FuncDescriptor> funcDescriptors = new LinkedList<>();
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(tokenDatabase, funcDescriptors);
        syntaxAnalyzer.analyze();

        DescriptorsOutput descriptorsOutput = new DescriptorsOutput();
        descriptorsOutput.printFunc(funcDescriptors);
    }

    private static TokenDatabase analyzeLexis() {
//        File file = new File("E:\\Progs\\Java\\java_projects\\labs\\Translators\\Compiler\\src\\com\\company\\programs\\lexicalRight.txt");
//        File file = new File("E:\\Progs\\Java\\java_projects\\labs\\Translators\\Compiler\\src\\com\\company\\programs\\lexicalWrong.txt");
        File file = new File("E:\\Progs\\Java\\java_projects\\labs\\Translators\\Compiler\\src\\com\\company\\programs\\syntaxTesting.txt");

        ArrayList<String> lines = file.getLines();

        TokenDatabase tokenDatabase = new TokenDatabase(lines);

        Tokenizer tokenizer = new Tokenizer(tokenDatabase);
        tokenDatabase.setTokens(tokenizer.get());

        TypeIdentifier typeIdentifier = new TypeIdentifier(tokenDatabase);
        typeIdentifier.identify();

        TokenOutput tokenOutput = new TokenOutput(tokenDatabase);
        tokenOutput.print();
        tokenOutput.printIdentifiers();
        tokenOutput.printLiterals();

        return tokenDatabase;
    }
}
