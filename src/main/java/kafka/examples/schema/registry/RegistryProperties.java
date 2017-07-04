package kafka.examples.schema.registry;


import kafka.examples.basic.BasicProperties;

public interface RegistryProperties extends BasicProperties {

  public static final String VERSIONS = "/versions";
  String SCHEMA_REG_URL = "http://localhost:8081";
  static final String DEFAULT_BASE_REGISTRY_SUBJECT_URL = SCHEMA_REG_URL + "/subjects/";
  static final String VALUE_SUFFIX = "-value";
  static final String SCHEMA_FILE = "KafkaExampleMessage.avsc";
  String KAFKA_AVRO_SERIALIZER = "io.confluent.kafka.serializers.KafkaAvroSerializer";
  String KAFKA_AVRO_DESERIALIZER = "io.confluent.kafka.serializers.KafkaAvroDeserializer";


  @Override
  default java.util.Properties getProducerProperties() {
    final java.util.Properties props = new java.util.Properties();
    props.put("bootstrap.servers", BROKER_BOOTSTRAP_URL);
    props.put("key.serializer", KAFKA_AVRO_SERIALIZER);
    props.put("value.serializer", KAFKA_AVRO_SERIALIZER);
    props.put("schema.registry.url", SCHEMA_REG_URL);
    return props;
  }

  @Override
  default java.util.Properties getConsumerProperties() {
    final java.util.Properties props = getProducerProperties();
    props.put("group.id", GROUP_ID);
    props.put("key.deserializer", KAFKA_AVRO_DESERIALIZER);
    props.put("value.deserializer", KAFKA_AVRO_DESERIALIZER);
    return props;
  }
}
