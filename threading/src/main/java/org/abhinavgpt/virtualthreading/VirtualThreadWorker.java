package org.abhinavgpt.virtualthreading;

public class VirtualThreadWorker {
    public void executeTasks() {
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " is running in virtual thread " + Thread.currentThread());
                });
            }
        } catch (Exception e) {
            System.out.println("Error while executing tasks");
        }
    }
}

