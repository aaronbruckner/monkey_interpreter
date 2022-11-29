package com.aarbru.monkey.ast.nodes;

public class AstLetStatement extends AstStatement {
    private final AstIdentifier identifier;
    private final AstExpression value;

    public AstLetStatement(AstIdentifier identifier, AstExpression value) {
        this.identifier = identifier;
        this.value = value;
    }

    public AstIdentifier getIdentifier() {
        return identifier;
    }
    
    public AstExpression getValue() {
        return value;
    }
}
