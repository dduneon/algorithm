import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] consult = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            consult[i][0] = Integer.parseInt(st.nextToken());
            consult[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int idx = i + consult[i][0] - 1;
            int value = dp[i - 1] + consult[i][1];
            for (int d = idx; d <= N; d++) {
                dp[d] = Math.max(dp[d], value);
            }
        }
        System.out.println(dp[N]);
    }
}