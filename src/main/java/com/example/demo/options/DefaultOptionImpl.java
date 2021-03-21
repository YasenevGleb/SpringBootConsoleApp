package com.example.demo.options;

import com.example.demo.commands.ParsedCommand;

public class DefaultOptionImpl implements AbstractOption {
    public DefaultOptionImpl() {
    }

    @Override
    public String operate(ParsedCommand parsedCommand) {
        return " ";
    }
}
