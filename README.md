# Coding Assignment

## Instructions

You are the only developer of a small startup that manages messages.
Your application receives messages via a REST API, stores them in memory and then make them available through a REST API.

You already wrote a maven/spring based application with two basic endpoints:

* [POST] `/messages` which adds a new message to the list of messages
* [GET]  `/messages` which returns the content of the list of messages

The JSON structure used for posting messages **MUST NOT** change.

To run locally the application you have to build it:

```shell
./mvnw package
```
and then execute the jar:
```shell
java -jar target/messages-1.0-SNAPSHOT.jar
```

You can run the tests with:
```shell
./mvnw test
```



### Task #1

You noticed that sometimes the `GET /messages` endpoint doesn't return all the messages that were previously added: you **SHOULD** verify that this can happen and you **MUST** fix it.




### Task #2
The evil marketer of the startup needs some statistics information about the messages to fulfill some obscure goal; you **MUST** add to this application a new endpoint called `/stats` returning some statistics about the last posted messages; in detail, the marketer needs:
  * the number of messages posted in the last minute

  * the average (mean) length of unique words in all the messages received in last minute

  * the number of occurrences of each word contained in the messages posted in the last minute; the word separator is any not alphanumeric character. For example, if in the last minute the application received the two messages "test message1" and "test message2", you **MUST** count:

    | word     | count |
    | -------- | ----- |
    | test     | 2     |
    | message1 | 1     |
    | message2 | 1     |



The JSON payload returned by the endpoint **MUST** be like this:

```json
{
  "posted_messages": 2,
  "average_length": 6.67,
  "occurrences": [
    {"word": "test", "count": 2},
    {"word": "message1", "count": 1},
    {"word": "message2", "count": 1}
  ]
}
```

You **MAY** optimize the procedure for time complexity or you **MAY** prefer code readibility.



### Task #3

This application has to connect to a Machine Learning external HTTP service that will crunch the messages to obtain some interesting data. You **MUST** add to the application a process that every day at 23:00:00Z connects to the external HTTP service passing a JSON containing all the messages received until that moment; all the messages received during the transmission of data **MUST** be sent the day after, with the new execution of the process; after having sent the messages to the external service, the process **MUST** delete all the sent messages. Take into account that the external service can be down, can crash while receiving, and can fail for whatever reason; you **MUST** assume either an `at least once` or an `exactly once` logic from the external service.

During the execution of this task and for the next minute the results of the statistics endpoint (the one in Task #2) **MAY** not return accurate result. The GET `/messages` endpoint **MUST** return all the messages until the execution of the process starts and from that moment on it **MUST** return only the newly arrived messages.

We have implemented for you a mock external service listening at http://localhost:8181/mock_external_service which just returns a 200 HTTP status code. If you already have something binding that port on your local machine, you **MAY** change the port number in `MessagesApplication.java:16`.

You can create the JSON structure you prefer for passing the data to the external process.



### Task #4

Replace Maven to Gradle


## Application constraints / notes:
* you can safely assume that your users will send no more than 100.000 messages every day (it's still a startup!)

* the application **SHOULD NOT** store messages in a persistent way, you can just leave them in memory

* you **MAY** use any external library you want

* you **SHOULD** write a production ready, tested, and well-modularised code

* you **MAY** choose one of these JVM languages in your solution: JAVA


Have fun!
