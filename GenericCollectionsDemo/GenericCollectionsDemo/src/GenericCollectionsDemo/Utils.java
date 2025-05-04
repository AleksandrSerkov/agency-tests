package GenericCollectionsDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Утилитарный класс с дженерик-методами
public class Utils {
    /**
     * Обменивает элементы массива любого ссылочного типа
     * @param array массив элементов типа T
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    public static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Преобразует массив в ArrayList
     * @param array массив элементов типа T
     * @return список с элементами массива
     */
    public static <T> List<T> toList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}