package obstacles;

import participants.Participant;

public class Treadmill implements Obstacle {
    private int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public boolean overcome(Participant p) {
        return p.run(length);
    }
}
