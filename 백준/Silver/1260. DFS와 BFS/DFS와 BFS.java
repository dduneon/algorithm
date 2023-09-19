// 1260: DFS, BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] edge;
    static boolean[] visited;
    static StringBuilder sb;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        int N, M, V;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        edge = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            edge[n1][n2] = true;
            edge[n2][n1] = true;
        }
        visited = new boolean[N];
        sb = new StringBuilder();
        dfs(V, N, 0);
        System.out.println(sb.toString());
        visited = new boolean[N];
        sb = new StringBuilder();
        queue = new LinkedList<>();
        bfs(V, N, 0);
        System.out.println(sb.toString());
    }

    public static void dfs(int current, int end, int depth) {
        if (depth == end)
            return;
        visited[current] = true;
        sb.append(current + " ");
        for (int i = 1; i < end; i++) {
            if (edge[current][i] && !visited[i]) {
                dfs(i, end, depth + 1);
            }
        }
    }

    public static void bfs(int current, int end, int depth) {
        if (depth == end)
            return;
        visited[current] = true;
        sb.append(current + " ");
        for (int i = 1; i < end; i++) {
            if (edge[current][i] && !visited[i]) {
                visited[i] = true;
                queue.add(i);
            }
        }
        if (!queue.isEmpty())
            bfs(queue.remove(), end, depth + 1);
    }
}
