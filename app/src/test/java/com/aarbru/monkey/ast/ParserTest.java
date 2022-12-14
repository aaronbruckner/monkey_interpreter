package com.aarbru.monkey.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.aarbru.monkey.ast.nodes.AstExpression;
import com.aarbru.monkey.ast.nodes.AstExpressionStatement;
import com.aarbru.monkey.ast.nodes.AstIdentifierExpression;
import com.aarbru.monkey.ast.nodes.AstLetStatement;
import com.aarbru.monkey.ast.nodes.AstReturnStatement;
import com.aarbru.monkey.ast.nodes.AstStatement;
import com.aarbru.monkey.exceptions.MissingSemicolonParseException;
import com.aarbru.monkey.exceptions.UnexpectedTokenParseException;
import com.aarbru.monkey.lexer.Lexer;

public class ParserTest {

    @Test
    void testParseProgramParsesLetStatement() {
        var source = "let test = 5; let another = 7;";
        var parser = new Parser(new Lexer(source));

        Program program = parser.parseProgram();
        
        var statements = assertProgramStatements(program, 2);
        assertLetStatement(statements.get(0), "test");
        assertLetStatement(statements.get(1), "another");
        // TODO - Assert expression once parsing is done.
    }

    @Test
    void testParseProgramParsesLetStatementMissingIdentifier() {
        var source = "let 123 = 5;";
        var parser = new Parser(new Lexer(source));

        var exception = assertThrowsExactly(UnexpectedTokenParseException.class, () -> parser.parseProgram());

        assertEquals("Unexpected token found. Expected IDENTIFIER, found LIT_INT(123) at row: 1, col: 5", exception.getMessage());
    }

    @Test
    void testParseProgramParsesLetStatementMissingEquals() {
        var source = "let test 5;";
        var parser = new Parser(new Lexer(source));

        var exception = assertThrowsExactly(UnexpectedTokenParseException.class, () -> parser.parseProgram());

        assertEquals("Unexpected token found. Expected OP_ASSIGN, found LIT_INT(5) at row: 1, col: 10", exception.getMessage());
    }

    @Test
    void testParseProgramParsesLetStatementMissingSemicolon() {
        var source = "let test = 5; \n let testTwo = 7";
        var parser = new Parser(new Lexer(source));

        var exception = assertThrowsExactly(MissingSemicolonParseException.class, () -> parser.parseProgram());

        assertEquals("Expected semicolon but found none", exception.getMessage());
    }

    @Test
    void testParseProgramParsesReturnStatement() {
        var source = "return 5; return 10;";
        var parser = new Parser(new Lexer(source));

        Program program = parser.parseProgram();
        
        var statements = assertProgramStatements(program, 2);
        assertReturnStatement(statements.get(0));
        assertReturnStatement(statements.get(1));
        // TODO - Assert expression once parsing is done.
    }

    @Test
    void testParseProgramParsesIdentifierExpressionStatement() {
        var source = "foobar;";
        var parser = new Parser(new Lexer(source));

        Program program = parser.parseProgram();

        var statements = assertProgramStatements(program, 1);
        var expressionStatement = assertExpressionStatement(statements.get(0));
        var identifierExpression = assertIdentifierExpression(expressionStatement.getValue());
        assertEquals(identifierExpression.getName(), "foobar");
    }

    private List<AstStatement> assertProgramStatements(Program program, int expectedSize) {
        var statements = program.getStatements();
        assertNotNull(statements);
        assertEquals(expectedSize, statements.size());
        
        return statements;
    }

    private AstIdentifierExpression assertIdentifierExpression(AstExpression expression) {
        assertNotNull(expression);
        assertInstanceOf(AstIdentifierExpression.class, expression);

        return (AstIdentifierExpression) expression;
    }

    private AstExpressionStatement assertExpressionStatement(AstStatement node) {
        assertInstanceOf(AstExpressionStatement.class, node);
        return (AstExpressionStatement) node;
    }

    private AstReturnStatement assertReturnStatement(AstStatement node) {
        assertInstanceOf(AstReturnStatement.class, node);
        return (AstReturnStatement) node;
    }

    private AstLetStatement assertLetStatement(AstStatement node, String expectedName) {
        assertInstanceOf(AstLetStatement.class, node);
        AstLetStatement letStatement = (AstLetStatement) node;
        assertEquals(expectedName, letStatement.getIdentifier().getName());
        return letStatement;
    }
}
