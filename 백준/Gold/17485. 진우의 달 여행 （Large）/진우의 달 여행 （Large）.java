import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    private static final int Z = 3;
    private static final int INF = 1000000;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][3];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (n == 0) {
                    Arrays.fill(dp[n][m], map[n][m]);
                } else {
                    Arrays.fill(dp[n][m], INF);
                }
            }
        }

        for (int n = 1; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (m != 0) {
                    dp[n][m][0] = Math.min(dp[n - 1][m - 1][1], dp[n - 1][m - 1][2]) + map[n][m];
                }
                dp[n][m][1] = Math.min(dp[n - 1][m][0], dp[n - 1][m][2]) + map[n][m];
                if (m != M - 1) {
                    dp[n][m][2] = Math.min(dp[n - 1][m + 1][0], dp[n - 1][m + 1][1]) + map[n][m];
                }
            }
        }

        int result = INF;
        for (int z = 0; z < Z; z++) {
            for (int m = 0; m < M; m++) {
                result = Math.min(result, dp[N - 1][m][z]);
            }
        }

        System.out.println(result);
    }
}
