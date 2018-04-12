package com.kassem.java9.reactive;

import com.kassem.domain.RandomMessage;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Kassem A. Ali on 2018-04-12.
 */
public class RandomSubscriberTest {

    RandomPublisher<RandomMessage> randomPublisher;
    RandomSubscriber randomSubscriber1;
    RandomSubscriber randomSubscriber2;

    int msgs3 = 3;
    int msg0 = 0;
    int msg10 = 10;

    @org.junit.Before
    public void setUp() throws Exception {
        randomPublisher = new RandomPublisher();

        randomSubscriber1 = new RandomSubscriber(msgs3, "subscriber1");
        randomSubscriber2 = new RandomSubscriber(msg10, "subscriber2");

        randomPublisher.subscribe(randomSubscriber1);
        randomPublisher.subscribe(randomSubscriber2);

        List.of(RandomMessage.create("message1"),
                RandomMessage.create("message2"),
                RandomMessage.create("message3"),
                RandomMessage.create("message4"),
                RandomMessage.create("message5")).forEach(randomPublisher::submit);

        while (randomPublisher.hasSubscribers()) {
            // do nothing just wait
        }
    }

    @org.junit.Test
    public void testSubscribers() {
        Assert.assertEquals(msgs3+1, randomSubscriber1.getMessagesReceived());
        Assert.assertEquals(true, randomSubscriber1.isCanceled());

    }
}