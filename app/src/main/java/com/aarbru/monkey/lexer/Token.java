package com.aarbru.monkey.lexer;

/**
 * Represents a complete token which was extracted from a
 * source file.
 */
public class Token {
    private final TokenType type;

    Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }
}
