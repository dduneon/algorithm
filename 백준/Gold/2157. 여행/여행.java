import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 수 (1-N번 도시)
        int N = Integer.parseInt(st.nextToken());
        // 방문할 도시의 수 M개 (이하)
        int M = Integer.parseInt(st.nextToken());
        // 개설된 항공로의 개수
        int K = Integer.parseInt(st.nextToken());

        // 동 -> 서 방향으로만, 도시 번호가 증가하는 순서대로만 이동
        int[][] flight = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            flight[i][0] = Integer.parseInt(st.nextToken());
            flight[i][1] = Integer.parseInt(st.nextToken());
            flight[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(flight, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        // 1부터 N까지 M개의 도시를 지났음
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 2; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < K; i++) {
            int start = flight[i][0];
            int end = flight[i][1];
            int score = flight[i][2];

            if (start >= end) {
                continue;
            }
            for (int m = 2; m <= M; m++) {
                if (dp[start][m - 1] == -1) {
                    continue;
                }
                dp[end][m] = Math.max(dp[end][m], dp[start][m - 1] + score);
            }
        }

        int result = 0;
        for (int i = 1; i <= M; i++) {
            result = Math.max(result, dp[N][i]);
        }
//
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= M; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(result);
    }
}