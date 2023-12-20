//package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[N + 2];

    // n=1일때는 가짓수가 1개임(세로로 1개)
    dp[1] = 1;
    dp[2] = 2;

    if (N < 3) {
      System.out.println(dp[N]);
      return;
    }

    for (int i = 3; i <= N; i++) {
      dp[i] = dp[i - 2] + dp[i - 1];
      dp[i] %= 10007;
    }

    System.out.println(dp[N]);
  }
}