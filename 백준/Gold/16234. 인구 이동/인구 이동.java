import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int N;
    private static int L;
    private static int R;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            boolean change = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (!solve(i, j)) {
                            change = true;
                        }
                    }
                }
            }
            if (!change) {
                break;
            }
            day++;
        }
        System.out.println(day);
    }

    private static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static boolean solve(int startY, int startX) {
        List<int[]> union = new ArrayList<>();
        union.add(new int[] {startY, startX});

        int sumScore = map[startY][startX];

        // 방문했음을 표시
        visited[startY][startX] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startY, startX});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curScore = map[current[0]][current[1]];

            for (int[] a : around) {
                int nextY = current[0] + a[0];
                int nextX = current[1] + a[1];

                if (isValid(nextY, nextX) && !visited[nextY][nextX] && isUnion(nextY, nextX, curScore)) {
                    visited[nextY][nextX] = true;
                    queue.add(new int[] {nextY, nextX});
                    union.add(new int[] {nextY, nextX});
                    sumScore += map[nextY][nextX];
                }
            }
        }

        if (union.size() == 1) {
            return true;
        }

        int result = sumScore / union.size();
        for (int[] u : union) {
            map[u[0]][u[1]] = result;
        }
        return false;
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    private static boolean isUnion(int nextY, int nextX, int curScore) {
        int minus = Math.abs(map[nextY][nextX] - curScore);
        return minus >= L && minus <= R;
    }
}