public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите задачу: task1 | task2 | task3 | task4");
            return;
        }
        switch (args[0]) {
            case "task1": Task1Winner.run(); break;
            case "task2": Task2Top10.run();  break;
            case "task3": Task3Streams.run(); break;
            case "task4": Task4GraphSearch.run(); break;
            default:
                System.out.println("Неизвестная задача: " + args[0]);
        }
    }
}
