import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static List<int[]> cctv;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctv = new ArrayList<>();

        // 벽의 개수
        int wall = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv.add(new int[] {i, j, map[i][j]});
                } else if (map[i][j] == 6) {
                    wall++;
                }
            }
        }

        // 해당 CCTV를 사용했는지 판단하는 배열
        used = new boolean[cctv.size()];

        solve(map, 0, 0);
        System.out.println(N * M - wall - cctv.size() - max);
    }

    private static boolean[] used;
    private static int max = 0;

    private static void solve(int[][] arr, int depth, int count) {
        if (depth == cctv.size()) {
            max = Math.max(max, count);
            return;
        }

        int[] current = cctv.get(depth);
        int curY = current[0];
        int curX = current[1];
        int cctv = current[2];

        switch (cctv) {
            case 1:
                for (int t = 0; t < 4; t++) {
                    int[][] copy = copyArr(arr);
                    int fillCount = filling(copy, curY, curX, t);
                    solve(copy, depth + 1, count + fillCount);
                }
                break;
            case 2:
                for (int t = 0; t < 2; t++) {
                    int[][] copy = copyArr(arr);

                    int fillCount = filling(copy, curY, curX, t);
                    fillCount += filling(copy, curY, curX, t + 2);
                    solve(copy, depth + 1, count + fillCount);
                }
                break;
            case 3:
                for (int t = 0; t < 4; t++) {
                    // 0 1 / 1 2 / 2 3 / 3 0
                    int[][] copy = copyArr(arr);
                    int fillCount = filling(copy, curY, curX, t);
                    fillCount += filling(copy, curY, curX, t + 1 == 4 ? 0 : t + 1);
                    solve(copy, depth + 1, count + fillCount);
                }
                break;
            case 4:
                for (int t = 0; t < 4; t++) {
                    int fillCount = 0;
                    int[][] copy = copyArr(arr);
                    for (int c = 0; c < 4; c++) {
                        if (t != c) {
                            fillCount += filling(copy, curY, curX, c);
                        }
                    }
                    solve(copy, depth + 1, count + fillCount);
                }
                break;
            case 5:
                int[][] copy = copyArr(arr);
                int fillCount = filling(copy, curY, curX, 0);
                fillCount += filling(copy, curY, curX, 1);
                fillCount += filling(copy, curY, curX, 2);
                fillCount += filling(copy, curY, curX, 3);
                solve(copy, depth + 1, count + fillCount);
                break;
        }
    }

    private static int[][] copyArr(int[][] arr) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(arr[i], M);
        }
        return copy;
    }

    // direction -> 0:위, 1:오른쪽, 2:아래, 3:왼쪽
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int filling(int[][] arr, int curY, int curX, int direction) {
        int[] move = dir[direction];
        int nextY = curY + move[0];
        int nextX = curX + move[1];

        int fillCount = 0;
        while (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M && arr[nextY][nextX] != 6) {
            if (arr[nextY][nextX] == 0) {
                arr[nextY][nextX] = 7;
                fillCount += 1;
            }
            nextY += move[0];
            nextX += move[1];
        }

        return fillCount;
    }
}