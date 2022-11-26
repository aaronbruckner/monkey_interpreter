package com.aarbru.monkey.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TokenTest {
    @Test
    public void testTokenCount() {
        // If someone is adding tokens, they should add more tests.
        assertEquals(17, Token.values().length);
    }

    @Test
    public void testTokenSpecials() {
        assertEquals("", Token.EOF.literal);
        assertEquals("", Token.ILLEGAL.literal);
        assertEquals("", Token.IDENTIFIER.literal);
    }

    @Test
    public void testTokenLiterals() {
        assertEquals("", Token.LIT_INT.literal);
    }

    @Test
    public void testTokenOperators() {
        assertEquals("=", Token.OP_ASSIGN.literal);
        assertEquals("-", Token.OP_MINUS.literal);
        assertEquals("+", Token.OP_PLUS.literal);
        assertEquals("/", Token.OP_DIVIDE.literal);
        assertEquals("*", Token.OP_MULT.literal);
    }

    @Test
    public void testTokenDelimiters() {
        assertEquals(",", Token.DEL_COMMA.literal);
        assertEquals(";", Token.DEL_SEMICOLON.literal);
        assertEquals(")", Token.DEL_PAREN_R.literal);
        assertEquals("(", Token.DEL_PAREN_L.literal);
        assertEquals("}", Token.DEL_BRACE_R.literal);
        assertEquals("{", Token.DEL_BRACE_L.literal);
    }

    @Test
    public void testTokenKeywords() {
        assertEquals("function", Token.KEY_FUNC.literal);
        assertEquals("let", Token.KEY_LET.literal);
    }
}
