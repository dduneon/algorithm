import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 오른쪽, 위, 왼쪽, 아래 순서
    // 처음 뱀의 방향은 오른쪽을 향함
    private static int[][] around = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static int direction = 0;
    private static Deque<int[]> snake;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        boolean[][] appleMap = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            appleMap[y][x] = true;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<Turn> turn = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            turn.add(new Turn(sec, dir));
        }

        boolean[][] snakeMap = new boolean[N + 1][N + 1];
        snake = new LinkedList<>();
        snakeMap[1][1] = true;
        snake.add(new int[] {1, 1});

        int sec = 0;
        while (!snake.isEmpty()) {
            // 1초가 지났음을 표시
            sec++;

            int[] head = snake.peek();

            int nextY = head[0] + around[direction][0];
            int nextX = head[1] + around[direction][1];

            // 다음 위치에 뱀의 몸통이 있거나, 범위를 벗어난 경우 종료
            if (nextY < 1 || nextY > N || nextX < 1 || nextX > N || snakeMap[nextY][nextX]) {
                break;
            }

            // 머리 추가는 동일
            snakeMap[nextY][nextX] = true;
            snake.addFirst(new int[] {nextY, nextX});

            if (appleMap[nextY][nextX]) {
                // 만약 다음 위치에 사과가 존재한다면
                // 현재 위치에 머리를 추가, 사과는 삭제

                // 사과 삭제
                appleMap[nextY][nextX] = false;
            } else {
                // 만약 다음 위치에 사과가 없다면
                // 꼬리를 지우고, 머리는 현재 위치에 추가

                // 꼬리 지우기
                int[] tail = snake.pollLast();
                snakeMap[tail[0]][tail[1]] = false;
            }

            // 방향 전환 확인
            if (!turn.isEmpty() && turn.peek().second == sec) {
                if (turn.poll().direction == 'L') {
                    turnLeft();
                } else {
                    turnRight();
                }
            }
        }

        System.out.println(sec);
    }

    private static void turnLeft() {
        direction++;
        if (direction > 3) {
            direction = 0;
        }
    }

    private static void turnRight() {
        direction--;
        if (direction < 0) {
            direction = 3;
        }
    }
}

class Turn {
    int second;
    char direction;

    public Turn(int second, char direction) {
        this.second = second;
        this.direction = direction;
    }
}