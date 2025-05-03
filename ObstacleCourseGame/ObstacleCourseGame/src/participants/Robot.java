package participants;
public class Robot implements Participant {
    private String id;
    private int maxRun;
    private int maxJump;
    private int successCount = 0;

    public Robot(String id, int maxRun, int maxJump) {
        this.id = id;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public boolean run(int distance) {
        boolean success = distance <= maxRun;
        System.out.printf("Робот %s пытается пробежать %d м: %s%n", id, distance, success ? "Удача!" : "Не смог.");
        if (success) successCount++;
        return success;
    }

    @Override
    public boolean jump(int height) {
        boolean success = height <= maxJump;
        System.out.printf("Робот %s пытается прыгнуть %d м: %s%n", id, height, success ? "Удача!" : "Не смог.");
        if (success) successCount++;
        return success;
    }

    @Override
    public String getName() {
        return id;
    }

    public int getSuccessCount() {
        return successCount;
    }
}
