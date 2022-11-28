package com.aarbru.monkey.terminal.commandline;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Allows you to interact with the lexer (component responsible for converting raw source into tokens).")
public class LexerCommand {
    @Parameter(names = {"-repl", "--repl"}, description = "Enters an interactive mode which allows you to enter a line of monkey source. The resulting tokens will be printed back.")
    private boolean repl = false;

    public boolean repl() {
        return repl;
    }
}
