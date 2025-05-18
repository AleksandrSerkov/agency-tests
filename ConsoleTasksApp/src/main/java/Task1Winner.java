import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1Winner {
    public static void run() {
        List<String> input = Arrays.asList(
            "alice 5", "bob 3", "alice 2", "bob 4", "charlie 7", "alice 1"
        );
        Map<String, Integer> scores = new HashMap<>();
        String winner = null;
        int maxScore = Integer.MIN_VALUE;
        for (String entry : input) {
            String[] parts = entry.split(" ");
            String name = parts[0];
            int pts = Integer.parseInt(parts[1]);
            scores.put(name, scores.getOrDefault(name, 0) + pts);
            int total = scores.get(name);
            if (total > maxScore) {
                maxScore = total;
                winner = name;
            }
        }
        System.out.println("Победитель: " + winner + " с очками=" + maxScore);
    }
}

