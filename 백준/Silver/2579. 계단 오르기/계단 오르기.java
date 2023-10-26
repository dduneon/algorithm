import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfStair = Integer.parseInt(br.readLine());
        int[] scores = new int[numberOfStair + 1];
        int[] dp = new int[numberOfStair + 1];
        for (int i = 1; i <= numberOfStair; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = scores[1];
        if (numberOfStair >= 2) {
            dp[2] = scores[1] + scores[2];
        }
        for (int i = 3; i <= numberOfStair; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + scores[i - 1]) + scores[i];
        }
        System.out.println(dp[numberOfStair]);
    }
}
