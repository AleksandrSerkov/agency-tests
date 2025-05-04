package GenericCollectionsDemo;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Демонстрация swap и toList
        Integer[] numbers = {1, 2, 3, 4};
        System.out.println("До swap: " + Arrays.toString(numbers));
        Utils.swap(numbers, 0, 3);
        System.out.println("После swap: " + Arrays.toString(numbers));

        List<Integer> numberList = Utils.toList(numbers);
        System.out.println("Список из массива: " + numberList);

        // Работа с коробками фруктов
        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());

        System.out.println("Вес коробки 1 с яблоками: " + appleBox1.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());

        // Сравнение коробок
        boolean equal = appleBox1.compare(orangeBox);
        System.out.println("Коробки равны по весу? " + equal);

        // Пересыпание фруктов
        Box<Apple> appleBox2 = new Box<>();
        appleBox1.transferTo(appleBox2);
        System.out.println("Вес первой коробки после пересыпания: " + appleBox1.getWeight());
        System.out.println("Вес второй коробки после пересыпания: " + appleBox2.getWeight());
    }
}
