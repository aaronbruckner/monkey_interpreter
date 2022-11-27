package com.aarbru.monkey.lexer;

/**
 * Represents a complete token which was extracted from a
 * source file.
 */
public class Token {
    private final TokenType type;
    private final String value;

    Token(TokenType type) {
        this(type, "");
    }

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
