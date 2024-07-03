package org.abhinavgpt.threads;

public class NumberPrinter extends Thread {
    private int num;
    public NumberPrinter(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(num + " " + Thread.currentThread().getName());
    }
}
