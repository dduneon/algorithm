import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 집의 수 m, 길의 수 n
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0) {
                break;
            }

            PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[2] - o2[2]));
            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                // x번 집과 y번 집 사이에 양방향 도로가 있으며, 그 거리가 z 미터라는 뜻
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                total += z;
                queue.add(new int[] {x, y, z});
            }

            // 자신이 포함된 루트 노드 기록
            root = new int[m];
            for (int i = 0; i < m; i++) {
                root[i] = i;
            }

            int selected = 0;
            int used = 0;
            while (!queue.isEmpty()) {
                if (selected == m - 1) {
                    sb.append(total - used).append("\n");
                    break;
                }

                int[] current = queue.poll();
                if (union(current[0], current[1])) {
                    used += current[2];
                    selected++;
                }
            }
        }
        System.out.print(sb);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }

        root[y] = x;
        return true;
    }

    private static int find(int x) {
        if (root[x] == x) {
            return x;
        } else {
            return find(root[x]);
        }
    }
}