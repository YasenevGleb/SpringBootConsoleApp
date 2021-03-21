package com.example.demo.options;

import com.example.demo.commands.Command;
import com.example.demo.commands.ParsedCommand;
import com.example.demo.departments.DepartmentService;
import com.example.demo.lectors.LectorsService;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoOptionImpl implements AbstractOption {
    private LectorsService lectorsService;
    private DepartmentService departmentService;
    @Autowired
    public DaoOptionImpl(
                         LectorsService lectorsService,
                         DepartmentService departmentService) {
        this.lectorsService=lectorsService;
        this.departmentService=departmentService;
    }

    @Override
    public String operate(ParsedCommand parsedCommand) {
        Command command = parsedCommand.getCommand();
        switch (command){
            case HEAD : return departmentService.headOfDepartmentService(parsedCommand.getText());

            case AVG : return lectorsService.avgSalary((parsedCommand.getText()));

            case COUNT : return lectorsService.countOfEmpl(parsedCommand.getText());

            case GLOBALSEARCH : return lectorsService.globalSearch(parsedCommand.getText());

            case STATISTIC :return lectorsService.getStatics(parsedCommand.getText());

        }
        //TODO : return some sticker
        return " ";
    }

}
