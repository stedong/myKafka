import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Random;

public class Producer {
    private KafkaProducer<Integer, String> _producer;
    private String _message;

    public Producer() {
        _message = "Error";
        Properties properties = new Properties();
        properties.put("bootstrap.servers",  "localhost:9092");
        properties.put("client.id", "DemoProducer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        _producer = new KafkaProducer<>(properties);
    }

    public void sendMessage(String topic, int key, String value) throws InterruptedException {
        _producer.send(new ProducerRecord<>(topic, key, value), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (metadata != null) {
                    System.out.println("topic = " + topic + ", key = " + key + ", value = " + value);
                } else {
                    System.out.println("Failed to send " + _message);
                }
            }
        });
    }

    public void setMessage(String _message) {
        this._message = _message;
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = (char)('a' + (int)(Math.random() * 11));
                sb.append(c);
            }
            System.out.println(sb.toString());
            producer.sendMessage("henry", i, sb.toString());
            Thread.sleep(5000);
        }
    }
}



