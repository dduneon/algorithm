import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 드래곤 커브의 개수
        int N = Integer.parseInt(br.readLine());

        map = new boolean[100 + 1][100 + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 주의 (i, j) 는 x좌표가 i, y좌표가 j 라는 뜻
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[y][x] = true;
            draw(x, y, d, g);
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    // (x, y) 기준
    // 0: 오른쪽, 1: 위, 2: 왼쪽, 3: 아래
    private static int[][] move = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    private static void draw(int startX, int startY, int direction, int generation) {
        // 진행할 다음 방향을 기록하는 큐
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[] {direction, 0});

        int currentX = startX + move[direction][0];
        int currentY = startY + move[direction][1];
        map[currentY][currentX] = true;

        int currentGeneration = 1;
        while (currentGeneration <= generation) {
            int start = stack.size() - 1;
            for (int idx = start; idx >= 0; idx--) {
                int[] current = stack.get(idx);

                int currentDirection = getNextDirection(current[0]);
                // 현재 위치 기준 진행
                currentX += move[currentDirection][0];
                currentY += move[currentDirection][1];

                map[currentY][currentX] = true;

                if (current[1] + 1 <= generation) {
                    stack.add(new int[] {currentDirection, currentGeneration + 1});
                }
            }
            currentGeneration++;
        }
    }

    private static int getNextDirection(int direction) {
        return (direction + 1 == 4) ? 0 : direction + 1;
    }
}