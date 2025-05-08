import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс для подсчёта уникальных слов и количества их вхождений
 */
public class WordCounter {
    /**
     * Возвращает множество уникальных слов из массива
     */
    public static Set<String> uniqueWords(String[] words) {
        return new HashSet<>(Arrays.asList(words));
    }

    /**
     * Возвращает отображение слово -> количество вхождений
     */
    public static Map<String, Integer> countOccurrences(String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        return counts;
    }
}