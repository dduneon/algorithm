import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도의 크기 M * N
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        // 조사 대상 영역의 개수 K
        int K = Integer.parseInt(br.readLine());

        // 3차원 (0: 정글의 수의 누적합, 1: 바다의 수의 누적합, 2: 얼음의 수의 누적합)
        int[][][] map = new int[M + 1][N + 1][3];
        for (int m = 1; m <= M; m++) {
            String line = br.readLine();
            for (int n = 1; n <= N; n++) {
                for (int i = 0; i < 3; i++) {
                    map[m][n][i] = map[m - 1][n][i] + map[m][n - 1][i] - map[m - 1][n - 1][i];
                }
                char current = line.charAt(n - 1);
                if (current == 'J') {
                    map[m][n][0]++;
                } else if (current == 'O') {
                    map[m][n][1]++;
                } else {
                    map[m][n][2]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            // 왼쪽 위 모서리 좌표
            int leftY = Integer.parseInt(st.nextToken());
            int leftX = Integer.parseInt(st.nextToken());
            // 오른쪽 아래 모서리 좌표
            int rightY = Integer.parseInt(st.nextToken());
            int rightX = Integer.parseInt(st.nextToken());

            int jungle = map[rightY][rightX][0] - map[leftY - 1][rightX][0] - map[rightY][leftX - 1][0] +
                    map[leftY - 1][leftX - 1][0];
            int ocean = map[rightY][rightX][1] - map[leftY - 1][rightX][1] - map[rightY][leftX - 1][1] +
                    map[leftY - 1][leftX - 1][1];
            int ice = map[rightY][rightX][2] - map[leftY - 1][rightX][2] - map[rightY][leftX - 1][2] +
                    map[leftY - 1][leftX - 1][2];

            sb.append(jungle).append(" ").append(ocean).append(" ").append(ice).append("\n");
        }
        System.out.print(sb);
    }
}