import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        dp[0] = 1;
        dp[1] = 3;
        for (int i = 2; i <= N; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2];
            dp[i] %= 9901;
        }
        System.out.println(dp[N]);

        // 1 0 / 0 1 / 0 0

        // 1 0 / 1 0
        // 0 1 / 0 0

        // 0 1 / 0 1
        // 1 0 / 0 0

        // 0 0 / 0 0 / 0 0
        // 1 0 / 0 0 / 0 1

        // 영향을 받는 것은 바로 직전밖에 없다고 생각하면 됨

        // 직전이 1 0 이나 0 1 인 경우 2배
        // 0 0 인 경우 3배 증가

        // 1 -> 1 0 or 0 1    /     2 -> 0 0
        // 1 1 2  (3)

        // 1   1   2
        // 12  12  112

        // 1        1         2
        // 1   2    1    2    1   1   2
        // 12  112  12   112  12  12  112

        // 1의 개수는 1은 1개씩, 2는 2개씩 증가함
        // 2의 개수는 전의 개수만큼 증가함

        // 112 -> 222 + 1111 ->

        // 2의개수 -> recent * 2
        // 1의 개수 ->

        // 1 : 2 4 10
        // 2 : 1 3 7 17
        // 2의 개수 = dp[i-1]

        // 1의 개수 = 전의 1의 개수에서 그대로 + 전의 2의 개수에서 2배
        // 전의 1의 개수 = 전의 전체 - 전의 2의 개수 = dp[i-1] - dp[i-2]
        // 전의 2의 개수에서 2배 = dp[i-2] * 2

        // 결론 = dp[i-1] + dp[i-1] + dp[i-2] = 2 * dp[i-1] + dp[i-2]
    }
}