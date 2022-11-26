package com.aarbru.monkey.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TokenTypeTest {
    @Test
    public void testTokenCount() {
        // If someone is adding tokens, they should add more tests.
        assertEquals(17, TokenType.values().length);
    }

    @Test
    public void testTokenSpecials() {
        assertEquals("", TokenType.EOF.literal);
        assertEquals("", TokenType.ILLEGAL.literal);
        assertEquals("", TokenType.IDENTIFIER.literal);
    }

    @Test
    public void testTokenLiterals() {
        assertEquals("", TokenType.LIT_INT.literal);
    }

    @Test
    public void testTokenOperators() {
        assertEquals("=", TokenType.OP_ASSIGN.literal);
        assertEquals("-", TokenType.OP_MINUS.literal);
        assertEquals("+", TokenType.OP_PLUS.literal);
        assertEquals("/", TokenType.OP_DIVIDE.literal);
        assertEquals("*", TokenType.OP_MULT.literal);
    }

    @Test
    public void testTokenDelimiters() {
        assertEquals(",", TokenType.DEL_COMMA.literal);
        assertEquals(";", TokenType.DEL_SEMICOLON.literal);
        assertEquals(")", TokenType.DEL_PAREN_R.literal);
        assertEquals("(", TokenType.DEL_PAREN_L.literal);
        assertEquals("}", TokenType.DEL_BRACE_R.literal);
        assertEquals("{", TokenType.DEL_BRACE_L.literal);
    }

    @Test
    public void testTokenKeywords() {
        assertEquals("function", TokenType.KEY_FUNC.literal);
        assertEquals("let", TokenType.KEY_LET.literal);
    }
}
