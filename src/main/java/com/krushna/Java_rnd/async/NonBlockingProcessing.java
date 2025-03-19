package com.krushna.Java_rnd.async;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.CountDownLatch;


public class NonBlockingProcessing {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        CountDownLatch latch = new CountDownLatch(1000);
        publisher.subscribe(new MessageSubscriber(latch));

        for (int i = 0; i < 1000; i++) {
            publisher.submit(i);
        }
        publisher.close();
        latch.await(); // Ensure all messages are processed before exiting
    }
}

class MessageSubscriber implements Flow.Subscriber<Integer> {
    private Flow.Subscription subscription;
    private final CountDownLatch latch;

    public MessageSubscriber(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("Processed message: " + item);
        subscription.request(1);
        latch.countDown();
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Processing complete.");
    }
}