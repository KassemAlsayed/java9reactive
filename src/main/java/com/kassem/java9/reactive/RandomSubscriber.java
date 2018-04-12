package com.kassem.java9.reactive;

import java.util.concurrent.Flow;

/**
 * Created by Kassem A. Ali on 2018-04-11.
 */
public class RandomSubscriber implements Flow.Subscriber {

    private final static int NUM_MSGS_AT_A_TIME = 2;
    private final static int WAIT_BEFORE_CANCEL_SUBSCRIPTION = 5; // in sec

    private int maxMsgsIntrestedIn = 0;
    private int messagesReceived = 0;
    private boolean isCanceled = false;
    private String subscriberName;
    private Flow.Subscription subscription;

    public RandomSubscriber(int maxMsgsIntrestedIn, String subName) {
        this.maxMsgsIntrestedIn = maxMsgsIntrestedIn;
        this.subscriberName = subName;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

        System.out.println(getSubscriberName() +" number of messages " + Thread.currentThread().getName() + " interested in: " + maxMsgsIntrestedIn);

        this.subscription = subscription;

        if (maxMsgsIntrestedIn > 0) {
            // send NUM_MSGS_AT_A_TIME at a time
            subscription.request(NUM_MSGS_AT_A_TIME);
        } else {
            cancelSubscription(subscription);
            System.out.println(getSubscriberName() +" Received "+ (messagesReceived) +" messages. Subscriber is not interested in any messages, canceling my subscription: " + Thread.currentThread().getName());

        }

        // cancel subscription after waiting X sec
        waitForXsecThenCancel(WAIT_BEFORE_CANCEL_SUBSCRIPTION);
    }

    private void cancelSubscription(Flow.Subscription subscription) {
        subscription.cancel();
        isCanceled = true;
    }

    @Override
    public void onNext(Object item) {

        messagesReceived++;

        if (messagesReceived > maxMsgsIntrestedIn || maxMsgsIntrestedIn == 0) {
            System.out.println(getSubscriberName() +" Received "+ (messagesReceived-1) +" messages. Subscriber is not interested anymore, canceling my subscription: " + Thread.currentThread().getName());
            cancelSubscription(subscription);
        } else {
            System.out.println(getSubscriberName() +" recieved: " + item +" "+ Thread.currentThread().getName());
            subscription.request(NUM_MSGS_AT_A_TIME);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println(getSubscriberName() +" completed... " + Thread.currentThread().getName());
        subscription.cancel();
    }

    /**
     * Similate the subscriber waiting X secs and then canceling its subscription
     *
     * @param sleepTime
     */
    private void waitForXsecThenCancel(final int sleepTime) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(sleepTime * 1000);
                    if ( !isCanceled) {
                        System.out.println(getSubscriberName() +" Received "+ messagesReceived + " messages. Waited for " + sleepTime + " sec, not interested anymore, canceling my subscription");
                        subscription.cancel();
                    } else {
                        // subscription already canceled after getting all messages this subscriber is interested in
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getSubscriberName() {
        return subscriberName;
    }
}
