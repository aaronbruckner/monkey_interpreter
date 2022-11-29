package com.aarbru.monkey.ast.nodes;

public class AstIdentifier {
    private final String name;

    public AstIdentifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
