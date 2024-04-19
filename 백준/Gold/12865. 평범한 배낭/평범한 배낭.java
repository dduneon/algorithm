import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] things = new int[N+1][2];
        // things[0]: 해당 물건의 무게
        // things[1]: 해당 물건의 가치

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            things[i][0] = Integer.parseInt(st.nextToken());
            things[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K+1];
        // i: 선택한 물품의 번호 (1~N)
        for(int i=1; i<=N; i++) {
            // j: 가방에 넣을 수 있는 최대 무게
            for(int j=1; j<=K; j++) {
                // 현재 선택한 물품보다 가방의 무게가 크면, 현재 물품을 고려하여 최대 가치를 선정
                // 아니면, 이전 물품에서 같은 가방의 무게일 때의 값을 대입
                dp[i][j] = Math.max(dp[i-1][j],
                        j >= things[i][0] ?
                        things[i][1] + dp[i-1][j-(things[i][0])] :
                        0);
            }
        }
        System.out.println(dp[N][K]);
    }
}