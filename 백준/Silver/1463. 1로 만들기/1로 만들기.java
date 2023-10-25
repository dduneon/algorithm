import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[number + 1];
        int result = BFS(number, visited);
        System.out.println(result);
    }

    public static int BFS(int start, boolean[] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node popItem = queue.poll();
            int value = popItem.value;
            int depth = popItem.depth;

            if (value == 1)
                return depth;

            if (value % 3 == 0 && !visited[value / 3])
                queue.add(new Node(value / 3, depth + 1));
            if (value % 2 == 0 && !visited[value / 2])
                queue.add(new Node(value / 2, depth + 1));
            if (value > 1 && !visited[value - 1])
                queue.add(new Node(value - 1, depth + 1));
        }
        return -1;
    }
}


class Node {
    int value;
    int depth;

    public Node(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }
}
