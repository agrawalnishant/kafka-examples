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
### Basic Setup
1. To uses some advanced features of Kafka, we will use Confluent's distribution available in [Download Center](https://www.confluent.io/download-center/). Download and un-Tar the contents. Lets call this location as KAFKA_HOME, for the purpose of discussion here. This location has folders like bin/ and config/ inside.

2. In console / terminal window go to KAFKA_HOME location, and excute following commands:
    1. Start zookeeper:
    `zookeeper-server-start.sh ../config/zookeeper.properties`
        - Wait for Zookeeper to start.
    2. Start Kafka broker/server:
        `kafka-server-start.sh ../config/server.properties`
        
3. Create a Kafka topic `topic_basic` with single replica and single partition:
    - `kafka-topics.sh  -zookeeper localhost:2181 --create --topic topic_basic --replication-factor 1 --partitions 1`

        
### Create Replica Broker for Consumer Group
Step# 2 above would start a single Zookeeper instance and a single Kafka broker. This setup is enough for basic message production and consumption. 

But for a more robust abd fault-tolerant Kafka Setup, we need at least 3 replicas and 3 partitions of Kafka for single failover instance.

Fow now, lets start one more broker to support a Consumer Group with 2 consumers. Single partition can only attach to a single Kafka consumer in a group setting.
* Each new broker instance needs its own `server.properties` file. 
  So clone the existing config/server.properties to, say `server-1.properties`, and change these parameters:
    - `log.dirs` to new log directoy (say /tmp/kafka-logs-1)
    - `listeners` to new port (say, 9093)
    Keep zookeeper connect port same as earlier.
 * Use following command, from bin folder, to start a Kafka replica synced to same zookeeper:
     - `kafka-server-start.sh ../config/server-1.properties`
        
### Enable 2 Partitions for Topic to support Consumer Group
* Use following command to increase replica count of topic, created ealier, from One to Two:
    - `kafka-topics.sh  -zookeeper localhost:2181 --alter --topic topic_basic --partitions 2`
