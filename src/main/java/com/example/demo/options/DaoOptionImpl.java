package com.example.demo.options;

import com.example.demo.botImlp.SenderBot;
import com.example.demo.commands.Command;
import com.example.demo.commands.ParsedCommand;
import com.example.demo.departments.DepartmentService;
import com.example.demo.lectors.LectorsService;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoOptionImpl extends AbstractOption {
    private LectorsService lectorsService;
    private DepartmentService departmentService;
    @Autowired
    public DaoOptionImpl(SenderBot senderBot,
                         LectorsService lectorsService,
                         DepartmentService departmentService) {
        super(senderBot);
        this.lectorsService=lectorsService;
        this.departmentService=departmentService;

    }

    @Override
    public String operate(ParsedCommand parsedCommand) {
        Command command = parsedCommand.getCommand();
        switch (command){
            case HEAD -> senderBot.sendQueue.add(departmentService.headOfDepartmentService(parsedCommand.getText()));

            case AVG -> senderBot.sendQueue
                    .add(lectorsService.avgSalary((parsedCommand.getText())));

            case COUNT -> senderBot.sendQueue
                    .add(lectorsService.countOfEmpl(parsedCommand.getText()));

            case GLOBALSEARCH -> senderBot.sendQueue
                    .add(lectorsService.globalSearch(parsedCommand.getText()));

            case STATISTIC -> senderBot.sendQueue
                    .add(lectorsService.getStatics(parsedCommand.getText()));

        }
        //TODO : return some sticker
        return " ";
    }

}
