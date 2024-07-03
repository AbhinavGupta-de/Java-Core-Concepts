package org.abhinavgpt;

import org.abhinavgpt.threads.EmptyThread;
import org.abhinavgpt.threads.NumberPrinter;
import org.abhinavgpt.threads.SequencePrinter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Threading running the main method is:" + Thread.currentThread().getName());

        for (int i = 1; i <= 100; i++) {
            NumberPrinter numberPrinter = new NumberPrinter(i);
            numberPrinter.start();
        }

        EmptyThread emptyThread = new EmptyThread();
        emptyThread.start();

//        Manages and re-uses threads!
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 100; i++) {
            es.execute(new NumberPrinter(i));
        }

        es.close();
    }
}