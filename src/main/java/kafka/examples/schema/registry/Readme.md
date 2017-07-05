Schema Registery is used to stop bad messages from being published.
Code example here used 1 valid schema KafkaExampleMessage.avsc, and registers it with Schema Registery using:
-- SchemaRegistryUtils.addSchemaAndPrintItsInfo()

Now we try to send a BadMessage instance, generated using BadMessage.avsc.
This message does not contain the "f1" attribute that exists in the valid registered schema.

So, when we try to publish BadMessage message, the Avro producer validates it with Schema Registery server running at port 8081, and fails.
The error message is logged to console output: 
-- Error registering Avro schema: {"type":"record","name":"BadMessage","namespace":"kafka.examples.schema.registry","fields":[{"name":"f2","type":"int"}]}
