import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int M;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }


    static int[][] around = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int dfs(int y, int x) {
        if (y == M - 1 && x == N - 1) {
            return 1;
        }

        if (dp[y][x] == -1) {
            dp[y][x] = 0;
            for (int[] a : around) {
                int nextY = y + a[0];
                int nextX = x + a[1];

                if (isValid(nextY, nextX) && map[y][x] > map[nextY][nextX]) {
                    dp[y][x] += dfs(nextY, nextX);
                }
            }
        }

        return dp[y][x];
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < M && x >= 0 && x < N;
    }
}