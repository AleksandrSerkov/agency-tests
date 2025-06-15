import java.util.Properties;
import java.util.Random;

public class ProducerApp {
    private static final String[] MESSAGES = {
        "Привет!", "Как дела?", "Что нового?", "Пока!"
    };

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer",
                  "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                  "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            String msg = MESSAGES[rnd.nextInt(MESSAGES.length)];
            producer.send(new ProducerRecord<>("chat-room", null, msg),
                (md, ex) -> {
                    if (ex == null) {
                        System.out.println("Отправлено: " + msg);
                    } else {
                        ex.printStackTrace();
                    }
                });
            try { Thread.sleep(500); } catch (InterruptedException e) { }
        }
        producer.close();
    }
}
