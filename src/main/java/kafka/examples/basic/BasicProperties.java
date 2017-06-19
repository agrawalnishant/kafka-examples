package kafka.examples.basic;


public interface BasicProperties {

  int MESSAGE_COUNT = 2000;

  int AVAILABLE_KAFKA_PARTITIONS_MIN = 2;

  String BROKER_BOOTSTRAP_URL = "localhost:9092, localhost:9093";

  String TOPIC_NAME = "topic_basic";

  default java.util.Properties getProducerProperties() {
    final java.util.Properties props = new java.util.Properties();
    props.put("bootstrap.servers", BROKER_BOOTSTRAP_URL);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    return props;
  }

  default java.util.Properties getConsumerProperties() {
    final java.util.Properties props = getProducerProperties();
    props.put("group.id", "basic");
    return props;
  }
}