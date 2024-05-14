import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < test_cases; t++) {
            int N = Integer.parseInt(br.readLine());
            result = false;

            int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] stores = new int[N][2];
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                stores[i][0] = Integer.parseInt(st.nextToken());
                stores[i][1] = Integer.parseInt(st.nextToken());
            }
            int[] end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dfs(start, end, stores);
            if (result) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    public static void dfs(int[] current, int[] end, int[][] stores) {
        int remain = getRemainDistance(current, end);
        if (remain <= 1000) {
            result = true;
            return;
        }

        for (int i = 0; i < stores.length; i++) {
            if (!visited[i] && isValid(current, stores[i]) && getRemainDistance(current, stores[i]) < remain) {
                visited[i] = true;
                dfs(stores[i], end, stores);
            }
        }
    }

    public static int getRemainDistance(int[] st1, int[] st2) {
        return Math.abs(st2[0] - st1[0]) + Math.abs(st2[1] - st1[1]);
    }

    public static boolean isValid(int[] st1, int[] st2) {
        return getRemainDistance(st1, st2) <= 1000;
    }
}