import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 보드의 크기, 세로 N, 가로 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] red = new int[2];
        int[] blue = new int[2];
        board = new char[N][M];
        visited = new boolean[N][M][N][M];
        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            for (int m = 0; m < M; m++) {
                board[n][m] = line.charAt(m);
                if (board[n][m] == 'R') {
                    red[0] = n;
                    red[1] = m;
                } else if (board[n][m] == 'B') {
                    blue[0] = n;
                    blue[1] = m;
                }
            }
        }
        visited[red[0]][red[1]][blue[0]][blue[1]] = true;

        /**
         * 빨간 구슬을 구멍을 통해서 빼내는 것이 목표
         * 파란 구슬이 구멍에 들어가면 안됨
         */

        int result = solve(new Ball(red[0], red[1], blue[0], blue[1], 0));
        System.out.println(result <= 10 ? result : -1);
    }

    static boolean[][][][] visited;
    static int[][] around = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int solve(Ball pos) {
        Queue<Ball> queue = new LinkedList<>();
        queue.add(pos);

        while (!queue.isEmpty()) {
            Ball current = queue.poll();
            int Ry = current.Ry;
            int Rx = current.Rx;
            int By = current.By;
            int Bx = current.Bx;
            int count = current.count;
            for (int[] a : around) {
                int[] moveR = move(Ry, Rx, a[0], a[1]);
                int[] moveB = move(By, Bx, a[0], a[1]);
                int nextCount = count + 1;

                if (board[moveB[0]][moveB[1]] == 'O') {
                    continue;
                }
                if (board[moveR[0]][moveR[1]] == 'O') {
                    return nextCount;
                }
                // 기존 위치를 비교한다. 기존 위치가 R B 순으로 있었는데, 오른쪽으로 이동 중 겹쳤다면 R을 하나 빼주어야(서쪽) 함
                // 1 4 -> 4 4 -> 3 4 (0, 1) 기존에 더 작았던 거에서 빼줌
                // 1 4 -> 1 1 -> 1 2 (0, -1) 기존에 더 컷던 거에서 빼줌
                // 1 4 -> 4 4 -> 3 4 (1, 0) 기존에 더 작았던 거에서 빼줌
                // 이동 방향이 양수인 경우와 음수인 경우로 나눔
                if (moveR[0] == moveB[0] && moveR[1] == moveB[1]) {
                    if (a[0] > 0) {
                        if (Ry > By) {
                            moveB[0] -= a[0];
                        } else {
                            moveR[0] -= a[0];
                        }
                    }
                    if (a[0] < 0) {
                        if (Ry > By) {
                            moveR[0] -= a[0];
                        } else {
                            moveB[0] -= a[0];
                        }
                    }
                    if (a[1] > 0) {
                        if (Rx > Bx) {
                            moveB[1] -= a[1];
                        } else {
                            moveR[1] -= a[1];
                        }
                    }
                    if (a[1] < 0) {
                        if (Rx > Bx) {
                            moveR[1] -= a[1];
                        } else {
                            moveB[1] -= a[1];
                        }

                    }
                }
                if (!visited[moveR[0]][moveR[1]][moveB[0]][moveB[1]]) {
                    visited[moveR[0]][moveR[1]][moveB[0]][moveB[1]] = true;
                    queue.add(new Ball(moveR[0], moveR[1], moveB[0], moveB[1], nextCount));
                }
            }
        }
        return -1;
    }

    private static int[] move(int y, int x, int moveY, int moveX) {
        while (board[y + moveY][x + moveX] != '#') {
            y += moveY;
            x += moveX;
            if (board[y][x] == 'O') {
                break;
            }
        }
        return new int[] {y, x};
    }
}

class Ball {
    int Ry;
    int Rx;
    int By;
    int Bx;
    int count;

    public Ball(int ry, int rx, int by, int bx, int count) {
        Ry = ry;
        Rx = rx;
        By = by;
        Bx = bx;
        this.count = count;
    }
}
