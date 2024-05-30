import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        if (N == M) {
            System.out.println(1);
            return;
        }

        int start = 1;
        int max = 0;
        int[] divide = new int[M + 1];
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            divide[i] = vip - start;
            max = Math.max(max, divide[i]);
            start = vip + 1;
        }
        divide[M] = N - start + 1;
        max = Math.max(max, divide[M]);

        int[] dp = new int[max + 1];
        dp[0] = 1;
        dp[1] = 1;

        // 0 -> 1
        // 1 -> 1
        // 1 2 ->       1 2 / 2 1               1 + 1 = 2
        // 1 2 3 ->     1 2 3 / 2 1 3 / 1 3 2   2 + 1 = 3
        // 1 2 3 4 ->   1 2 3 4 / 2 1 3 4 / 1 3 2 4 / 1 2 4 3 / 2 1 4 3     -> dp[i-1] + dp[i-2] = 5
        for (int i = 2; i <= max; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int result = 1;
        for (int i = 0; i <= M; i++) {
            result *= dp[divide[i]];
        }
        System.out.println(result);
    }
}