package com.aarbru.monkey.ast;

import java.util.ArrayList;

import com.aarbru.monkey.ast.nodes.AstExpression;
import com.aarbru.monkey.ast.nodes.AstExpressionStatement;
import com.aarbru.monkey.ast.nodes.AstIdentifier;
import com.aarbru.monkey.ast.nodes.AstIdentifierExpression;
import com.aarbru.monkey.ast.nodes.AstLetStatement;
import com.aarbru.monkey.ast.nodes.AstReturnStatement;
import com.aarbru.monkey.ast.nodes.AstStatement;
import com.aarbru.monkey.exceptions.MissingSemicolonParseException;
import com.aarbru.monkey.exceptions.UnexpectedTokenParseException;
import com.aarbru.monkey.lexer.Lexer;
import com.aarbru.monkey.lexer.TokenType;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Program parseProgram() {
        var statements = new ArrayList<AstStatement>();
        
        while(lexer.peekNextToken().getType() != TokenType.EOF) {
            var statement = switch(lexer.peekNextToken().getType()) {
                case KEY_LET -> extractLetStatement();
                case KEY_RETURN -> extractReturnStatement();
                default -> extractExpressionStatement();
            };

            if (statement != null) {
                statements.add(statement);
            }
        }

        return new Program(statements);
    }

    /**
     * Example Let Statement: `let test = 5;`
     * 
     * @return an extracted Let statement AST node.
     */
    private AstLetStatement extractLetStatement() {
        assert lexer.nextToken().getType() == TokenType.KEY_LET;

        var identifierToken = lexer.nextToken();
        var assignToken = lexer.nextToken();
        
        // Unexpected token checks
        if (identifierToken.getType() != TokenType.IDENTIFIER) {
            throw new UnexpectedTokenParseException(identifierToken, TokenType.IDENTIFIER);
        }
        if (assignToken.getType() != TokenType.OP_ASSIGN) {
            throw new UnexpectedTokenParseException(assignToken, TokenType.OP_ASSIGN);
        }

        // TODO consume until we can extract expressions.
        extractExpression();

        return new AstLetStatement(new AstIdentifier(identifierToken.getValue()), null);
    }

    /**
     * Example Return Statement: `return 5;`
     * 
     * @return an extracted Return statement AST node.
     */
    private AstReturnStatement extractReturnStatement() {
        assert lexer.nextToken().getType() == TokenType.KEY_RETURN;

        // TODO consume until we can extract expressions.
        extractExpression();

        return new AstReturnStatement(null);
    }

    private AstExpressionStatement extractExpressionStatement() {
        return new AstExpressionStatement(extractExpression());
    }

    private AstExpression extractExpression() {
        var expression = new AstIdentifierExpression(lexer.peekNextToken());
        
        // TODO consume until we can extract expressions.
        TokenType type;
        while ((type = lexer.nextToken().getType()) != TokenType.DEL_SEMICOLON) {
            if (type == TokenType.EOF) {
                throw new MissingSemicolonParseException();
            }
        }

        return expression;
    }
}
