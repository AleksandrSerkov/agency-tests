import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Простой телефонный справочник: фамилия -> список номеров
 */
public class PhoneDirectory {
    private Map<String, List<String>> directory = new HashMap<>();

    /**
     * Добавляет телефон под указанной фамилией
     */
    public void add(String surname, String phone) {
        directory.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone);
    }

    /**
     * Возвращает список телефонов по фамилии, или пустой список, если нет записей
     */
    public List<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptyList());
    }
}
