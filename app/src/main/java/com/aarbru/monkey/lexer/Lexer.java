package com.aarbru.monkey.lexer;

/**
 * Responsible for converting a source file into a stream of tokens.
 * 
 * This is the first step in interpreting Monkey code.
 */
public class Lexer {

    private final String source;

    private int cursor;

    public Lexer(String source) {
        this.source = source;
    }

    /**
     * Allows you to incrementally get the next token from the source that's
     * currently being parsed. Consumes the token. 
     * 
     * @return the next token in the source file. Otherwise returns EOF token.
     */
    public Token nextToken() {
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
        TokenType type = switch (nextChar) {
            case '=' -> TokenType.OP_ASSIGN;
            case '/' -> TokenType.OP_DIVIDE;
            case '-' -> TokenType.OP_MINUS;
            case '*' -> TokenType.OP_MULTI;
            case '+' -> TokenType.OP_PLUS;
            case '{' -> TokenType.DEL_BRACE_L;
            case '}' -> TokenType.DEL_BRACE_R;
            case '(' -> TokenType.DEL_PAREN_L;
            case ')' -> TokenType.DEL_PAREN_R;
            case ';' -> TokenType.DEL_SEMICOLON;
            case ',' -> TokenType.DEL_COMMA;
            default -> throw new RuntimeException("Not Yet Implemented");
        };

        return new Token(type);
    }
}