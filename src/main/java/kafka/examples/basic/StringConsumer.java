package kafka.examples.basic;


import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class StringConsumer {

  static final Logger LOG = LoggerFactory.getLogger(StringConsumer.class);
  private static final String MDC_KEY_CONSUMERID = "consumerid";

  // UUID used in Logback MDC to identify messages consumed by different consumers
  private final UUID CONSUMER_ID = UUID.randomUUID();

  /**
   * Consumes messages received over `topicName` kafka topic.
   */
  public void consume(final Properties consumerProps, final String topicName) {
    MDC.put(MDC_KEY_CONSUMERID, CONSUMER_ID.toString());

    // Prepare List of topics this consumer will consume messages from.
    // Here we only consume from a single topic.
    final ArrayList<String> topicList = new ArrayList<>();
    topicList.add(topicName);

    // Create Kafka consumer to listen to the topic name passes in parameter.
    final KafkaConsumer stringKafkaConsumer = new KafkaConsumer(consumerProps);
    stringKafkaConsumer.subscribe(topicList);
    try {
      while (true) {

        //Consumer will poll partition every 10 milli-secs to check for new messages.
        final ConsumerRecords<String, String> consumerRecords = stringKafkaConsumer.poll(10);

        // Iterate over each message (<code> ConsumerRecord </code>), and print its attributes.
        consumerRecords.spliterator().forEachRemaining(this::print);
      }
    } catch (final Exception exc) {
      LOG.trace("Error:", exc);
    } finally {
      stringKafkaConsumer.close();
    }
    MDC.clear();
  }


  private void print(final ConsumerRecord<String, String> record) {
    LOG.info(" Topic: {}, Partition: {}, Offset: {}, Key: {}, Value {}", record.topic(), record.partition(),
        record.offset(), record.key(), record.value());
  }
}