package com.kassem.java9.reactive;

import com.kassem.domain.RandomMessage;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kassem A. Ali on 2018-04-11.
 */
public class RandomPublisher extends SubmissionPublisher<RandomMessage> {

    private final static int TIME_TO_SLEEP = 1;

    public void start(){
        /*
        List.of(RandomMessage.create("message1"),
                RandomMessage.create("message2"),
                RandomMessage.create("message3"),
                RandomMessage.create("message4"),
                RandomMessage.create("message5")).forEach(this::submit);
                */

        System.out.println("Publisher: is emitting 5 messages, one every "+ TIME_TO_SLEEP + " sec.");
        List.of(RandomMessage.create("message1"),
                RandomMessage.create("message2"),
                RandomMessage.create("message3"),
                RandomMessage.create("message4"),
                RandomMessage.create("message5")).stream().map(item -> {
            try {
                TimeUnit.SECONDS.sleep(TIME_TO_SLEEP);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return item;
        }).forEach(this::submit);
    }
}
