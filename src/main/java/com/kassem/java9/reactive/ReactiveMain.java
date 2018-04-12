package com.kassem.java9.reactive;



import com.kassem.domain.RandomMessage;

import java.util.List;

/**
 * Created by Kassem A. Ali on 2018-04-11.
 */
public class ReactiveMain {

    public static void main(String[] args) {

        try (RandomPublisher<RandomMessage> randomPublisher = new RandomPublisher()) {

            randomPublisher.subscribe(new RandomSubscriber(10, "subscriber1"));

            randomPublisher.subscribe(new RandomSubscriber(10, "subscriber2"));

            List.of(RandomMessage.create("message1"),
                    RandomMessage.create("message2"),
                    RandomMessage.create("message3"),
                    RandomMessage.create("message4"),
                    RandomMessage.create("message5")).forEach(randomPublisher::submit);

            while (randomPublisher.hasSubscribers()) {
                // do nothing just wait
            }
            System.out.println("Finished - no more subscribers");
        }
    }
}
