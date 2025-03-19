package com.krushna.Java_rnd.async;

import java.util.concurrent.*;

public class AsyncExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            final int messageId = i;
            CompletableFuture.runAsync(() -> processMessage(messageId), executor);
        }
        executor.shutdown();
    }

    private static void processMessage(int id) {
        try {
            Thread.sleep(100);
            System.out.println("Processed message: " + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
