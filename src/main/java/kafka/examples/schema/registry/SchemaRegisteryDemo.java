package kafka.examples.schema.registry;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaRegisteryDemo implements RegistryProperties {

  private static final Logger LOG = LoggerFactory.getLogger(SchemaRegisteryDemo.class);
  private static final int WAIT_TIME_MILLIS = 20000;

  public static void main(final String[] args) {
    final SchemaRegisteryDemo demo = new SchemaRegisteryDemo();
    demo.perform();
  }

  private void perform() {

    try {
      //First we register a schema with schema registry
      SchemaRegistryUtils.addSchemaAndGetRegistrationInfo();

      //Now we start a consumer to consume the messages to be produced later.
      final AvroMessageConsumer consumer = new AvroMessageConsumer();
      final ExecutorService consumerExecutorService = Executors.newSingleThreadExecutor();
      consumerExecutorService.execute(() -> consumer.consume(getConsumerProperties(), TOPIC_NAME));

      final AvroMessageProducer producer = new AvroMessageProducer();
      final ExecutorService producerExecutorService = Executors.newSingleThreadExecutor();

      //First Send Bad Message, which should result in error in sending: "schema being registered is incompatible with an earlier schema"
      producerExecutorService
          .execute(() -> producer.produceValidMessage(getProducerProperties(), TOPIC_NAME, MESSAGE_COUNT));

      //Then send message valid as per schema
      producerExecutorService
          .execute(() -> producer.produceInvalidMessage(getProducerProperties(), TOPIC_NAME, MESSAGE_COUNT));
      LOG.info("Will wait " + (WAIT_TIME_MILLIS / 1000) + "seconds for consumer output before EXIT.");
      Thread.sleep(WAIT_TIME_MILLIS);
    } catch (final Exception ex) {
      ex.printStackTrace();
    } finally {
      System.exit(0);
    }
  }
}