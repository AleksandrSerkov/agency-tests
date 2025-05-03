package participants;
public class Human implements Participant {
    private String name;
    private int maxRun;
    private int maxJump;
    private int successCount = 0;

    public Human(String name, int maxRun, int maxJump) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public boolean run(int distance) {
        boolean success = distance <= maxRun;
        System.out.printf("%s пытается пробежать %d м: %s%n", name, distance, success ? "Удача!" : "Не смог.");
        if (success) successCount++;
        return success;
    }

    @Override
    public boolean jump(int height) {
        boolean success = height <= maxJump;
        System.out.printf("%s пытается прыгнуть %d м: %s%n", name, height, success ? "Удача!" : "Не смог.");
        if (success) successCount++;
        return success;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getSuccessCount() {
        return successCount;
    }
}