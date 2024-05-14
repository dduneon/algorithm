import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 청소기의 처음 위치 (R, C)
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        // 바라보는 방향 D (0: 북, 1: 동, 2: 남, 3: 서)
        int D = Integer.parseInt(st.nextToken());

        // 0: 청소 X, 1: 벽
        int[][] map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        doClean(R, C, D, map);
        System.out.println(count);
    }

    static int count = 0;
    static boolean[][] visited;

    static int[][] around = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // 바라보는 방향 D (0: 북, 1: 동, 2: 남, 3: 서)
    public static void doClean(int R, int C, int D, int[][] map) {
        if (!visited[R][C] && map[R][C] == 0) {
            visited[R][C] = true;
            count++;
        }

        for (int i = 1; i <= 4; i++) {
            int direction = D - i;
            if (direction < 0) {
                direction += 4;
            }
            int nextR = R + around[direction][0];
            int nextC = C + around[direction][1];

            if (isValid(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 0) {
                doClean(nextR, nextC, direction, map);
                return;
            }
        }

        // 후진 하는 경우
        int backDirection = D - 2;
        if (backDirection < 0) {
            backDirection += 4;
        }

        int nextR = R + around[backDirection][0];
        int nextC = C + around[backDirection][1];

        if (isValid(nextR, nextC) && map[nextR][nextC] == 0) {
            doClean(nextR, nextC, D, map);
        }
    }

    public static boolean isValid(int R, int C) {
        return R >= 0 && R < N && C >= 0 && C < M;
    }
}