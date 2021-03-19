package com.example.demo.service;

import com.example.demo.botImlp.SenderBot;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSender implements Runnable{
    private final int SENDER_SLEEP_TIME = 1000;
    private SenderBot bot;
    @Autowired
    public MessageSender(SenderBot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Object object = bot.sendQueue.poll(); object != null; object = bot.sendQueue.poll()) {
                    send(object);
                }
                try {
                    Thread.sleep(SENDER_SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void send(Object object) {
        System.out.println(object.toString());

    }

}
