package com.example.demo.options;

import com.example.demo.botImlp.SenderBot;
import com.example.demo.commands.ParsedCommand;

public class DefaultOptionImpl extends AbstractOption {
    public DefaultOptionImpl(SenderBot senderBot) {
        super(senderBot);
    }

    @Override
    public String operate(ParsedCommand parsedCommand) {
        return " ";
    }
}
