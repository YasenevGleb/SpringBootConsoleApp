package com.example.demo.commands;

public class Parser {

    private final String PREFIX_FOR_COMMAND="/";
    public ParsedCommand getParsedCommand(String text) {
        String trimText = "";
        if (text != null) trimText = text.trim();
        ParsedCommand result = new ParsedCommand(Command.NONE, trimText);

        if ("".equals(trimText)) return result;
        Pair<String, String> commandAndText = getDelimitedCommand(trimText);
        if (isCommand(commandAndText.getKey())) {
            String commandForParse = cutCommandFromFullText(commandAndText.getKey());
            Command commandFromText = getCommandFromText(commandForParse);
            result.setText(commandAndText.getValue());
            result.setCommand(commandFromText);

        }
        return result;
    }


    private Pair<String, String> getDelimitedCommand(String trimText) {
        Pair<String, String> commandText;

        if (trimText.contains(" ")) {
            int indexOfSpace = trimText.indexOf(" ");
            commandText = new Pair<>(trimText.substring(0, indexOfSpace), trimText.substring(indexOfSpace + 1));
        } else commandText = new Pair<>(trimText, "");
        return commandText;
    }


    private boolean isCommand(String text) {
        return text.startsWith(PREFIX_FOR_COMMAND);
    }

    private Command getCommandFromText(String text) {
        String upperCaseText = text.toUpperCase().trim();
        Command command = Command.NONE;
        try {
            command = Command.valueOf(upperCaseText);
        } catch (IllegalArgumentException e) {
            System.err.println(" Cant parse your command " + text);
        }
        return command;
    }
    private String cutCommandFromFullText(String text) {
        return text.substring(1);
    }
}
