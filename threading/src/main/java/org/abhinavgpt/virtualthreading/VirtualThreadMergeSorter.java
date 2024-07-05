package org.abhinavgpt.virtualthreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newThreadPerTaskExecutor;

public class VirtualThreadMergeSorter implements Callable<List<Integer>> {
    private final List<Integer> listToBeSorted;

    public VirtualThreadMergeSorter(List<Integer> listToBeSorted) {
        this.listToBeSorted = listToBeSorted;
    }

    @Override
    public List<Integer> call() throws Exception {
        return mergeSort(listToBeSorted);
    }

    private List<Integer> mergeSort(List<Integer> list) throws Exception {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;

        ExecutorService executorService = newThreadPerTaskExecutor(Thread.ofVirtual().factory());

        Future<List<Integer>> leftFuture = executorService.submit(new VirtualThreadMergeSorter(list.subList(0, mid)));
        Future<List<Integer>> rightFuture = executorService.submit(new VirtualThreadMergeSorter(list.subList(mid, list.size())));

        executorService.shutdown();

        try {
            List<Integer> left = leftFuture.get();
            List<Integer> right = rightFuture.get();
            return merge(left, right);
        } catch (Exception e) {
            System.out.println("Error while merging the sorted arrays");
            throw e;
        }
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                mergedList.add(left.get(leftIndex));
                leftIndex++;
            } else {
                mergedList.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            mergedList.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            mergedList.add(right.get(rightIndex));
            rightIndex++;
        }

        return mergedList;
    }
}
