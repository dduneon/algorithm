import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] concat;
    private static int N;
    private static int M;
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로선의 개수 N
        N = Integer.parseInt(st.nextToken());
        // 가로선의 개수 M
        M = Integer.parseInt(st.nextToken());
        // 세로선마다 가로선을 놓을 수 있는 위치의 개수 H
        H = Integer.parseInt(st.nextToken());

        // 상하좌우 사이드 고려하여 +2만큼 더 크게 생성
        // 세로 줄의 양 끝 (0, N+1) 은 없는 줄이고 false 이기 때문에 갈 수 없음
        // 가로 줄의 마지막 (M+1) 은 도착지점을 의미함
        concat = new boolean[N + 2][H + 2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // b와 b+1 세로선이 a 위치에서 연결되어 있음을 표시
            concat[b][a] = true;
        }

        // 만약 초기 상태에서 조건에 만족한다면 리턴
        if (check()) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                // 연결되어 있지 않은 가로선이라면
                if (!concat[j][i]) {
                    // 연결로 바꾸고
                    concat[j][i] = true;
                    // DFS 시작, depth는 1
                    solve(1, i, j);
                    // 다시 연결 해제
                    concat[j][i] = false;
                }
            }
        }
        System.out.println(result == 4 ? -1 : result);
    }

    private static int result = 4;

    private static void solve(int depth, int Ry, int Rx) {
        // 총 3개까지만 추가할 수 있음
        if (depth == 4) {
            return;
        }

        // 만약 조건에 만족한다면
        if (check()) {
            result = Math.min(result, depth);
            return;
        }

        for (int i = Ry; i <= H; i++, Rx = 1) {
            for (int j = Rx; j < N; j++) {
                // 연결되어 있지 않은 가로선이라면
                if (!concat[j][i]) {
                    // 연결로 바꾸고
                    concat[j][i] = true;
                    // DFS 시작, 하나 더 설치했기에 올려줌
                    solve(depth + 1, i, j);
                    // 다시 연결 해제
                    concat[j][i] = false;
                }
            }
        }
    }

    private static boolean check() {
        // 마지막 세로줄은 확인할 필요가 없음
        for (int i = 1; i <= N; i++) {
            // 현재 위치한 세로축의 번호
            int height = 1;
            // 현재 위치한 가로축의 번호
            int width = i;
            while (height != H + 1) {
                if (concat[width - 1][height]) {
                    // 왼쪽 세로줄과 연결이 되어 있다면
                    height++;
                    width--;
                } else if (concat[width][height]) {
                    // 오른쪽 세로줄과 연결이 되어 있다면
                    height++;
                    width++;
                } else {
                    // 연결되어 있지 않다면 세로 줄 유지, 아래로 한칸
                    height++;
                }
            }
            // 만약 시작 세로축과, 끝 세로축이 같지 않다면 false 리턴
            if (i != width) {
                return false;
            }
        }
        return true;
    }
}