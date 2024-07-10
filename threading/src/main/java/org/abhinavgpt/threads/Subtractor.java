package org.abhinavgpt.threads;

import org.abhinavgpt.helper.Value;

import java.util.concurrent.Callable;

public class Subtractor implements Callable<Void> {
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public Void call() {
        for(int i = 1; i <= 10000; i++){
            value.subtract(i);
        }

        return null;
    }

}
