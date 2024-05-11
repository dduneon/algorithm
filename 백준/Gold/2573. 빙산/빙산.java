import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (true) {
            visited = new boolean[N][M];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] > 0) {
                        visited[i][j] = true;
                        dfs(i, j);
                        count++;
                    }
                }
            }
            result++;

            if (count >= 2) {
                System.out.println(result - 1);
                return;
            } else if (count == 0) {
                System.out.println(0);
                return;
            }
        }
    }

    static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void dfs(int i, int j) {
        int meltCount = 0;
        for (int[] a : around) {
            int nextI = i + a[0];
            int nextJ = j + a[1];

            if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < M && !visited[nextI][nextJ]) {
                if (map[nextI][nextJ] > 0) {
                    visited[nextI][nextJ] = true;
                    dfs(nextI, nextJ);
                } else if (map[nextI][nextJ] == 0) {
                    meltCount++;
                }
            }
        }

        map[i][j] = Math.max(map[i][j] - meltCount, 0);
    }
}