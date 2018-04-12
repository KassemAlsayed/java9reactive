package com.kassem.domain;

import java.time.LocalTime;
import java.util.Random;

/**
 * Created by Kassem A. Ali on 2018-04-11.
 */
public class RandomMessage {
    private final String randomMessage;
    private int randomNumber = 0;
    private final LocalTime randomDate;

    private RandomMessage(String randomMessage, int randomNumber, LocalTime randomDate) {
        this.randomMessage = randomMessage;
        this.randomNumber = randomNumber;
        this.randomDate = randomDate;
    }

    public static RandomMessage create(String msg) {
        return new RandomMessage(msg, (new Random()).nextInt(100), LocalTime.now());
    }

    public String getRandomMessage() {
        return randomMessage;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public LocalTime getRandomDate() {
        return randomDate;
    }

    @Override
    public String toString() {
        return "RandomMessage{" +
                "randomMessage='" + randomMessage + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", randomDate=" + randomDate +
                '}';
    }
}
