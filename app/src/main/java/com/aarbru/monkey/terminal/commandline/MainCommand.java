package com.aarbru.monkey.terminal.commandline;

import com.beust.jcommander.Parameter;

public class MainCommand {

    @Parameter(names = {"-h", "--help"}, help = true)
    private boolean help;
    
    public boolean help() {
        return help;
    }
}
