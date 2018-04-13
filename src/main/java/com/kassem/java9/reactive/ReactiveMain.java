package com.kassem.java9.reactive;



import com.kassem.domain.RandomMessage;

import java.util.List;

/**
 * Created by Kassem A. Ali on 2018-04-11.
 */
public class ReactiveMain {

    public static void main(String[] args) {

        try (RandomPublisher randomPublisher = new RandomPublisher()) {
            randomPublisher.subscribe(new RandomSubscriber(10, "subscriber1"));
            randomPublisher.subscribe(new RandomSubscriber(3, "subscriber2"));
            randomPublisher.start();
            while (randomPublisher.hasSubscribers()) {
                // just wait, allow the subscribers to get their msgs
            }
            System.out.println("----------------");
            System.out.println("All done - all subscribers got the messages they are interested in or canceled");
        }
    }
}
