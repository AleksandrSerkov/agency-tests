import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Демонстрация практической работы по коллекциям Map и Set
 */
public class Main {
    public static void main(String[] args) {
        // Задача 1: уникальные слова и их количество
        String[] words = {"java", "code", "java", "map", "set", "code", "iterator", "map", "java"};
        System.out.println("Исходный массив слов: " + Arrays.toString(words));

        Set<String> unique = WordCounter.uniqueWords(words);
        System.out.println("Уникальные слова: " + unique);

        Map<String, Integer> occurrences = WordCounter.countOccurrences(words);
        System.out.println("Количество вхождений:");
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Задача 2: телефонный справочник
        PhoneDirectory dir = new PhoneDirectory();
        dir.add("Ivanov", "+7-903-123-45-67");
        dir.add("Petrov", "+7-916-987-65-43");
        dir.add("Ivanov", "+7-903-765-43-21");

        System.out.println("Номера Ivanov: " + dir.get("Ivanov"));
        System.out.println("Номера Sidorov (нет записи): " + dir.get("Sidorov"));
    }
}
