# kafka-examples

Here you will find example code, written in __Java__, to understand various features provided by __Kafka__.
Each of these examples demostrates one or related aspects of Kafka messaging, in a __simple__ manner.

These examples expect a minimal setup, for which instructions are mentioned in __Setup__ section at the end. Please use these instructions before running the code.

Examples are arranges by following topics:

+ Warmup with console utilities
+ [Basic Producer and Baisc Consumer](https://github.com/agrawalnishant/kafka-examples/tree/master/src/main/java/kafka/examples/basic)
+ [Consumer Group](https://github.com/agrawalnishant/kafka-examples/blob/master/src/main/java/kafka/examples/basic/StringProducerConsumerGroupDemo.java)
+ Custom Partitioner
+ Schema Registery

## Setup

1. To uses some advanced features of Kafka, we will use Confluent's distribution available in [Download Center](https://www.confluent.io/download-center/). Download and un-Tar the contents. Lets call this location as KAFKA_HOME, for the puepose of discussion here. This location has folders like bin/ and config/ inside.

2. In console / terminal window go to KAFKA_HOME location, and excute following commands:
    1. To start zookeeper:
    `zookeeper-server-start.sh ../config/zookeeper.properties`
        - Wait for Zookeeper to start.
    2. To start Kafka broker/server:
        `kafka-server-start.sh ../config/server.properties`
3. Now above step# 2, would start a single Zookeep instance and a single Kafka broker. This setup is enough for basic message production and consumption. But for a more robust abd fault-tolerant Kafka Setup, we need at least 3 replicas of Kafka for single failover scenario. Fow now, lets start one more replica to support a Consumer Group with 2 consumers. Single replica can only attach to a single Kafka consumer in a group setting.
    1. Each new broker instance needs its own server.properties file. So clone the existing config/server.properties to, say server-1.properties, and change these parameters and save the file as server-1.properties or any other name.
        - `log.dirs` to new log directoy (say append "-1" to existing)
        - `listeners` to new port (say, 9093)
    Keep zookeeper connect port as same as earlier.
    2. Now use following command, from bin folder, to start a Kafka replica synced to same zookeeper:
        - `kafka-server-start.sh ../config/server-1.properties`
