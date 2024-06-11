import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static boolean[][] visited;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 일자, ㄴ자, ㄹ자, 네모 총 4개의 경우를 고려
        int maxScore = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                maxScore = Math.max(maxScore, solve(1, i, j));
                visited[i][j] = false;
            }
        }

        // ㅜ 모양을 고려, (i, j) 위치가 중앙이라고 가정
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 현재 기준 주위 4곳 중 하나를 선택하지 않으면 ㅜ 모양이 됨
                for (int n = 0; n < 4; n++) {
                    int sum = 0;
                    boolean invalid = false;
                    for (int a = 0; a < 4; a++) {
                        int nextY = i + around[a][0];
                        int nextX = j + around[a][1];
                        if (a != n) {
                            if (isValid(nextY, nextX)) {
                                sum += map[nextY][nextX];
                            } else {
                                invalid = true;
                                break;
                            }
                        } else {
                            sum += map[i][j];
                        }
                    }
                    if (!invalid) {
                        maxScore = Math.max(maxScore, sum);
                    }
                }
            }
        }
        System.out.println(maxScore);
    }

    private static int[][] around = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int solve(int depth, int curY, int curX) {
        if (depth == 4) {
            return map[curY][curX];
        }
        int score = 0;
        for (int[] a : around) {
            int nextY = curY + a[0];
            int nextX = curX + a[1];

            if (isValid(nextY, nextX) && !visited[nextY][nextX]) {
                visited[nextY][nextX] = true;
                score = Math.max(score, solve(depth + 1, nextY, nextX));
                visited[nextY][nextX] = false;
            }
        }

        return map[curY][curX] + score;
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}