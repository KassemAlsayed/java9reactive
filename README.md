# Java 9 Reactive Streams

For better compatibility, ease of use and standardization, Java 9 now includes interfaces for each of the Reactive Stream concepts in the Flow Concurrency library. This allows Java applications to depend on this one library for Reactive Stream, rather than deciding on a specific implementation. Most famous implementations (rxjava, akka, reactor...) are now implementing these java 9 Reactive Stream interfaces. This allows for switching the implementation library without changing your code.

To better understand this, I implemented this little app that uses java 9 Reactive Stream that is in the Flow Concurrency Lib (java.util.concurrent). Note that this is different than java 8 reactive streams under org.reactivestreams which should not be used going forward... The interfaces names are called the same under both packages which could be confusing.

This is an example of Java 9 reactive streams. 
- It shows the interaction between a publisher and subscribers
- Subscribers canceling after wiating X secs or after receiving a certain # of messages
- It showes how theadds/pools are bing used/reused in the publisher

## Prerequisites:
* Maven >= 3.0.0
* JDK 9

## Running the example
* `mvn clean package`
* `.\run.cmd` or `./run.sh`

## Known issues
* At the moment, no issues

## Sample output
- Publisher: is emitting 5 messages, one every 1 sec.

- subscriber1: number of messages interested in: 10 ForkJoinPool.commonPool-worker-2

- subscriber2: number of messages interested in: 3 ForkJoinPool.commonPool-worker-1

- subscriber1: recieved: RandomMessage{randomMessage='message1', randomNumber='60', randomDate=21:58:13.809854} ForkJoinPool.commonPool-worker-1

- subscriber2: recieved: RandomMessage{randomMessage='message1', randomNumber='60', randomDate=21:58:13.809854} ForkJoinPool.commonPool-worker-2

- subscriber1: recieved: RandomMessage{randomMessage='message2', randomNumber='23', randomDate=21:58:13.840820} ForkJoinPool.commonPool-worker-2

- subscriber2: recieved: RandomMessage{randomMessage='message2', randomNumber='23', randomDate=21:58:13.840820} ForkJoinPool.commonPool-worker-1

- subscriber1: recieved: RandomMessage{randomMessage='message3', randomNumber='92', randomDate=21:58:13.840861} ForkJoinPool.commonPool-worker-1

- subscriber2: recieved: RandomMessage{randomMessage='message3', randomNumber='92', randomDate=21:58:13.840861} ForkJoinPool.commonPool-worker-2

- subscriber1: recieved: RandomMessage{randomMessage='message4', randomNumber='32', randomDate=21:58:13.842169} ForkJoinPool.commonPool-worker-2

- subscriber2: Received 3 messages. Subscriber is not interested anymore, canceling my subscription: ForkJoinPool.commonPool-worker-1

- subscriber1: Received 4 messages. Waited for 5 sec, not interested anymore, canceling my subscription

- Finished - no more subscribers
