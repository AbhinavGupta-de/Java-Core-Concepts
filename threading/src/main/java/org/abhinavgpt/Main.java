package org.abhinavgpt;

import org.abhinavgpt.threads.EmptyThread;
import org.abhinavgpt.threads.ListMultiplier;
import org.abhinavgpt.threads.NumberPrinter;
import org.abhinavgpt.threads.SequencePrinter;
import org.abhinavgpt.virtualthreading.VirtualThreadWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }

        ListMultiplier listMultiplier = new ListMultiplier(list);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<List<Integer>> future = executorService.submit(listMultiplier);

        try {
            List<Integer> multipliedList = future.get();
            System.out.println(multipliedList.toString());
        } catch (Exception e) {
            System.out.println("Error while multiplying the list");
        }

        executorService.close();

        VirtualThreadWorker worker = new VirtualThreadWorker();
        worker.executeTasks();
    }
}