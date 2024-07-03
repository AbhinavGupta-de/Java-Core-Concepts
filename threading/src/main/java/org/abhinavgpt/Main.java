package org.abhinavgpt;

import org.abhinavgpt.threads.EmptyThread;
import org.abhinavgpt.threads.NumberPrinter;
import org.abhinavgpt.threads.SequencePrinter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Threading running the main method is:" + Thread.currentThread().getName());

        for(int i = 1; i <= 100; i++) {
            NumberPrinter numberPrinter = new NumberPrinter(i);
            numberPrinter.start();
        }

        EmptyThread emptyThread = new EmptyThread();
        emptyThread.start();
    }
}