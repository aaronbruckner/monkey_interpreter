package com.aarbru.monkey.ast.nodes;

import com.aarbru.monkey.lexer.Token;

public class AstIdentifierExpression extends AstExpression {
    private final String name;
    
    public AstIdentifierExpression(Token token) {
        this.name = token.getValue();
    }

    public String getName() {
        return name;
    }
}
