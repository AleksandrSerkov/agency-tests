package ObstacleCourseGame;

import obstacles.Obstacle;
import obstacles.Treadmill;
import obstacles.Wall;
import participants.Cat;
import participants.Human;
import participants.Participant;
import participants.Robot;



public class Main {
    public static void main(String[] args) {
        Participant[] participants = {
            new Human("Иван", 800, 2),
            new Cat("Барсик", 200, 2),
            new Robot("R2-D2", 1000, 1)
        };

        Obstacle[] obstacles = {
            new Treadmill(300),
            new Wall(1),
            new Treadmill(500),
            new Wall(2)
        };

        for (Participant p : participants) {
            System.out.println("\n---- " + p.getName() + " начинает испытание ----");
            for (Obstacle o : obstacles) {
                if (!o.overcome(p)) {
                    System.out.println(p.getName() + " выбывает из соревнований.");
                    break;
                }
            }
            if (p instanceof Human) {
                System.out.printf("Успешных преодолений: %d%n", ((Human) p).getSuccessCount());
            } else if (p instanceof Cat) {
                System.out.printf("Успешных преодолений: %d%n", ((Cat) p).getSuccessCount());
            } else if (p instanceof Robot) {
                System.out.printf("Успешных преодолений: %d%n", ((Robot) p).getSuccessCount());
            }
        }
    }
}