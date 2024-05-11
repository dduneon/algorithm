import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int min = 100;
        int max = 2;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int result = 1;
        for (int i = min; i <= max; i++) {
            visited = new boolean[N][N];
            int count = 0;
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < N; m++) {
                    if (map[n][m] > i && !visited[n][m]) {
                        visited[n][m] = true;
                        dfs(n, m, i);
                        count++;
                    }
                }
            }
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void dfs(int i, int j, int height) {
        for (int[] a : around) {
            int nextI = i + a[0];
            int nextJ = j + a[1];

            if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N && !visited[nextI][nextJ]
                    && map[nextI][nextJ] > height) {
                visited[nextI][nextJ] = true;
                dfs(nextI, nextJ, height);
            }
        }
    }
}
