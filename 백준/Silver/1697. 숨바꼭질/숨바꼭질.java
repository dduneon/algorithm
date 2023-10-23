import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = BFS(N, K);
        System.out.println(result);
    }

    public static int BFS(int N, int K) {
        int result = 0;
        Queue<Integer> items = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        items.add(N);
        levels.add(0);
        while (!items.isEmpty()) {
            int item = items.remove();
            int level = levels.remove();

            visited[N] = true;
            // end condition
            if (item == K) {
                result = level;
                break;
            }
            // queue adding
            if (item + 1 <= 100000 && !visited[item + 1]) {
                items.add(item + 1);
                levels.add(level + 1);
                visited[item + 1] = true;
            }
            if (item - 1 >= 0 && !visited[item - 1]) {
                items.add(item - 1);
                levels.add(level + 1);
                visited[item - 1] = true;
            }
            if (item * 2 <= 100000 && !visited[item * 2]) {
                items.add(item * 2);
                levels.add(level + 1);
                visited[item * 2] = true;
            }
        }
        return result;
    }
}
