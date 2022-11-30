package com.aarbru.monkey.ast.nodes;

public class AstReturnStatement extends AstStatement {
    private final AstExpression value;

    public AstReturnStatement(AstExpression value) {
        this.value = value;
    }

    public AstExpression getValue() {
        return value;
    }
}
