//package solving;

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] costMap = new int[N+1][3];
        int[][] dp = new int[N+1][3];
        for(int currentIndex = 1; currentIndex<=N; currentIndex++) {
            String inputOneLine = br.readLine();
            StringTokenizer st = new StringTokenizer(inputOneLine);
            for(int rgb = 0; rgb<3; rgb ++) {
                costMap[currentIndex][rgb] = Integer.parseInt(st.nextToken());
            }
            dp[currentIndex][0] = Math.min(dp[currentIndex-1][1], dp[currentIndex-1][2]) + costMap[currentIndex][0];
            dp[currentIndex][1] = Math.min(dp[currentIndex-1][0], dp[currentIndex-1][2]) + costMap[currentIndex][1];
            dp[currentIndex][2] = Math.min(dp[currentIndex-1][0], dp[currentIndex-1][1]) + costMap[currentIndex][2];
        }

        System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
    }
}
