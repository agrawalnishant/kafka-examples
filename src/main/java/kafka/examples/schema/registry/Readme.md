Schema Registry is used to stop invalid messages from being published on a Kafka Topic.
Code example here use `KafkaExampleMessage.avsc`, an Avro schema, and registers it with Schema Registery using: `SchemaRegistryUtils.addSchemaAndPrintItsInfo()`.
This means that messages that comply with this schema will be considered as valid by the Schema Registry, and hence by Avro Message Serializer.

We try to send a BadMessage instance, generated using `BadMessage.avsc`.
This message does not contain the "f1" attribute that exists in the valid registered schema- `KafkaExampleMessage.avsc`.
So, when we try to publish BadMessage message, the Avro producer validates it with Schema Registery server running at port 8081, and fails.
The error message is logged to console output: 
`Error registering Avro schema: {"type":"record","name":"BadMessage","namespace":"kafka.examples.schema.registry","fields":[{"name":"f2","type":"int"}]}`

Next, the Avro Message Producer is able to publish KafkaExampleMessage messages, and these are consumed by the Avro Consumer.
