import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 땅 개수
        int N = Integer.parseInt(st.nextToken());
        // 오리 수
        int Q = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int want = Integer.parseInt(br.readLine());

            int meet = 0;
            int idx = want;
            while (idx >= 1) {
                if (visited[idx]) {
                    meet = idx;
                }
                idx /= 2;
            }
            if (meet == 0) {
                visited[want] = true;
            }
            sb.append(meet).append("\n");
        }
        System.out.print(sb);
    }
}