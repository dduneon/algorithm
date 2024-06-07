import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[1] = 1;
        if (N == 1) {
            System.out.println(1);
            return;
        }

        for (int i = 2; i <= N; i++) {
            dp[i] = (i % 2 == 0) ? (dp[i - 1] + dp[i / 2]) % 1_000_000_000 : dp[i - 1];
        }
        System.out.println(dp[N]);

        /**
         * n=1
         * 1
         *
         * n=2
         * 1 1
         * 2
         *
         * n=3
         * 1 1 1
         * 2 1
         *
         * n=4
         * 1 1 1 1
         * 2 1 1
         * 2 2
         * 4
         *
         * n=5
         * 1 1 1 1 1
         * 2 1 1 1
         * 2 2 1
         * 4 1
         *
         * n=6
         * 1 1 1 1 1 1
         * 2 1 1 1 1
         * 2 2 1 1
         * 4 1 1
         *
         * 2 2 2
         * 4 2
         *
         * n=7
         * 1 1 1 1 1 1 1
         * 2 1 1 1 1 1
         * 2 2 1 1 1
         * 4 1 1 1
         *
         * 2 2 2 1
         * 4 2 1
         *
         * n=8
         * 1 1 1 1 1 1 1 1
         * 2 1 1 1 1 1 1
         * 2 2 1 1 1 1
         * 4 1 1 1 1
         * 2 2 2 1 1
         * 4 2 1 1
         *
         * 2 2 2 2
         * 4 2 2
         * 4 4
         * 8
         *
         *
         */
    }
}