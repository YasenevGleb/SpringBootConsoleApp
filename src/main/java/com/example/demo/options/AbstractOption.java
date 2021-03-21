package com.example.demo.options;
import com.example.demo.commands.ParsedCommand;

public interface  AbstractOption {
    String operate( ParsedCommand parsedCommand);
}

