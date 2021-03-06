package com.example.demo.options;

import com.example.demo.commands.Command;
import com.example.demo.commands.ParsedCommand;

public class SystemOptionImpl implements AbstractOption {
    private final String END_LINE = "\n";
    public SystemOptionImpl() {

    }

    @Override
    public String operate(ParsedCommand parsedCommand) {
        Command command = parsedCommand.getCommand();
        return switch (command) {
            case HELP -> getMessageHelp();
            case START -> getMessageStart();
            default -> "";
        };

    }
    private String getMessageStart() {
        StringBuilder text = new StringBuilder();
        text.append("Hello. I'm Helper to you database ").append(END_LINE);
        text.append("All that I can do - you can see calling the command [/help](/help)");
        return text.toString();
    }
    private String getMessageHelp() {
        StringBuilder text = new StringBuilder();
        text.append("*This is help message*").append(END_LINE).append(END_LINE);
        text.append("[/start](/start) - show start message").append(END_LINE);
        text.append("[/help](/help) - show help message").append(END_LINE);
        text.append("[/statistic](/statistic) - show {department_name} statistics").append(END_LINE);
        text.append("[/globalsearch](/globalsearch) - global search around names and lastnames ").append(END_LINE);
        text.append("[/count](/count) - employees count of department  ").append(END_LINE);
        text.append("[/avg](/avg) - average salary for department ").append(END_LINE);
        text.append("[/head](/head) - head of department ").append(END_LINE);
        return text.toString();

    }
}
