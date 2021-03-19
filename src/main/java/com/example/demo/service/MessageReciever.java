package com.example.demo.service;

import com.example.demo.botImlp.SenderBot;
import com.example.demo.commands.Command;
import com.example.demo.commands.ParsedCommand;
import com.example.demo.commands.Parser;
import com.example.demo.departments.DepartmentService;
import com.example.demo.lectors.LectorsService;
import com.example.demo.options.AbstractOption;
import com.example.demo.options.DaoOptionImpl;
import com.example.demo.options.DefaultOptionImpl;
import com.example.demo.options.SystemOptionImpl;
import org.springframework.beans.factory.annotation.Autowired;


public class MessageReciever implements Runnable{
    private SenderBot bot;
    private final int WAIT_FOR_NEW_MESSAGE_DELAY = 1000;
    private Parser parser;
    private LectorsService lectorsService;
    private DepartmentService departmentService;
    @Autowired
    public MessageReciever(SenderBot senderBot, LectorsService lectorsService,DepartmentService departmentService) {
        this.bot = senderBot;
        this.lectorsService=lectorsService;
        this.departmentService=departmentService;
        parser = new Parser();
    }

    @Override
    public void run() {
        while (true) {
            for (Object object = bot.receiveQueue.poll(); object != null; object = bot.receiveQueue.poll()) {
                analyze(object);
            }
            try {
                Thread.sleep(WAIT_FOR_NEW_MESSAGE_DELAY);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
    private void analyze(Object object) {
        String inputText = (String) object;

        ParsedCommand parsedCommand = parser.getParsedCommand(inputText);
        AbstractOption optionForCommand = getOptionForCommand(parsedCommand.getCommand());

        String operationResult = optionForCommand.operate(parsedCommand);

        if (!"".equals(operationResult)) {
            bot.sendQueue.add(operationResult);
        }
    }
    private AbstractOption getOptionForCommand(Command command) {
        if (command == null) {
            return new DefaultOptionImpl(bot);
        }
        switch (command) {
            case START:
            case HELP:
                SystemOptionImpl systemOption = new SystemOptionImpl(bot);
                return systemOption;
            case HEAD:
            case AVG:
            case STATISTIC:
            case COUNT:
            case GLOBALSEARCH:
                DaoOptionImpl daoOption=new DaoOptionImpl(bot,lectorsService,departmentService);
                return daoOption;


            default:
                return new DefaultOptionImpl(bot);
        }
    }

}
