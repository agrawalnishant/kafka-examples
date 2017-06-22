package kafka.examples.schema.registry;


public class RegistryProperties {

  public static final String VERSIONS = "/versions";
  static final String DEFAULT_BASE_REGISTRY_SUBJECT_URL = "http://localhost:8081/subjects/";
  static final String VALUE_SUFFIX = "-value";
  static final String SCHEMA_FILE = "KafkaExampleMessage.avsc";
}
