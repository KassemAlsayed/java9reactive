# Java 9 Reactive Streams

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