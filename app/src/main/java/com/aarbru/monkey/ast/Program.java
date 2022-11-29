package com.aarbru.monkey.ast;

import java.util.List;

import com.aarbru.monkey.ast.nodes.AstStatement;

public class Program {
    private final List<AstStatement> statements;

    public Program(List<AstStatement> statements) {
        this.statements = statements;
    }

    public List<AstStatement> getStatements() {
        return statements;
    }
}
