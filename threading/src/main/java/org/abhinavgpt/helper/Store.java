package org.abhinavgpt.helper;

import java.util.concurrent.Semaphore;

public class Store {

    private int value;
    private final Semaphore items;
    private final Semaphore spaces;

    public Store(int max_val) {
        this.value = 0;
        this.items = new Semaphore(0);
        this.spaces = new Semaphore(max_val);
    }

    public void increment() {
        try {
            spaces.acquire();
            synchronized (this) {
                value++;
                System.out.println("val add: " + value);
            }
            items.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void decrement() {
        try {
            items.acquire();
            synchronized (this) {
                value--;
                System.out.println("val sub: " + value);
            }
            spaces.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
