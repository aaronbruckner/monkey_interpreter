package com.aarbru.monkey.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.aarbru.monkey.ast.nodes.AstLetStatement;
import com.aarbru.monkey.ast.nodes.AstStatement;
import com.aarbru.monkey.lexer.Lexer;

public class ParserTest {
    @Test
    void testParseProgramParsesLetStatement() {
        var source = "let test = 5; let another = 7;";
        var parser = new Parser(new Lexer(source));

        Program program = parser.parseProgram();
        
        var statements = program.getStatements();
        assertNotNull(statements);
        assertEquals(2, statements.size());
        assertLetStatement(statements.get(0), "test");
        assertLetStatement(statements.get(1), "another");
        // TODO - Assert expression once parsing is done.
    }

    private AstLetStatement assertLetStatement(AstStatement node, String expectedName) {
        assertInstanceOf(AstLetStatement.class, node);
        AstLetStatement letStatement = (AstLetStatement) node;
        assertEquals(expectedName, letStatement.getIdentifier().getName());
        return letStatement;
    }
}