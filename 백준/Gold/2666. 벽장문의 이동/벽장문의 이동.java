import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][][] dp;
    static int[] door;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] open = new int[2];
        for (int i = 0; i < 2; i++) {
            open[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        door = new int[M];
        for (int i = 0; i < M; i++) {
            door[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[M][N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= N; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        System.out.println(solve(0, open[0], open[1]));
    }

    private static int solve(int order, int open1, int open2) {
        if (order == M) {
            return 0;
        }

        if (dp[order][open1][open2] != -1) {
            return dp[order][open1][open2];
        }

        int pick1 = solve(order + 1, door[order], open2) + Math.abs(door[order] - open1);
        int pick2 = solve(order + 1, open1, door[order]) + Math.abs(door[order] - open2);

        dp[order][open1][open2] = Math.min(pick1, pick2);

        return dp[order][open1][open2];
    }
}