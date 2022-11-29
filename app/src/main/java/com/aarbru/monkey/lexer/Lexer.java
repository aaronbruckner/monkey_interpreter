package com.aarbru.monkey.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for converting a source file into a stream of tokens.
 * 
 * This is the first step in interpreting Monkey code.
 */
public class Lexer {

    private final String source;
    private int cursor;
    private Token peekToken;

    public Lexer(String source) {
        this.source = source;
    }

    public Token peekNextToken() {
        if (peekToken == null) {
            peekToken = nextToken();
        }

        return peekToken;
    }

    /**
     * Allows you to incrementally get the next token from the source that's
     * currently being parsed. Consumes the token. 
     * 
     * @return the next token in the source file. Otherwise returns EOF token.
     */
    public Token nextToken() {
        if (peekToken != null) {
            var result = peekToken;
            peekToken = null;
            return result;
        }

        skipWhitespace();

        if (cursor >= source.length()) {
            return new Token(TokenType.EOF);
        }

        char nextChar = source.charAt(cursor);
        if (isLetter(nextChar)) {
            return handleWordLikeToken();
        }

        if (Character.isDigit(nextChar)) {
            return handleNumberLikeToken();
        }
        
        return handleSingleCharToken();
    }

    /**
     * Consumes the source file and returns a list of every token found within the source.
     * @return a list of all tokens found within the source.
     */
    public List<Token> getAllTokens() {
        var tokens = new ArrayList<Token>();

        while(true) {
            tokens.add(nextToken());
            
            if (tokens.get(tokens.size() - 1).getType() == TokenType.EOF) {
                return tokens;
            }
        }

    }

    /**
     * Resets the token stream to the start of the source file.
     */
    public void reset() {

    }

    private boolean isLetter(char c) {
        return Character.isAlphabetic(c) || c == '_';
    }

    private void skipWhitespace() {
        while (cursor < source.length() && Character.isWhitespace(source.charAt(cursor))) {
            cursor++;
        }
    }

    private Token handleWordLikeToken() {
        assert cursor < source.length();
        assert isLetter(source.charAt(cursor));

        var sb = new StringBuilder();
        char nextChar;
        while(cursor < source.length() && isLetter(nextChar = source.charAt(cursor))) {
            cursor++;
            sb.append(nextChar);
        }

        String word = sb.toString();

        TokenType type = switch(word) {
            case "fn" -> TokenType.KEY_FUNC;
            case "let" -> TokenType.KEY_LET;
            case "if" -> TokenType.KEY_IF;
            case "else" -> TokenType.KEY_ELSE;
            case "return" -> TokenType.KEY_RETURN;
            case "true" -> TokenType.LIT_TRUE;
            case "false" -> TokenType.LIT_FALSE;
            default -> TokenType.IDENTIFIER;
        };
        
        return new Token(type, word);
    }

    private Token handleNumberLikeToken() {
        assert cursor < source.length();
        assert Character.isDigit(source.charAt(cursor));

        var sb = new StringBuilder();
        char nextChar;
        while (cursor < source.length() && Character.isDigit((nextChar = source.charAt(cursor)))) {
            cursor++;
            sb.append(nextChar);
        }

        String word = sb.toString();

        return new Token(TokenType.LIT_INT, word);
    }

    private Token handleSingleCharToken() {
        assert cursor < source.length();

        char nextChar = source.charAt(cursor++);
        String value = String.valueOf(nextChar);
        TokenType type = switch (nextChar) {
            case '=' -> {
                if (peekNextChar() == '=') {
                    cursor++;
                    value = "==";
                    yield TokenType.OP_EQUALS;
                }
                yield TokenType.OP_ASSIGN;
            }
            case '!' -> {
                if (peekNextChar() == '=') {
                    cursor++;
                    value = "!=";
                    yield TokenType.OP_NOT_EQUALS;
                }
                yield TokenType.OP_EXCLAMATION;
            }
            case '/' -> TokenType.OP_SLASH;
            case '-' -> TokenType.OP_MINUS;
            case '*' -> TokenType.OP_ASTERISK;
            case '+' -> TokenType.OP_PLUS;
            case '{' -> TokenType.DEL_BRACE_L;
            case '}' -> TokenType.DEL_BRACE_R;
            case '(' -> TokenType.DEL_PAREN_L;
            case ')' -> TokenType.DEL_PAREN_R;
            case ';' -> TokenType.DEL_SEMICOLON;
            case ',' -> TokenType.DEL_COMMA;
            case '<' -> TokenType.OP_LESS_THAN;
            case '>' -> TokenType.OP_GREATER_THAN;
            default -> TokenType.ILLEGAL;
        };

        return new Token(type, value);
    }

    private char peekNextChar() {
        if (cursor >= source.length()) {
            return Character.MIN_VALUE;
        }

        return source.charAt(cursor);
    }
}
