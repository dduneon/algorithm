import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < test_cases; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 가로 길이 M
            M = Integer.parseInt(st.nextToken());
            // 세로 길이 N
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            boolean[][] map = new boolean[N][M];
            boolean[][] visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                map[Y][X] = true;
            }

            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] && !visited[i][j]) {
                        bfs(new int[] {i, j}, map, visited);
                        result++;
                    }
                }
            }

            System.out.println(result);
        }
    }

    static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static void bfs(int[] startPos, boolean[][] map, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPos);
        visited[startPos[0]][startPos[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] a : around) {
                int nextY = current[0] + a[0];
                int nextX = current[1] + a[1];
                if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M && map[nextY][nextX] && !visited[nextY][nextX]) {
                    queue.add(new int[] {nextY, nextX});
                    visited[nextY][nextX] = true;
                }
            }
        }
    }
}