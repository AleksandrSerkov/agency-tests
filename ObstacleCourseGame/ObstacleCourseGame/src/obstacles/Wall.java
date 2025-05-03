package obstacles;

import participants.Participant;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Participant p) {
        return p.jump(height);
    }
}