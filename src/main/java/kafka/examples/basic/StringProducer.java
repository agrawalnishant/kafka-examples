package kafka.examples.basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringProducer implements BasicProperties {

  static final Logger LOG = LoggerFactory.getLogger(StringProducer.class);

  private static final int MAX_LOOPS = 200;

  public static void main(final String[] args) {
    final StringProducer stringProducer = new StringProducer();
    stringProducer.produce();
  }

  void produce() {
    final KafkaProducer<String, String> stringKafkaProducer = new KafkaProducer<String, String>(
        getProducerProperties());
    try {
      for (int i = 0; i < MAX_LOOPS; i++) {
        final Object o = stringKafkaProducer
            .send(new ProducerRecord<String, String>(BasicProperties.TOPIC_NAME, "msg: " + i));
        LOG.info("Produced Message = [" + o + "]");

      }
    } catch (final Exception ex) {
      LOG.trace("Error: ", ex);
    } finally {
      stringKafkaProducer.close();
    }
  }
}