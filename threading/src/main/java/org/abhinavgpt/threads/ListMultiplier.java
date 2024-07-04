package org.abhinavgpt.threads;

import java.util.List;
import java.util.concurrent.Callable;

public class ListMultiplier implements Callable<List<Integer>> {
    private final List<Integer> list;

    public ListMultiplier(List<Integer> list) {
        this.list = list;
    }

    @Override
    public List<Integer> call() throws Exception {
        list.replaceAll(integer -> integer * 2);
        return list;
    }
}
