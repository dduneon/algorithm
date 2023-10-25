import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        int[] dp = new int[number + 1];

        for (int i = 2; i <= number; i++) {
            // default case
            dp[i] = dp[i - 1] + 1;
            // divide with 3
            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            // divide with 2
            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
        }
        System.out.println(dp[number]);
    }
}
