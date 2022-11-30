package com.aarbru.monkey.lexer;

/**
 * Represents a complete token which was extracted from a
 * source file.
 */
public class Token {
    private final TokenType type;
    private final String value;
    private final int row;
    private final int col;

    Token(TokenType type) {
        this(type, "");
    }

    Token(TokenType type, String value) {
        this(type, value, 0, 0);
    }

    Token(TokenType type, String value, int row, int col) {
        this.type = type;
        this.value = value;
        this.row = row;
        this.col = col;
    }

    /**
     * Allows you to determine what kind of token was actually parsed.
     * 
     * @return the primary token type associated with the parsed token.
     */
    public TokenType getType() {
        return type;
    }

    /**
     * @return the extracted string value associated with the token. This
     * matters for tokens like identifiers or literal values.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the source row where the token originated.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the source col within the row where the token originated.
     */
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Token{ Type=" + type + ", value='" + value + "' }";
    }
}
