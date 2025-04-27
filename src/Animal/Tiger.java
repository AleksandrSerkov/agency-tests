package Animal;

public class Tiger extends Animal {
    public static int count = 0;  // счётчик тигров

    public Tiger(String name) {
        super(name, 1000, 200);  // бег до 1000 м, плавание до 200 м
        count++;
    }
}

