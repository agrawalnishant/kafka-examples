package kafka.examples.basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class StringProducer {

    private static final int MAX_LOOPS = 100;

    public static void main(String[] args) {
        produce();
    }

    static void produce() {
        KafkaProducer<String, String> stringKafkaProducer = new KafkaProducer<String, String>(BasicProperties.getProducerProperties());
        try {
            for (int i = 0; i < MAX_LOOPS; i++) {
                Object o = stringKafkaProducer.send(new ProducerRecord<String, String>(BasicProperties.TOPIC_NAME, "msg: " + i));
                System.out.println("Produced Message = [" + o + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            stringKafkaProducer.close();
        }

    }
}
