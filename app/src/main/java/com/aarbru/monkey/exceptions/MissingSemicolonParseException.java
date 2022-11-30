package com.aarbru.monkey.exceptions;

public class MissingSemicolonParseException extends RuntimeException{
    public MissingSemicolonParseException() {
        super("Expected semicolon but found none");
    }
}
