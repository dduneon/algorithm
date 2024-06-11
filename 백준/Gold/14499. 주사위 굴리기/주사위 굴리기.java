import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 지도의 세로 크기 N, 가로 크기 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        // 현재 주사위의 좌표 (Cr, Cc)
        int Cr = Integer.parseInt(st.nextToken());
        int Cc = Integer.parseInt(st.nextToken());
        // 명령의 개수 K
        int K = Integer.parseInt(st.nextToken());


        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        /**
         * 1 3 0 0 4
         * 2 2 2
         * 1 1 1 1
         */

        Dice dice = new Dice();
        // 현재 지도와 맞닿아 있는 면을 기록
        dice.cur = map[Cr][Cc];
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 동쪽 1, 서쪽 2, 북쪽 3, 남쪽 4
        for (int k = 0; k < K; k++) {
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1:
                    if (Cc + 1 < M) {
                        Cc++;
                        dice.turnRight();
                        if (map[Cr][Cc] == 0) {
                            map[Cr][Cc] = dice.cur;
                        } else {
                            dice.cur = map[Cr][Cc];
                            map[Cr][Cc] = 0;
                        }
                        sb.append(dice.other).append('\n');
                    }
                    break;
                case 2:
                    if (Cc - 1 >= 0) {
                        Cc--;
                        dice.turnLeft();
                        if (map[Cr][Cc] == 0) {
                            map[Cr][Cc] = dice.cur;
                        } else {
                            dice.cur = map[Cr][Cc];
                            map[Cr][Cc] = 0;
                        }
                        sb.append(dice.other).append('\n');
                    }
                    break;
                case 3:
                    if (Cr - 1 >= 0) {
                        Cr--;
                        dice.turnUp();
                        if (map[Cr][Cc] == 0) {
                            map[Cr][Cc] = dice.cur;
                        } else {
                            dice.cur = map[Cr][Cc];
                            map[Cr][Cc] = 0;
                        }
                        sb.append(dice.other).append('\n');
                    }
                    break;
                case 4:
                    if (Cr + 1 < N) {
                        Cr++;
                        dice.turnDown();
                        if (map[Cr][Cc] == 0) {
                            map[Cr][Cc] = dice.cur;
                        } else {
                            dice.cur = map[Cr][Cc];
                            map[Cr][Cc] = 0;
                        }
                        sb.append(dice.other).append('\n');
                    }
                    break;
                default:
            }
        }
        System.out.print(sb);
    }
}

class Dice {
    int cur;
    int up;
    int left;
    int right;
    int down;
    int other;

    public Dice() {
        cur = 0;
        up = 0;
        left = 0;
        right = 0;
        down = 0;
        other = 0;
    }

    public void turnLeft() {
        int tmp = cur;
        cur = left;
        left = other;
        other = right;
        right = tmp;
    }

    public void turnRight() {
        int tmp = cur;
        cur = right;
        right = other;
        other = left;
        left = tmp;
    }

    public void turnUp() {
        int tmp = cur;
        cur = up;
        up = other;
        other = down;
        down = tmp;
    }

    public void turnDown() {
        int tmp = cur;
        cur = down;
        down = other;
        other = up;
        up = tmp;
    }
}