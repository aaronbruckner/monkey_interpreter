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
        char nextChar;
        while(true) {
            if (cursor >= source.length()) {
                return new Token(TokenType.EOF);
            }

            nextChar = source.charAt(cursor++);

            if (Character.isWhitespace(nextChar)) {
                continue;
            }

            break;
        }
        
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
