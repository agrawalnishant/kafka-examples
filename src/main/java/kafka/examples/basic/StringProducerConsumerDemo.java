package kafka.examples.basic;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringProducerConsumerDemo implements BasicProperties {

  public static void main(final String[] args) {

    final StringProducerConsumerDemo demo = new StringProducerConsumerDemo();
    demo.perform();

  }

  private void perform() {

    final StringConsumer consumer = new StringConsumer();
    final ExecutorService consumerExecutorService = Executors.newSingleThreadExecutor();
    consumerExecutorService.execute(() -> consumer.consume(getConsumerProperties(), TOPIC_NAME));

    final StringProducer producer = new StringProducer();
    final ExecutorService producerExecutorService = Executors.newSingleThreadExecutor();
    producerExecutorService
        .execute(() -> producer.produce(getProducerProperties(), TOPIC_NAME, MESSAGE_COUNT));
  }
}