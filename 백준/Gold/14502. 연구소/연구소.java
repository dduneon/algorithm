import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int N;
    private static int M;
    private static List<int[]> virus;
    private static int wallCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[] {i, j});
                } else if (map[i][j] == 1) {
                    // 벽의 개수를 미리 세 놓음
                    wallCount++;
                }
            }
        }
        // 3개의 벽을 추가로 설치할 것임
        wallCount += 3;
        build(1);
        System.out.println(result);
    }

    private static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static int calculate(int[][] arr) {
        Queue<int[]> queue = new LinkedList<>(virus);

        int virusCount = virus.size();
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] a : around) {
                int nextY = current[0] + a[0];
                int nextX = current[1] + a[1];

                if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M && arr[nextY][nextX] == 0) {
                    arr[nextY][nextX] = 2;
                    virusCount++;
                    queue.add(new int[] {nextY, nextX});
                }
            }
        }
        return (N * M) - wallCount - virusCount;
    }

    private static int result = 0;

    // 벽 세우기 (DFS)
    private static void build(int depth) {
        if (depth == 4) {
            // 바이러스 안전 영역 크기 체크
            // BFS를 진행할 배열 복사
            int[][] copy = new int[N][M];
            for (int i = 0; i < N; i++) {
                copy[i] = Arrays.copyOf(map[i], M);
            }

            // result 최신화
            result = Math.max(result, calculate(copy));
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    build(depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }
}