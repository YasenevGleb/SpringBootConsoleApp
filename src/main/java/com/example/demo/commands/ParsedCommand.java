package com.example.demo.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ParsedCommand {
    Command command = Command.NONE;
    String text="";
}
