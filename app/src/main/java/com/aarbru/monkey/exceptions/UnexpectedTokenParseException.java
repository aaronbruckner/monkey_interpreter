package com.aarbru.monkey.exceptions;

import com.aarbru.monkey.lexer.Token;
import com.aarbru.monkey.lexer.TokenType;

/**
 * Thrown if the parser finds a token in the wrong place when it's expecting something
 * specific.
 */
public class UnexpectedTokenParseException extends RuntimeException {
    public UnexpectedTokenParseException(Token token, TokenType expectedType) {
        super(String.format("Unexpected token found. Expected %s, found %s(%s) at row: %d, col: %d", expectedType, token.getType(), token.getValue(), token.getRow(), token.getCol()));
    }
}
