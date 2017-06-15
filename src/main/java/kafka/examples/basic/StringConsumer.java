package kafka.examples.basic;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;

class StringConsumer {

    public static void main(String[] args) {
        consume();
    }

    static void consume() {
        KafkaConsumer stringKafkaConsumer = new KafkaConsumer(BasicProperties.getConsumerProperties());
        ArrayList<String> topicList = new ArrayList<>();
        topicList.add(BasicProperties.TOPIC_NAME);
        stringKafkaConsumer.subscribe(topicList);
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = stringKafkaConsumer.poll(10);
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value %s", record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            stringKafkaConsumer.close();
        }

    }


}
