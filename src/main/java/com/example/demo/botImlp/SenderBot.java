package com.example.demo.botImlp;

import org.springframework.stereotype.Component;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class SenderBot implements Runnable {
    public final String name="Hey u yo wazup";
    public final Queue<Object> sendQueue = new ConcurrentLinkedQueue<>();
    public final Queue<Object> receiveQueue = new ConcurrentLinkedQueue<>();
    private Scanner scanner=new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("Hello, lets start to communicate with me , just press /start ");
        while (true){
            receiveQueue.add(scanner.nextLine());
        }
    }
}
