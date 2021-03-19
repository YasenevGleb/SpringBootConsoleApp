package com.example.demo.options;

import com.example.demo.botImlp.SenderBot;
import com.example.demo.commands.ParsedCommand;

public abstract class AbstractOption {
    public AbstractOption(SenderBot senderBot) {
        this.senderBot = senderBot;
    }

    SenderBot senderBot;
    public abstract String operate( ParsedCommand parsedCommand);
}

