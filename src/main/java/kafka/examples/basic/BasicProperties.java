package kafka.examples.basic;


public interface BasicProperties {

  int MESSAGE_COUNT = 20;

  int AVAILABLE_KAFKA_PARTITIONS_MIN = 2;

  String BROKER_BOOTSTRAP_URL = "localhost:9092, localhost:9093";

  String TOPIC_NAME = "topic_basic";
  String GROUP_ID = "basic";
  String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
  String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

  default java.util.Properties getProducerProperties() {
    final java.util.Properties props = new java.util.Properties();
    props.put("bootstrap.servers", BROKER_BOOTSTRAP_URL);
    props.put("key.serializer", STRING_SERIALIZER);
    props.put("value.serializer", STRING_SERIALIZER);
    return props;
  }

  default java.util.Properties getConsumerProperties() {
    final java.util.Properties props = getProducerProperties();
    props.put("group.id", GROUP_ID);
    props.put("key.deserializer", STRING_DESERIALIZER);
    props.put("value.deserializer", STRING_DESERIALIZER);
    return props;
  }
}