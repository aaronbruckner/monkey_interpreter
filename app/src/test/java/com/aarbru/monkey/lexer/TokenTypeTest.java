package com.aarbru.monkey.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TokenTypeTest {
    @Test
    public void testTokenCount() {
        // If someone is adding tokens, they should add more tests.
        assertEquals(27, TokenType.values().length);
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
        assertEquals("true", TokenType.LIT_TRUE.literal);
        assertEquals("false", TokenType.LIT_FALSE.literal);
    }

    @Test
    public void testTokenOperators() {
        assertEquals("=", TokenType.OP_ASSIGN.literal);
        assertEquals("-", TokenType.OP_MINUS.literal);
        assertEquals("+", TokenType.OP_PLUS.literal);
        assertEquals("/", TokenType.OP_SLASH.literal);
        assertEquals("*", TokenType.OP_ASTERISK.literal);
        assertEquals("==", TokenType.OP_EQUALS.literal);
        assertEquals("!=", TokenType.OP_NOT_EQUALS.literal);
        assertEquals("!", TokenType.OP_EXCLAMATION.literal);
        assertEquals("<", TokenType.OP_LESS_THAN.literal);
        assertEquals(">", TokenType.OP_GREATER_THAN.literal);
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
        assertEquals("fn", TokenType.KEY_FUNC.literal);
        assertEquals("let", TokenType.KEY_LET.literal);
        assertEquals("if", TokenType.KEY_IF.literal);
        assertEquals("else", TokenType.KEY_ELSE.literal);
        assertEquals("return", TokenType.KEY_RETURN.literal);
    }
}
