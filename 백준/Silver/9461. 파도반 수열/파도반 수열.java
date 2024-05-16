import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] cases = new int[T];
        int maxCase = 0;
        for (int t = 0; t < T; t++) {
            cases[t] = Integer.parseInt(br.readLine());
            maxCase = Math.max(maxCase, cases[t]);
        }

        long[] dp = new long[maxCase + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= maxCase; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

        for (int c : cases) {
            sb.append(dp[c]).append("\n");
        }

        System.out.print(sb);
    }
}