import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.function.Consumer;

public class ConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "chat-group");
        props.put("key.deserializer",
                  StringDeserializer.class.getName());
        props.put("value.deserializer",
                  StringDeserializer.class.getName());
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("chat-room"));

        System.out.println("Ожидаем сообщений...");
        while (true) {
            for (ConsumerRecord<String, String> record :
                 consumer.poll(Duration.ofMillis(100))) {
                System.out.printf("Получено: %s (партиция %d, офсет %d)%n",
                    record.value(), record.partition(), record.offset());
            }
        }
    }
}
