package com.aarbru.monkey.terminal;

import java.util.Scanner;

import com.aarbru.monkey.lexer.Lexer;

public class LexerRepl {
    public static void executeForever() {
        System.out.println("Welcome to the Monkey Lexer REPL! Enter text, press <ENTER>, and Lexer REPL will show you the tokens.");

        try (var scanner = new Scanner(System.in)) {
            while(true) {
                var line = scanner.nextLine();

                var lexer = new Lexer(line);
                var tokens = lexer.getAllTokens();
                
                System.out.println("\n---");
                for (var token : tokens) {
                    System.out.println(token);
                }
                System.out.println("---\n");
            }
        }
    }
}
