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

  private static final Logger LOG = LoggerFactory.getLogger(AvroMessageConsumer.class);
  private static final String MDC_KEY_CONSUMERID = "consumerid";

  // UUID used in Logback MDC to identify messages consumed by different consumers
  private final UUID CONSUMER_ID = UUID.randomUUID();

  /**
   * Consumes Avro messages received over topicName kafka topic.
   */
  public void consume(final Properties consumerProps, final String topicName) {
    MDC.put(MDC_KEY_CONSUMERID, CONSUMER_ID.toString());

    // Prepare List of topics this consumer will consume messages from.
    // Here we only consume from a single topic.
    final KafkaConsumer kafkaConsumer = new KafkaConsumer(consumerProps);
    final ArrayList<String> topicList = new ArrayList<>();
    topicList.add(topicName);
    kafkaConsumer.subscribe(topicList);

    try {

      while (true) {
        final ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10);

        // Iterate over each message (<code> ConsumerRecord </code>), and print its attributes.
        consumerRecords.spliterator().forEachRemaining(this::print);
      }
    } catch (final Exception exc) {
      exc.printStackTrace();
    } finally {
      kafkaConsumer.close();
    }

  }

  private void print(final ConsumerRecord<String, String> record) {
    LOG.info(" Topic: {}, Partition: {}, Offset: {}, Key: {}, Value {}", record.topic(), record.partition(),
        record.offset(), record.key(), record.value());
  }
}