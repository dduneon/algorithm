import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] scores = {{0, 1}, {0, 2}, {0, 4}, {0, 8}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N극은 0, S극은 1 / 총 8개의 정수
        String[] top = new String[4];
        for (int i = 0; i < 4; i++) {
            // 12시 방향부터 시계방향 순서대로 주어짐
            top[i] = br.readLine();
        }
        // 현재 톱니바퀴의 12시 방향에 위치한 인덱스
        int[] idx = new int[4];

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            // 1은 시계방향, -1은 반시계 방향
            int rotate = Integer.parseInt(st.nextToken());

            // 톱니바퀴 왼쪽편에 있는 톱니바퀴들을 다음에 돌릴 방향을 결정
            int nextLeftRotate = rotate == 1 ? -1 : 1;
            int leftCount = 0;
            // 현재 톱니바퀴 기준 왼쪽편 톱니바퀴들을 돌려준다
            for (int current = num; current > 0; current--) {
                int curLeftIdx = getLeftIdx(idx[current]);
                int leftRightIdx = getRightIdx(idx[current - 1]);

                // 만약 각각 마주보는 극이 같다면
                if (top[current].charAt(curLeftIdx) == top[current - 1].charAt(leftRightIdx)) {
                    // 더이상 진행하지 않음
                    break;
                } else {
                    leftCount++;
                }
            }
            for (int current = num - 1; current >= num - leftCount; current--) {
                idx[current] =
                        nextLeftRotate == 1 ? rotateRight(idx[current]) : rotateLeft(idx[current]);
                // 돌리는 방향을 반대로 바꾸어줌
                nextLeftRotate = nextLeftRotate == 1 ? -1 : 1;
            }


            int rightCount = 0;
            int nextRightRotate = rotate == 1 ? -1 : 1;
            // 현재 톱니바퀴 기준 오른쪽편 톱니바퀴들을 돌려준다
            for (int current = num; current < 3; current++) {
                int curRightIdx = getRightIdx(idx[current]);
                int rightLeftIdx = getLeftIdx(idx[current + 1]);

                // 만약 마주보는 극이 같다면
                if (top[current].charAt(curRightIdx) == top[current + 1].charAt(rightLeftIdx)) {
                    // 더이상 진행하지 않음
                    break;
                } else {
                    rightCount++;

                }
            }
            for (int current = num + 1; current <= num + rightCount; current++) {
                idx[current] =
                        nextRightRotate == 1 ? rotateRight(idx[current]) : rotateLeft(idx[current]);
                // 돌리는 방향 반대로
                nextRightRotate = nextRightRotate == 1 ? -1 : 1;
            }

            idx[num] = rotate == 1 ? rotateRight(idx[num]) : rotateLeft(idx[num]);

        }

        int result = 0;
        for (int check = 0; check < 4; check++) {
            int currentTop = top[check].charAt(idx[check]) == '0' ? 0 : 1;
            result += scores[check][currentTop];
        }
        System.out.println(result);

    }

    // 오른쪽(시계방향) 으로 회전시켰을 때의 인덱스 위치 리턴
    private static int rotateRight(int idx) {
        return idx - 1 == -1 ? 7 : idx - 1;
    }

    // 왼쪽(시계 반대방향) 으로 회전시켰을 때의 인덱스 위치 리턴
    private static int rotateLeft(int idx) {
        return idx + 1 == 8 ? 0 : idx + 1;
    }

    private static int getLeftIdx(int idx) {
        // 0 -> 6
        // 1 -> 7
        return idx - 2 < 0 ? idx + 6 : idx - 2;
    }

    private static int getRightIdx(int idx) {
        // 6 -> 0
        // 7 -> 1
        return idx + 2 > 7 ? idx - 6 : idx + 2;
    }
}