package kafka.examples.basic;


import java.util.ArrayList;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StringConsumer implements BasicProperties {

  static final Logger LOG = LoggerFactory.getLogger(StringConsumer.class);

  public static void main(final String[] args) {
    final StringConsumer consumer = new StringConsumer();
    consumer.consume();
  }

  void consume() {
    final ArrayList<String> topicList = new ArrayList<>();
    topicList.add(BasicProperties.TOPIC_NAME);
    final KafkaConsumer stringKafkaConsumer = new KafkaConsumer(getConsumerProperties());
    stringKafkaConsumer.subscribe(topicList);
    try {
      while (true) {
        final ConsumerRecords<String, String> consumerRecords = stringKafkaConsumer.poll(10);
        consumerRecords.spliterator().forEachRemaining(this::print);
      }
    } catch (final Exception exc) {
      LOG.trace("Error:", exc);
    } finally {
      stringKafkaConsumer.close();
    }
  }


  private void print(final ConsumerRecord<String, String> record) {
    LOG.info("Topic: {}, Partition: {}, Offset: {}, Key: {}, Value {}", record.topic(),
        record.partition(), record.offset(), record.key(), record.value());
  }
}



