package kafka.examples.basic;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringProducerConsumerGroupDemo implements BasicProperties {

  public static void main(final String[] args) {

    final StringProducerConsumerGroupDemo demo = new StringProducerConsumerGroupDemo();
    demo.perform();

  }

  /**
   * Single partition attaches to s single consumer in a group. Need at least 2 Partitions for
   * different consumers to receive messages.
   */
  private void perform() {

    final ExecutorService consumerExecutorService = Executors
        .newFixedThreadPool(AVAILABLE_KAFKA_PARTITIONS_MIN);

    for (int count = 0; count < AVAILABLE_KAFKA_PARTITIONS_MIN; count++) {
      final StringConsumer consumers = new StringConsumer();
      consumerExecutorService
          .execute(() -> consumers.consume(getConsumerProperties(), TOPIC_NAME));
    }

    final StringProducer producer = new StringProducer();
    final ExecutorService producerExecutorService = Executors.newSingleThreadExecutor();
    producerExecutorService
        .execute(() -> producer.produce(getProducerProperties(), TOPIC_NAME, MESSAGE_COUNT));
  }
}