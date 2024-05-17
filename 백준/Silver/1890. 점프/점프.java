import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static long[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        dfs(0, 0);

        System.out.println(dp[0][0]);
    }

    private static int[][] move = {{1, 0}, {0, 1}};

    private static long dfs(int y, int x) {
        if (y == N - 1 && x == N - 1) {
            return 1;
        }

        if (dp[y][x] == -1) {
            dp[y][x] = 0;

            if (map[y][x] == 0) {
                return 0;
            }

            for (int[] m : move) {
                int nextY = y + m[0] * map[y][x];
                int nextX = x + m[1] * map[y][x];

                if (isValid(nextY, nextX)) {
                    dp[y][x] += dfs(nextY, nextX);
                }
            }
        }

        return dp[y][x];
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}