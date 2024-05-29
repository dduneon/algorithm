import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] scores = new int[N][3];
        for (int i = 0; i < N; i++) {
            scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // [0] -> 최대, [1] -> 최소
        int[][][] dp = new int[N][3][2];
        for (int i = 0; i < 3; i++) {
            dp[0][i][0] = scores[0][i];
            dp[0][i][1] = scores[0][i];
        }

        for (int i = 1; i < N; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][1][0]) + scores[i][0];
            dp[i][1][0] = Math.max(Math.max(dp[i - 1][0][0], dp[i - 1][1][0]), dp[i - 1][2][0]) + scores[i][1];
            dp[i][2][0] = Math.max(dp[i - 1][1][0], dp[i - 1][2][0]) + scores[i][2];

            dp[i][0][1] = Math.min(dp[i - 1][0][1], dp[i - 1][1][1]) + scores[i][0];
            dp[i][1][1] = Math.min(Math.min(dp[i - 1][0][1], dp[i - 1][1][1]), dp[i - 1][2][1]) + scores[i][1];
            dp[i][2][1] = Math.min(dp[i - 1][1][1], dp[i - 1][2][1]) + scores[i][2];
        }

        int max = Math.max(Math.max(dp[N - 1][0][0], dp[N - 1][1][0]), dp[N - 1][2][0]);
        int min = Math.min(Math.min(dp[N - 1][0][1], dp[N - 1][1][1]), dp[N - 1][2][1]);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(min);
        System.out.println(sb);
    }
}