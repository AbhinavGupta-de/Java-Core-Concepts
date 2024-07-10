package org.abhinavgpt.helper;

public class Value {
    private long val = 0;

    public synchronized long getVal() {
        return val;
    }

    public synchronized void setVal(long val) {
        this.val = val;
    }
}
