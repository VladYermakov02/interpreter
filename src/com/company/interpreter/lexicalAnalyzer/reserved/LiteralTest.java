package com.company.interpreter.lexicalAnalyzer.reserved;

import com.company.interpreter.lexicalAnalyzer.types.Type;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;


class LiteralTest {

    private Literal literal;
    private Type type_res;

    @Test
    void test1() {
        literal = new Literal();
        Type type_exp;

        type_exp = literal.getTypes().get(1);
        type_res = literal.containsNumber("4", literal.getTypes(), 1, 0);

        assertEquals(type_exp, type_res);
    }

    @Test
    void test3() {
        literal = new Literal();

        type_res = literal.containsNumber("text", literal.getTypes(), 1, 0);

        assertEquals(null, type_res);
    }

    @Test
    void test4() {
        literal = new Literal();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        literal.containsNumber(null, literal.getTypes(), 1, 0);

        // Put things back
        System.out.flush();
        System.setOut(old);

        assertEquals("Exception appeared on this value: null", baos.toString().trim());
    }

    @Test
    void test5() {
        literal = new Literal();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        literal.containsNumber("4", null, 1, 0);

        // Put things back
        System.out.flush();
        System.setOut(old);

        assertEquals("Exception appeared on this value: 4", baos.toString().trim());
    }

    /*@Test
    void test2() {
        literal = new Literal();

        literal.containsNumber(1, literal.getTypes(), 1, 0);
        //cannot catch an error message, but an error is expected
    }

    @Test
    void test6() {
        literal = new Literal();

        literal.containsNumber("4", literal.getTypes(), 100000000000, 0);
        //cannot catch an error message, but an error is expected (too large number)
    }

    @Test
    void test7() {
        literal = new Literal();

        literal.containsNumber("4", literal.getTypes(), 1.5, 0);
        //cannot catch an error message, but an error is expected (incompatible types)
    }

    @Test
    void test8() {
        literal = new Literal();

        literal.containsNumber("4", literal.getTypes(), 1, 100000000000);
        //cannot catch an error message, but an error is expected (too large number)
    }

    @Test
    void test9() {
        literal = new Literal();

        literal.containsNumber("4", literal.getTypes(), 1, 1.5);
        //cannot catch an error message, but an error is expected (incompatible types)
    }*/
}