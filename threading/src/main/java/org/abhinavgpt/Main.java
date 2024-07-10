package org.abhinavgpt;

import org.abhinavgpt.helper.Value;
import org.abhinavgpt.threads.*;
import org.abhinavgpt.virtualthreading.VirtualThreadMergeSorter;
import org.abhinavgpt.virtualthreading.VirtualThreadWorker;

import java.time.LocalDateTime;
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

        List<Integer> list1 = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list1.add(i);
        }
        for (int i = 1; i <= 100; i++) {
            list1.add(i);
        }

        SortedListMerger sortedListMerger = new SortedListMerger(list1);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        LocalDateTime start = LocalDateTime.now();
        Future<List<Integer>> future1 = executorService1.submit(sortedListMerger);

        try {
            List<Integer> sortedList = future1.get();
            LocalDateTime end = LocalDateTime.now();
            System.out.println(sortedList.toString());
            System.out.println("Time taken: " + (end.getSecond() - start.getSecond()));
        } catch (Exception e) {
            System.out.println("Error while merging the list");
        }

        executorService1.close();

        VirtualThreadMergeSorter virtualThreadMergeSorter = new VirtualThreadMergeSorter(list1);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        LocalDateTime start2 = LocalDateTime.now();
        Future<List<Integer>> future2 = executorService2.submit(virtualThreadMergeSorter);

        try {
            List<Integer> sortedList = future2.get();
            LocalDateTime end2 = LocalDateTime.now();
            System.out.println(sortedList.toString());
            System.out.println("Time taken by Virtual Threads: " + (end2.getSecond() - start2.getSecond()));
        } catch (Exception e) {
            System.out.println("Error while merging the list");
        }

        executorService2.close();


        Value value = new Value();
        value.setVal(0L);
        Adder adder = new Adder();
        adder.setValue(value);
        Subtractor subtractor = new Subtractor();
        subtractor.setValue(value);

        ExecutorService executorService3 = Executors.newFixedThreadPool(2);

        Future<Void> e1 = executorService3.submit(adder);
        Future<Void> e2 = executorService3.submit(subtractor);

        try {
            e1.get();
            e2.get();
        } catch (Exception e) {
            System.out.println("Error while adding and subtracting");
        }

        executorService3.close();

        System.out.println(value.getVal());
    }
}