import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] scores = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            scores[i][0] = Integer.parseInt(st.nextToken());
            scores[i][1] = Integer.parseInt(st.nextToken());
            scores[i][2] = Integer.parseInt(st.nextToken());
        }

        // [0] -> 최대, [1] -> 최소
        int[] minDp = new int[3];
        int[] maxDp = new int[3];
        for (int i = 0; i < 3; i++) {
            minDp[i] = scores[0][i];
            maxDp[i] = scores[0][i];
        }

        for (int i = 1; i < N; i++) {
            int maxLeft = Math.max(maxDp[0], maxDp[1]);
            int maxRight = Math.max(maxDp[1], maxDp[2]);

            maxDp[0] = maxLeft + scores[i][0];
            maxDp[1] = Math.max(maxLeft, maxRight) + scores[i][1];
            maxDp[2] = maxRight + scores[i][2];

            int minLeft = Math.min(minDp[0], minDp[1]);
            int minRight = Math.min(minDp[1], minDp[2]);

            minDp[0] = minLeft + scores[i][0];
            minDp[1] = Math.min(minLeft, minRight) + scores[i][1];
            minDp[2] = minRight + scores[i][2];
        }

        int max = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int min = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(min);
        System.out.println(sb);
    }
}