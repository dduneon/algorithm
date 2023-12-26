//package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] table = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      table[i] = Integer.parseInt(br.readLine());
    }
    int[] dp = new int[N + 1];
    dp[0] = 0;
    dp[1] = table[1];

    if (N == 1) {
      System.out.println(dp[1]);
      return;
    }

    dp[2] = table[1] + table[2];
    if (N == 2) {
      System.out.println(dp[2]);
      return;
    }

    for (int i = 3; i <= N; i++) {
      dp[i] = Math.max(dp[i - 2], dp[i - 3] + table[i - 1]) + table[i];
      dp[i] = Math.max(dp[i], dp[i - 1]);
    }

    System.out.println(Arrays.stream(dp).max().getAsInt());
  }
}