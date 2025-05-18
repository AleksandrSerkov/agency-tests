import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Task4GraphSearch {
    static class User {
        int id; String name; List<User> friends;
        User(int id,String name){this.id=id;this.name=name;friends=new ArrayList<>();}
        public void addFriend(User u){friends.add(u);}
    }

    public static void run() {
        // пример графа
        User a = new User(1, "Alice");
        User b = new User(2, "Bob");
        User c = new User(3, "Charlie");
        a.addFriend(b); b.addFriend(c); c.addFriend(a);

        System.out.println("BFS по имени Bob:");
        bfs(a, "Bob").forEach(u -> System.out.println(u.name));

        System.out.println("DFS по имени Bob:");
        dfs(a, "Bob").forEach(u -> System.out.println(u.name));
    }

    private static List<User> bfs(User start, String target) {
        List<User> found = new ArrayList<>();
        Queue<User> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        q.add(start); vis.add(start.id);
        while (!q.isEmpty()) {
            User u = q.poll();
            if (u.name.equals(target)) found.add(u);
            for (User f : u.friends) {
                if (vis.add(f.id)) q.add(f);
            }
        }
        return found;
    }

    private static List<User> dfs(User start, String target) {
        List<User> found = new ArrayList<>();
        Deque<User> st = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        st.push(start);
        while (!st.isEmpty()) {
            User u = st.pop();
            if (!vis.add(u.id)) continue;
            if (u.name.equals(target)) found.add(u);
            for (User f : u.friends) st.push(f);
        }
        return found;
    }
}