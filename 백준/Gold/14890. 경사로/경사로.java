import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int L;
    private static int[][] line;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        line = new int[2 * N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                line[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                line[N + i][j] = map[j][i];
            }
        }


        int result = 0;
        for (int i = 0; i < N * 2; i++) {
            check = new boolean[N];
            // 현재 위치 저장
            int current = 0;

            // 조건 설정하기
            while (true) {
                if (current == N - 1) {
                    result++;
                    break;
                }

                int currentH = line[i][current];
                int next = current + 1;
                int nextH = line[i][next];
                // 같은 높이의 칸인 경우
                if (currentH == nextH) {
                    // 다음 칸으로 이동
                    current = next;
                } else {
                    if (currentH - nextH == 1) {
                        // 가다가 낮은 칸을 만나는 경우
                        if (checkDown(current, i)) {
                            current += L;
                        } else {
                            break;
                        }
                    } else if (currentH - nextH == -1) {
                        // 가다가 높은 칸을 만나는 경우
                        if (checkUp(current, i)) {
                            current++;
                        } else {
                            break;
                        }
                    } else {
                        // 칸 수의 차이가 1 이상이기에 실패
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static boolean checkUp(int current, int h) {
        if (current - L + 1 < 0) {
            return false;
        }
        int height = line[h][current];
        for (int i = current; i > current - L; i--) {
            if (check[i] || line[h][i] != height) {
                return false;
            }
            check[i] = true;
        }
        return true;
    }

    private static boolean checkDown(int current, int h) {
        if (current + L >= N) {
            return false;
        }
        int height = line[h][current + 1];
        for (int i = current + 1; i <= current + L; i++) {
            if (check[i] || line[h][i] != height) {
                return false;
            }
            check[i] = true;
        }
        return true;
    }
}