package com.example.demo;

import com.example.demo.botImlp.SenderBot;
import com.example.demo.departments.DepartmentService;
import com.example.demo.lectors.LectorsService;
import com.example.demo.service.MessageReciever;
import com.example.demo.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private static final int PRIORITY_FOR_SENDER = 1;
    private static final int PRIORITY_FOR_RECEIVER = 3;
    private static final int PRIORITY_FOR_BOT=4;
    private LectorsService lectorsService;
    private DepartmentService departmentService;
    @Autowired
    public MyRunner(LectorsService lectorsService,DepartmentService departmentService) {
        this.lectorsService=lectorsService;
        this.departmentService=departmentService;

    }

    @Override
    public void run(String... args) throws Exception {
        SenderBot senderBot=new SenderBot();
        MessageReciever messageReciever = new MessageReciever(senderBot,lectorsService,departmentService);
        MessageSender messageSender = new MessageSender(senderBot);
        Thread bot=new Thread(senderBot);
        bot.setDaemon(true);
        bot.setPriority(PRIORITY_FOR_BOT);
        bot.setName("BOT");
        bot.start();
        Thread receiver = new Thread(messageReciever);
        receiver.setDaemon(true);
        receiver.setName("MsgReciever");
        receiver.setPriority(PRIORITY_FOR_RECEIVER);
        receiver.start();

        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(PRIORITY_FOR_SENDER);
        sender.start();
    }
}
