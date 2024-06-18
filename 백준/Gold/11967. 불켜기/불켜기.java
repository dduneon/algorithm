import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static HashMap<Node, List<Node>> list;
    private static boolean[][] map;
    private static boolean[][] visited;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N*N 개의 방 (1, 1) ~ (N, N)
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new HashMap<>();
        for (int i = 0; i < M; i++) {
            // (x, y) 방에서 (a, b) 방의 불을 켜고 끌 수 있음
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Node start = new Node(y, x);
            Node end = new Node(b, a);

            if (list.containsKey(start)) {
                List<Node> l = list.get(start);
                l.add(end);
            } else {
                List<Node> l = new ArrayList<>();
                l.add(end);
                list.put(start, l);
            }
        }

        map = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        int result = solve();
        System.out.println(result);
    }

    private static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static int solve() {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> cantGo = new HashSet<>();
        queue.add(new Node(1, 1));
        visited[1][1] = true;
        map[1][1] = true;

        int result = 1;
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (list.containsKey(current)) {
                List<Node> lightOn = list.get(current);
                for (Node n : lightOn) {
                    if (!map[n.y][n.x]) {
                        map[n.y][n.x] = true;
                        if (cantGo.contains(n)) {
                            queue.add(n);
                        }
                        result++;
                    }
                }
            }

            for (int[] a : around) {
                int nextY = current.y + a[0];
                int nextX = current.x + a[1];

                if (isValid(nextY, nextX) && !visited[nextY][nextX]) {
                    if (map[nextY][nextX]) {
                        visited[nextY][nextX] = true;
                        queue.add(new Node(nextY, nextX));
                    } else {
                        cantGo.add(new Node(nextY, nextX));
                    }
                }
            }
        }

        return result;
    }

    private static boolean isValid(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }
}


class Node {
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}