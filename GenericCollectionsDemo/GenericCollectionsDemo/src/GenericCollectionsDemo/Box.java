package GenericCollectionsDemo; 
// Коробка для фруктов определённого типа

import java.util.ArrayList;
import java.util.List;

class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    /** Добавляет фрукт в коробку */
    public void add(T fruit) {
        fruits.add(fruit);
    }

    /** Считает общий вес фруктов в коробке */
    public float getWeight() {
        float sum = 0;
        for (T f : fruits) {
            sum += f.getWeight();
        }
        return sum;
    }

    /** Сравнивает вес с другой коробкой */
    public boolean compare(Box<?> other) {
        return Math.abs(this.getWeight() - other.getWeight()) < 0.0001f;
    }

    /** Пересыпает фрукты в другую коробку того же типа */
    public void transferTo(Box<T> other) {
        other.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
