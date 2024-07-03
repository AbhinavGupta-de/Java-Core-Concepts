package org.abhinavgpt.threads;

public class HelloWorldPrinter extends Thread{
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}
