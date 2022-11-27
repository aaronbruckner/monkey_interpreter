package com.aarbru.monkey.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LexerTest {
    
    @Test
    void testNextTokenBasicTokens() {
        final String source = "+-/*{}();,=";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.OP_PLUS, tokens[0].getType());
        assertEquals(TokenType.OP_MINUS, tokens[1].getType());
        assertEquals(TokenType.OP_DIVIDE, tokens[2].getType());
        assertEquals(TokenType.OP_MULTI, tokens[3].getType());
        assertEquals(TokenType.DEL_BRACE_L, tokens[4].getType());
        assertEquals(TokenType.DEL_BRACE_R, tokens[5].getType());
        assertEquals(TokenType.DEL_PAREN_L, tokens[6].getType());
        assertEquals(TokenType.DEL_PAREN_R, tokens[7].getType());
        assertEquals(TokenType.DEL_SEMICOLON, tokens[8].getType());
        assertEquals(TokenType.DEL_COMMA, tokens[9].getType());
        assertEquals(TokenType.OP_ASSIGN, tokens[10].getType());
        assertEquals(TokenType.EOF, tokens[11].getType());
    }

    @Test
    void testNextTokenIgnoresSpaces() {
        final String source = """
                + -
                  * 
                /
                """;
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.OP_PLUS, tokens[0].getType());
        assertEquals(TokenType.OP_MINUS, tokens[1].getType());
        assertEquals(TokenType.OP_MULTI, tokens[2].getType());
        assertEquals(TokenType.OP_DIVIDE, tokens[3].getType());
        assertEquals(TokenType.EOF, tokens[4].getType());
    }

    @Test
    void testNextTokenIgnoresTabs() {
        final String source = "\t+\t-";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.OP_PLUS, tokens[0].getType());
        assertEquals(TokenType.OP_MINUS, tokens[1].getType());
        assertEquals(TokenType.EOF, tokens[2].getType());
    }

    private Token[] extractTokens(Lexer lexer, int size){
        var tokens = new Token[size];

        for (int i = 0; i < size; i++) {
            tokens[i] = lexer.nextToken();
        }

        return tokens;
    }
}
