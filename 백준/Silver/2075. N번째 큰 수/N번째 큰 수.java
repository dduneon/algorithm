import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        // 수들 저장
        int[][] map = new int[N][N];
        // 현재 위치 저장, 처음 위치는 맨 끝(가장 큰 수)
        int[] cur = new int[N];
        Arrays.fill(cur, N - 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N번째 큰 수를 찾기 위하여 N번 실행
        for (int i = 0; i < N; i++) {
            // 각 세로줄에서 가장 큰 수를 찾는다
            int maxLine = 0;
            int maxNum = Integer.MIN_VALUE;
            for (int j = 0; j < N; j++) {
                if (maxNum < map[cur[j]][j]) {
                    maxNum = map[cur[j]][j];
                    maxLine = j;
                }
            }
            if (i == N - 1) {
                System.out.println(maxNum);
            }
            cur[maxLine]--;
        }
    }
}
