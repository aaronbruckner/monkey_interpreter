package com.aarbru.monkey.ast.nodes;

public class AstExpressionStatement extends AstStatement {
    private final AstExpression value;

    public AstExpressionStatement(AstExpression value) {
        this.value = value;
    }

    public AstExpression getValue() {
        return value;
    }
}
