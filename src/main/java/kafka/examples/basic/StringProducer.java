package kafka.examples.basic;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringProducer {

  static final Logger LOG = LoggerFactory.getLogger(StringProducer.class);


  void produce(final Properties producerProps, final String topicName, final int messageCount) {
    final KafkaProducer<String, String> stringKafkaProducer = new KafkaProducer<String, String>(
        producerProps);
    try {
      for (int i = 0; i < messageCount; i++) {
        final Object o = stringKafkaProducer
            .send(new ProducerRecord<String, String>(topicName, "msg: " + i));
        LOG.info("Produced Message = [" + o + "]");

      }
    } catch (final Exception ex) {
      LOG.trace("Error: ", ex);
    } finally {
      stringKafkaProducer.close();
    }
  }
}