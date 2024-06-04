import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX_VAL = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            sb.append(0).append("\n").append(1);
            System.out.println(sb);
            return;
        }
        int[] dp = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = MAX_VAL;
            if (i % 3 == 0) {
                dp[i] = dp[i / 3] + 1;
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }

        sb.append(dp[N]).append("\n");

        int current = N;
        while (true) {
            if (current == 1) {
                sb.append(1);
                break;
            }
            sb.append(current).append(" ");
            if (current % 3 == 0 && dp[current] == dp[current / 3] + 1) {
                current /= 3;
                continue;
            }
            if (current % 2 == 0 && dp[current] == dp[current / 2] + 1) {
                current /= 2;
                continue;
            }
            current = current - 1;
        }

        System.out.println(sb.toString());
    }
}