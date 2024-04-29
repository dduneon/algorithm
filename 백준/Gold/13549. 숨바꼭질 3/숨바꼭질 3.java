import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 200001; i++) {
            weight[i] = 200000;
        }
        weight[N] = 0;

        bfs(N, K);
    }

    static int[] weight = new int[200001];

    public static void bfs(int N, int K) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(N);

        while (!deque.isEmpty()) {
            int current = deque.pollFirst();

            if (current == K) {
                System.out.println(weight[current]);
                return;
            }

            int jump = current * 2;
            if (isValid(jump) && weight[jump] > weight[current]) {
                deque.addFirst(jump);
                weight[jump] = weight[current];
            }

            int walkBack = current - 1;
            if (isValid(walkBack) && weight[walkBack] > weight[current] + 1) {
                deque.addLast(walkBack);
                weight[walkBack] = weight[current] + 1;
            }

            int walkFront = current + 1;
            if (isValid(walkFront) && weight[walkFront] > weight[current] + 1) {
                deque.addLast(walkFront);
                weight[walkFront] = weight[current] + 1;
            }
        }
    }

    public static boolean isValid(int current) {
        return current >= 0 && current <= 200000;
    }
}
