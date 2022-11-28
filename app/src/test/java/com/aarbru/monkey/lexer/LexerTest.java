package com.aarbru.monkey.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LexerTest {
    
    @Test
    void testNextTokenBasicTokens() {
        final String source = "+-/*{}();,=!<>";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.OP_PLUS, tokens[0].getType());
        assertEquals(TokenType.OP_MINUS, tokens[1].getType());
        assertEquals(TokenType.OP_SLASH, tokens[2].getType());
        assertEquals(TokenType.OP_ASTERISK, tokens[3].getType());
        assertEquals(TokenType.DEL_BRACE_L, tokens[4].getType());
        assertEquals(TokenType.DEL_BRACE_R, tokens[5].getType());
        assertEquals(TokenType.DEL_PAREN_L, tokens[6].getType());
        assertEquals(TokenType.DEL_PAREN_R, tokens[7].getType());
        assertEquals(TokenType.DEL_SEMICOLON, tokens[8].getType());
        assertEquals(TokenType.DEL_COMMA, tokens[9].getType());
        assertEquals(TokenType.OP_ASSIGN, tokens[10].getType());
        assertEquals(TokenType.OP_EXCLAMATION, tokens[11].getType());
        assertEquals(TokenType.OP_LESS_THAN, tokens[12].getType());
        assertEquals(TokenType.OP_GREATER_THAN, tokens[13].getType());
        assertEquals(TokenType.EOF, tokens[14].getType());
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
        assertEquals(TokenType.OP_ASTERISK, tokens[2].getType());
        assertEquals(TokenType.OP_SLASH, tokens[3].getType());
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

    @Test
    void testNextTokenParsesKeywords() {
        final String source = """
                fn+let
                let fn true
                false if else return""";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.KEY_FUNC, tokens[0].getType());
        assertEquals(TokenType.OP_PLUS, tokens[1].getType());
        assertEquals(TokenType.KEY_LET, tokens[2].getType());
        assertEquals(TokenType.KEY_LET, tokens[3].getType());
        assertEquals(TokenType.KEY_FUNC, tokens[4].getType());
        assertEquals(TokenType.LIT_TRUE, tokens[5].getType());
        assertEquals(TokenType.LIT_FALSE, tokens[6].getType());
        assertEquals(TokenType.KEY_IF, tokens[7].getType());
        assertEquals(TokenType.KEY_ELSE, tokens[8].getType());
        assertEquals(TokenType.KEY_RETURN, tokens[9].getType());
        assertEquals(TokenType.EOF, tokens[10].getType());
    }

    @Test
    void testNextTokenParsesIdentifiers() {
        final String source = """
            test_+_helloWorld
            normal What_ever""";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.IDENTIFIER, tokens[0].getType());
        assertEquals("test_", tokens[0].getValue());
        assertEquals(TokenType.OP_PLUS, tokens[1].getType());
        assertEquals(TokenType.IDENTIFIER, tokens[2].getType());
        assertEquals("_helloWorld", tokens[2].getValue());
        assertEquals(TokenType.IDENTIFIER, tokens[3].getType());
        assertEquals("normal", tokens[3].getValue());
        assertEquals(TokenType.IDENTIFIER, tokens[4].getType());
        assertEquals("What_ever", tokens[4].getValue());
        assertEquals(TokenType.EOF, tokens[5].getType());
    }

    @Test
    void testNextTokenParsesLiteralNumbers() {
        final String source = """
            123+8965
            let 88""";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.LIT_INT, tokens[0].getType());
        assertEquals("123", tokens[0].getValue());
        assertEquals(TokenType.OP_PLUS, tokens[1].getType());
        assertEquals(TokenType.LIT_INT, tokens[2].getType());
        assertEquals("8965", tokens[2].getValue());
        assertEquals(TokenType.KEY_LET, tokens[3].getType());
        assertEquals(TokenType.LIT_INT, tokens[4].getType());
        assertEquals("88", tokens[4].getValue());
        assertEquals(TokenType.EOF, tokens[5].getType());
    }

    @Test
    void testNextTokenParsesTwoSymbolTokens() {
        final String source = "==+!=+=";
        var lexer = new Lexer(source);

        var tokens = extractTokens(lexer, source.length() + 1);

        assertEquals(TokenType.OP_EQUALS, tokens[0].getType());
        assertEquals(TokenType.OP_PLUS, tokens[1].getType());
        assertEquals(TokenType.OP_NOT_EQUALS, tokens[2].getType());
        assertEquals(TokenType.OP_PLUS, tokens[3].getType());
        assertEquals(TokenType.OP_ASSIGN, tokens[4].getType());
        assertEquals(TokenType.EOF, tokens[5].getType());
    }

    @Test
    void testGetAllTokensReturnsAListOfEveryTokenInTheSource() {
        final String source = "=-";
        var lexer = new Lexer(source);

        var tokens = lexer.getAllTokens();

        assertNotNull(tokens);
        assertEquals(3, tokens.size());
        assertEquals(TokenType.OP_ASSIGN, tokens.get(0).getType());
        assertEquals(TokenType.OP_MINUS, tokens.get(1).getType());
        assertEquals(TokenType.EOF, tokens.get(2).getType());
    }

    private Token[] extractTokens(Lexer lexer, int size){
        var tokens = new Token[size];

        for (int i = 0; i < size; i++) {
            tokens[i] = lexer.nextToken();
        }

        return tokens;
    }
}
