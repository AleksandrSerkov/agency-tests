import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Task3Streams {
    static class Client {
        int id, age;
        String name;
        List<String> phones;
        Client(int id,String n,int ag,List<String>ph){this.id=id;name=n;age=ag;phones=ph;}
        public int getId(){return id;} public int getAge(){return age;} public String getName(){return name;}
        public List<String> getPhones(){return phones;}
    }

    public static void run() {
        List<Client> clients = Arrays.asList(
            new Client(1, "Alice", 30, List.of("123","456")),
            new Client(2, "Bob", 25, List.of("789")),
            new Client(3, "Alice", 22, List.of("000"))
        );
        // 1. суммарный возраст по имени
        int sumAge = clients.stream()
            .filter(c -> c.getName().equals("Alice"))
            .mapToInt(Client::getAge).sum();
        System.out.println("Сумма возрастов Alice = " + sumAge);

        // 2. уникальные имена в порядке
        Set<String> names = clients.stream()
            .map(Client::getName)
            .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("Имена: " + names);

        
    }
}