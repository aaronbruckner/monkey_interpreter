package com.aarbru.monkey.ast;

import java.util.ArrayList;

import com.aarbru.monkey.ast.nodes.AstIdentifier;
import com.aarbru.monkey.ast.nodes.AstLetStatement;
import com.aarbru.monkey.ast.nodes.AstStatement;
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
                default -> throw new RuntimeException("Unrecognized Token: " + lexer.peekNextToken().getType());
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
        while (lexer.nextToken().getType() != TokenType.DEL_SEMICOLON);

        return new AstLetStatement(new AstIdentifier(identifierToken.getValue()), null);
    }
}
