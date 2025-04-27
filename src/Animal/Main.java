package Animal;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
            new Dog("Бобик"),
            new Cat("Мурзик"),
            new Tiger("Шерхан"),
            new Dog("Шарик"),
            new Cat("Барсик")
        };

        for (Animal animal : animals) {
            animal.run(300);
            animal.swim(5);
            System.out.println();
        }

        System.out.println("Создано объектов:");
        System.out.println("— Всего животных: " + Animal.count);
        System.out.println("— Собак: " + Dog.count);
        System.out.println("— Котов: " + Cat.count);
        System.out.println("— Тигров: " + Tiger.count);
    }
}
