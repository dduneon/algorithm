//package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] numbers = new int[N];
    int[] dp = new int[N];

    String[] input = br.readLine().split(" ");
    for (int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(input[i]);
    }

    /**
     * dp[0] = numbers[0]
     * dp[1] = numbers[1] or dp[0] + numbers[1] (큰값)
     * dp[2] = numbers[2] or dp[1] + numbers[2]
     * dp[3] = numbers[3] or dp[2] + numbers[3]
     */

    dp[0] = numbers[0];
    for (int i = 1; i < N; i++) {
      dp[i] = Math.max(numbers[i], dp[i - 1] + numbers[i]);
    }
    System.out.println(Arrays.stream(dp).max().getAsInt());
  }
}