import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static boolean[] visited;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        result = new int[M];

        sb = new StringBuilder();
        // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
        dfs(1, 0);
        System.out.print(sb);
    }

    private static StringBuilder sb;

    private static void dfs(int current, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            result[depth] = i;
            dfs(i, depth + 1);
        }
    }
}
