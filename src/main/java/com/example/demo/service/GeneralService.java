package com.example.demo.service;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GeneralService implements CommandLineRunner  {
    private Parser parser;
    private Scanner scanner=new Scanner(System.in);
    private LectorsService lectorsService;
    private DepartmentService departmentService;
    private ConfigurableApplicationContext context;
    @Autowired
    public GeneralService(Parser parser,
                          LectorsService lectorsService,
                          DepartmentService departmentService,
                          ConfigurableApplicationContext context) {
        this.parser = parser;
        this.lectorsService = lectorsService;
        this.departmentService = departmentService;
        this.context=context;
    }

    private void analyze(Object object) {
        String inputText = (String) object;
        ParsedCommand parsedCommand = parser.getParsedCommand(inputText);
        AbstractOption optionForCommand = getOptionForCommand(parsedCommand.getCommand());

        String operationResult = optionForCommand.operate(parsedCommand);

        if (!" ".equals(operationResult)) {
            System.out.println(operationResult);;
        }
}
    private AbstractOption getOptionForCommand (Command command){
        if (command == null) {
            return new DefaultOptionImpl();
        }
        switch (command) {
            case START:
            case HELP:
                SystemOptionImpl systemOption = new SystemOptionImpl();
                return systemOption;
            case HEAD:
            case AVG:
            case STATISTIC:
            case COUNT:
            case GLOBALSEARCH:
                DaoOptionImpl daoOption = new DaoOptionImpl( lectorsService, departmentService);
                return daoOption;
            case EXIT: System.exit(SpringApplication.exit(context));


            default:
                return new DefaultOptionImpl();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello, lets start to communicate with me , just press /start ");
        while (true){
            String nextLine = scanner.nextLine();
            analyze(nextLine);
        }

    }

}
