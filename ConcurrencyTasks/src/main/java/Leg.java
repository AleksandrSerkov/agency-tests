import java.util.concurrent.Semaphore;

public class Leg implements Runnable {
    private static final Semaphore semLeft = new Semaphore(1);
    private static final Semaphore semRight = new Semaphore(0);

    private final String name;
    private final boolean isLeft;

    public Leg(String name, boolean isLeft) {
        this.name = name;
        this.isLeft = isLeft;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isLeft) {
                    semLeft.acquire();       // ждём, пока семафор не даст разрешение
                    System.out.println(name);
                    semRight.release();      // даём ход правой ноге
                } else {
                    semRight.acquire();
                    System.out.println(name);
                    semLeft.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
public static void main(String[] args) {
        new Thread(new Leg("left", true)).start();
        new Thread(new Leg("right", false)).start();
    }
}