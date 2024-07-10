package org.abhinavgpt.threads;

import org.abhinavgpt.helper.Store;

public class Workers {

    private class Producer extends Thread {

        private Store store;

        public Producer(Store store) {
            this.store = store;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                store.increment();
            }
        }

    }

    private class Consumer extends Thread {

        private Store store;

        public Consumer(Store store) {
            this.store = store;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                store.decrement();
            }
        }

    }

    public void start() {
        Store store = new Store(5);
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        producer.start();
        consumer.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println();
        }

        producer.interrupt();
        consumer.interrupt();
    }
}
