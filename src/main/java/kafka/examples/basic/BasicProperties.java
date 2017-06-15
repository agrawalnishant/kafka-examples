package kafka.examples.basic;


interface BasicProperties {
    String BROKER_BOOTSTRAP_URL = "localhost:9092";

    String TOPIC_NAME = "topic_basic";

    static java.util.Properties getProducerProperties() {
        java.util.Properties props = new java.util.Properties();
        props.put("bootstrap.servers", BROKER_BOOTSTRAP_URL);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    static java.util.Properties getConsumerProperties() {
        java.util.Properties props = getProducerProperties();
        props.put("group.id", "basic");
        return props;
    }
}
