package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int K = Integer.parseInt(input[1]);

    int[] numbers = new int[N];
    for (int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(br.readLine());
    }

    int[] dp = new int[K + 1];
    dp[0] = 1;

    for (int num : numbers) {
      for (int i = num; i <= K; i++) {
        dp[i] = dp[i] + dp[i - num];
      }

      for (int n : dp) {
        System.out.print(n + " ");
      }
      System.out.println();
    }
    System.out.println(dp[K]);
  }
}