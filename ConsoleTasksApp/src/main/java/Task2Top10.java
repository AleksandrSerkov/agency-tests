import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Task2Top10 {
    public static class Post { public final int id, likes; public Post(int i,int l){id=i;likes=l;} }

    public static void run() {
        List<Post> posts = new ArrayList<>();
        // эмуляция набора данных
        Random rnd = new Random();
        for (int i = 1; i <= 1_000_000; i++) {
            posts.add(new Post(i, rnd.nextInt(10_000)));
        }
        PriorityQueue<Post> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.likes));
        for (Post p : posts) {
            pq.offer(p);
            if (pq.size() > 10) pq.poll();
        }
        List<Post> top = new ArrayList<>(pq);
        top.sort(Comparator.comparingInt(p -> -p.likes));
        System.out.println("Топ-10 постов по лайкам:");
        top.forEach(p -> System.out.println("id="+p.id+" likes="+p.likes));
    }
}