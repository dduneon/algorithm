import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            map[first][second] = 1;
            map[second][first] = 1;
        }

        System.out.println(bfs(A, B));
    }

    public static int bfs(int start, int end) {
        Node startNode = new Node(start, 0);

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.num == end) {
                return current.chon;
            }

            for (int i = 1; i <= N; i++) {
                if (map[current.num][i] == 1 && !visited[i]) {
                    queue.add(new Node(i, current.chon + 1));
                    visited[i] = true;
                }
            }
        }

        return -1;
    }
}

class Node {
    int num;
    int chon;

    public Node(int num, int chon) {
        this.num = num;
        this.chon = chon;
    }
}
