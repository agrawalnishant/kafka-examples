package kafka.examples.schema.registry;


import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroMessageProducer {

  static final Logger LOG = LoggerFactory.getLogger(AvroMessageProducer.class);

  /**
   * Creates messageCount number of Avro messages, and sends them to topicName.
   * Used producerProps to connect to Kafka Broker through Zookeeper.
   */
  public void produceValidMessage(final Properties producerProps, final String topicName, final int messageCount) {

    final KafkaProducer<String, KafkaExampleMessage> avroProducer = new KafkaProducer<String, KafkaExampleMessage>(
        producerProps);

    try {
      for (int i = 0; i < messageCount; i++) {
        final KafkaExampleMessage message = new KafkaExampleMessage("" + i);
        final Object o = avroProducer.send(new ProducerRecord<String, KafkaExampleMessage>(topicName, message));
        System.out.println("o = [" + o + "]");
      }
    } catch (final Exception ex) {
      ex.printStackTrace();
    } finally {
      avroProducer.close();
    }


  }

  /**
   * Creates messageCount number of Avro messages, and sends them to topicName.
   * Used producerProps to connect to Kafka Broker through Zookeeper.
   */
  public void produceInvalidMessage(final Properties producerProps, final String topicName, final int messageCount) {

    final KafkaProducer<String, BadMessage> avroProducer = new KafkaProducer<String, BadMessage>(producerProps);

    try {
      for (int i = 0; i < messageCount; i++) {
        final BadMessage message = new BadMessage(i);
        final Object o = avroProducer.send(new ProducerRecord<String, BadMessage>(topicName, message));
        System.out.println("o = [" + o + "]");
      }
    } catch (final Exception ex) {
      ex.printStackTrace();
    } finally {
      avroProducer.close();
    }


  }

}