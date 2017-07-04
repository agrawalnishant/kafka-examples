package kafka.examples.schema.registry;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchemaRegisteryDemo implements RegistryProperties {

  public static void main(final String[] args) {

    final SchemaRegisteryDemo demo = new SchemaRegisteryDemo();
    demo.perform();

  }

  private void perform() {

    final AvroMessageConsumer consumer = new AvroMessageConsumer();
    final ExecutorService consumerExecutorService = Executors.newSingleThreadExecutor();
    consumerExecutorService.execute(() -> consumer.consume(getConsumerProperties(), TOPIC_NAME));

    final AvroMessageProducer producer = new AvroMessageProducer();
    final ExecutorService producerExecutorService = Executors.newSingleThreadExecutor();
    producerExecutorService.execute(() -> producer.produce(getProducerProperties(), TOPIC_NAME, MESSAGE_COUNT));
  }
}