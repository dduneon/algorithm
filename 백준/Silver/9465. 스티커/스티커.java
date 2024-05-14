import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for (int t = 0; t < test_case; t++) {
            int N = Integer.parseInt(br.readLine());

            int[][] map = new int[2][N];
            map[0] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] dp = new int[2][N + 1];
            dp[0][1] = map[0][0];
            dp[1][1] = map[1][0];

            for (int i = 2; i <= N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + map[0][i - 1];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + map[1][i - 1];
            }

            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }

    }
}