package Animal;

public class Cat extends Animal {
    public static int count = 0;

    public Cat(String name) {
        super(name, 200, 0);
        count++;
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать");
    }
}
