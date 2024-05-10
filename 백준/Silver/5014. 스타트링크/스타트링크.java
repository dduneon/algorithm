import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 총 F층이 있음
        int F = Integer.parseInt(st.nextToken());
        // 현재 S층에 있음
        int S = Integer.parseInt(st.nextToken());
        // G 층으로 도착해야 함
        int G = Integer.parseInt(st.nextToken());
        // 위로 U층을 올라감
        int U = Integer.parseInt(st.nextToken());
        // 아래로 D층을 내려감
        int D = Integer.parseInt(st.nextToken());

        if ((S < G && U <= 0) || (S > G && D == 0)) {
            System.out.println("use the stairs");
            return;
        }
        visited = new boolean[F + 1];

        int result = bfs(F, S, G, U, D);
        System.out.println(result == -1 ? "use the stairs" : result);
    }

    public static int bfs(int total, int current, int goal, int up, int down) {
        Move start = new Move(current, 0);
        Queue<Move> queue = new LinkedList<>();
        queue.add(start);
        visited[current] = true;

        while (!queue.isEmpty()) {
            Move cur = queue.poll();
            if (cur.stair == goal) {
                return cur.num;
            }

            int upStair = cur.stair + up;
            int downStair = cur.stair - down;
            if (upStair <= total && !visited[upStair]) {
                queue.add(new Move(upStair, cur.num + 1));
                visited[upStair] = true;
            }
            if (downStair > 0 && !visited[downStair]) {
                queue.add(new Move(downStair, cur.num + 1));
                visited[downStair] = true;
            }
        }

        return -1;
    }
}

class Move {
    int stair;
    int num;

    public Move(int stair, int num) {
        this.stair = stair;
        this.num = num;
    }
}
