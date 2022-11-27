package com.aarbru.monkey.lexer;

/**
 * Defines all the kinds of tokens we can see when parsing Monkey source.
 */
public enum TokenType {
    ILLEGAL,
    EOF,
    IDENTIFIER,
    
    // Literal
    LIT_INT,
    
    // Operators
    OP_ASSIGN("="),
    OP_MINUS("-"),
    OP_PLUS("+"),
    OP_SLASH("/"),
    OP_ASTERISK("*"),
    OP_EQUALS("=="),
    OP_NOT_EQUALS("!="),
    OP_EXCLAMATION("!"),
    OP_LESS_THAN("<"),
    OP_GREATER_THAN(">"),


    // Delimiters
    DEL_COMMA(","),
    DEL_SEMICOLON(";"),
    DEL_PAREN_R(")"),
    DEL_PAREN_L("("),
    DEL_BRACE_R("}"),
    DEL_BRACE_L("{"),


    // Keywords
    KEY_FUNC("fn"),
    KEY_LET("let");

    public final String literal;

    TokenType() {
        this("");
    }

    TokenType(String literal){
        this.literal = literal;
    }
}


