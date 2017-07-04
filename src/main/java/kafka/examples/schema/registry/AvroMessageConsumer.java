package kafka.examples.schema.registry;


import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class AvroMessageConsumer {

  static final Logger LOG = LoggerFactory.getLogger(AvroMessageConsumer.class);

  // UUID used in Logback MDC to identify messages consumed by different consumers
  private final UUID consumerId = UUID.randomUUID();

  /**
   * Consumes Avro messages received over topicName kafka topic.
   */
  public void consume(final Properties consumerProps, final String topicName) {
    MDC.put("consumerid", consumerId.toString());

    // Prepare List of topics this consumer will consume messages from.
    // Here we only consume from a single topic.
    final KafkaConsumer kafkaConsumer = new KafkaConsumer(consumerProps);
    final ArrayList<String> topicList = new ArrayList<>();
    topicList.add(topicName);

    kafkaConsumer.subscribe(topicList);

    try {

      while (true) {
        final ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10);
        for (final ConsumerRecord<String, String> record : consumerRecords) {
          System.out.println(String
              .format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value %s", record.topic(), record.partition(),
                  record.offset(), record.key(), record.value()));
        }
      }
    } catch (final Exception exc) {
      exc.printStackTrace();
    } finally {
      kafkaConsumer.close();
    }


  }

}