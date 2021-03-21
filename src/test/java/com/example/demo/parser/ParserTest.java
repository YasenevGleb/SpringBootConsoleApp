package com.example.demo.parser;

import com.example.demo.commands.Command;
import com.example.demo.commands.Pair;
import com.example.demo.commands.ParsedCommand;
import com.example.demo.commands.Parser;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class ParserTest {
    private final Parser parser=new Parser();

    @Test
    void isCommand_ShouldExecute_WhenCalled(){
        String command="/hello";
        assertThat(parser.isCommand(command)).isTrue();
    }
    @Test
    void isCommand_ShouldNotWorkWhenTextContainMoreThanOnePrefix(){
        String command="//hello";
        assertThat(parser.isCommand(command)).isFalse();

    }
    @Test
    void cutCommandFromFullText_ShouldExecute_WhenCalled(){
        String command="/hello";
        assertThat(parser.cutCommandFromFullText(command).equals("hello")).isTrue();

    }
    @Test
    void getDelimitedCommand_ShouldGetDelimitedCommandWorks(){
        String trimText="/hello sayHi";
        Pair<String,String> pair=new Pair<>("/hello","sayHi");
        Pair<String, String> parserDelimitedCommand = parser.getDelimitedCommand(trimText);
        assertThat(pair).isEqualToComparingFieldByField(parserDelimitedCommand);
        assertThat(pair.getKey().equals(parserDelimitedCommand.getKey())).isTrue();
        assertThat(pair.getValue().equals(parserDelimitedCommand.getValue())).isTrue();

    }
    @Test
    void getDelimitedCommand_ReturnDelimitedCommandWorksOnlyForKey(){
        String trimText="/hello";
        Pair<String,String> pair=new Pair<>("/hello","");
        Pair<String, String> parserDelimitedCommand = parser.getDelimitedCommand(trimText);
        assertThat(pair).isEqualToComparingFieldByField(parserDelimitedCommand);
        assertThat(pair.getKey().equals(parserDelimitedCommand.getKey())).isTrue();
        assertThat(pair.getValue().equals(parserDelimitedCommand.getValue())).isTrue();
    }
   @Test
   void getCommandFromText_ShouldReturn_CommandFromText(){
        String command="avg";
        Command commandFromText = parser.getCommandFromText(command);
        assertThat(commandFromText.equals(Command.AVG)).isTrue();
   }
   @Test
   void getCommandFromText_ShouldGetCommandFromTextNoneCommand(){
        String command="ok";
        Command commandFromText = parser.getCommandFromText(command);
        assertThat(commandFromText.equals(Command.NONE)).isTrue();

   }
   @Test
   void getParsedCommand_testGeneralProcessOfParse(){
        String parsedText="/statistic department1";
        ParsedCommand parsedCommandGet=new ParsedCommand(Command.STATISTIC,"department1");


        ParsedCommand parsedCommandFrom = parser.getParsedCommand(parsedText);
        assertThat(parsedCommandGet.getCommand().equals(parsedCommandFrom.getCommand())).isTrue();
        assertThat(parsedCommandGet.getText().equals(parsedCommandFrom.getText())).isTrue();
        assertThat(parsedCommandGet).isEqualToComparingFieldByField(parsedCommandFrom);


   }
   @Test
   void getParsedCommand_ShouldReturnParsedCommandWithNoneCommand(){
        String parsedText="/noting do";
        ParsedCommand parsedCommandGet=new ParsedCommand(Command.NONE,"do");
        ParsedCommand parsedCommandFrom = parser.getParsedCommand(parsedText);
        assertThat(parsedCommandGet.getCommand().equals(parsedCommandFrom.getCommand())).isTrue();
        assertThat(parsedCommandGet.getText().equals(parsedCommandFrom.getText())).isTrue();
        assertThat(parsedCommandGet).isEqualToComparingFieldByField(parsedCommandFrom);
   }
   @Test
   void getParsedCommand_ShouldNotWorkingWithoutCommandAndPrefix(){
        String parsedText="java 15";
        ParsedCommand parsedCommandForTest=new ParsedCommand(Command.NONE,"java 15");

       ParsedCommand parsedCommandFrom = parser.getParsedCommand(parsedText);
       assertThat(parsedCommandForTest.getCommand().equals(parsedCommandFrom.getCommand())).isTrue();
       assertThat(parsedCommandForTest.getText().equals(parsedCommandFrom.getText())).isTrue();
       assertThat(parsedCommandForTest).isEqualToComparingFieldByField(parsedCommandFrom);

   }
}
