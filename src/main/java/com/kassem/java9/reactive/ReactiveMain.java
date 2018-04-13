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
                // do nothing just wait
            }
            System.out.println("Finished - no more subscribers");
        }
    }
}
