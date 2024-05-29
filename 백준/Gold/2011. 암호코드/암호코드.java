import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        int[] dp = new int[code.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        if (code.isEmpty() || code.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < code.length(); i++) {
            // 1. 현재 숫자가 말이 되는 경우
            // 1-2. 이전 숫자와 합쳤을 때 말이 되는 경우
            // 2. 현재 숫자가 말이 안되는 경우(0)
            // 2-2. 이전 숫자와 합쳤을 때에는 말이 되는 경우

            char current = code.charAt(i);
            char before = code.charAt(i - 1);
            if (current >= '1' && current <= '9') {
                dp[i + 1] += dp[i];
                if (before == '1' || (before == '2' && current <= '6')) {
                    dp[i + 1] += dp[i - 1];
                }
            } else if (current == '0' && before >= '1' && before <= '2') {
                dp[i + 1] += dp[i - 1];
            } else {
                System.out.println(0);
                return;
            }
            dp[i + 1] %= 1_000_000;
        }

        System.out.println(dp[code.length()]);

        // 2, 5, 25, 1, 1, 11, 4, 14
        // 1 / 2 / 2 /
        // 2 -> B
        // 25 -> BE or Y 나눠짐
        // 251 -> BEA or YA
        // 2511 -> BEAA or BEK or YAA or YK 나눠짐
        // 25114 -> BEAAD or BEKD or YAAD or YKD or YAN or BEAN
        // 1 , 2, 1, 2, 2
        // 1, 2, 2, 2, 2, 2, 2, 2, 2, 2
    }
}