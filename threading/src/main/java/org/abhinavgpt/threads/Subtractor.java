package org.abhinavgpt.threads;

import org.abhinavgpt.helper.Value;

import java.util.concurrent.Callable;

public class Subtractor implements Callable<Void> {
    Value value;

    public Subtractor(Value value) {
        this.value = value;
    }

    @Override
    public Void call() {
        for(int i = 1; i <= 10000; i++){
            value.val -= i;
        }

        return null;
    }

}
