import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * 1번부터 N번까지의 악보
     * 1 <= x, y <= N  만족하는 x, y 를 골라 번호 순서대로 연주
     * 지금 연주하는 악보가 다음 연주할 악보보다 어려우면 실수
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 악보의 개수 N
        int N = Integer.parseInt(br.readLine());

        // 1번부터 i번까지 곡을 연주했을 때, 실수하는 곡의 개수
        int[] mistake = new int[N + 1];
        int[] difficult = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            difficult[i] = Integer.parseInt(st.nextToken());
            // 이전에서 다음 곡의 난이도보다 높다면 실수 하나 추가
            if (i > 1) {
                mistake[i - 1] = mistake[i - 2] + (difficult[i - 1] > difficult[i] ? 1 : 0);
            }
        }
        mistake[N] = mistake[N - 1];

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // x번 악보부터 y번 악보까지 연주한다면 나오는 실수의 개수는
            // 1번부터 y번 악보까지 연주했을 때 나오는 실수 개수 - 1번부터 x-1 번 악보까지 연주했을때 나오는 실수 이다.
            if (x == y) {
                sb.append(0).append("\n");
                continue;
            }
            int mis = mistake[y] - mistake[x - 1];
            if (mistake[y] != mistake[y - 1]) {
                mis -= 1;
            }
            sb.append(mis).append("\n");
        }
        System.out.print(sb);
    }
}