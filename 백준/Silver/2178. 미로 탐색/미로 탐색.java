import java.io.*;
import java.util.*;

class Main {

    static int[][] miro;
    static boolean[][] visited;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        miro = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] inputRow = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                miro[i][j] = Integer.parseInt(inputRow[j]);
            }
        }

        int result = BFS(0, 0);
        System.out.println(result);
    }

    static int[] arroundCurrent = {-1, 1};

    public static int BFS(int currentRow, int currentCol) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(currentRow, currentCol, 1));
        visited[currentRow][currentCol] = true;

        while (!queue.isEmpty()) {
            Node popItem = queue.poll();
            int row = popItem.row;
            int col = popItem.col;
            int depth = popItem.depth;
            // DEBUG
            // System.out.println("Row : " + row + " Col : " + col + " Depth : " + depth);

            // End condition
            if (row == N - 1 && col == M - 1)
                return depth;

            // Check Around Current Node
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 1) {
                    if (isValidPosition(row + arroundCurrent[i], col)) {
                        queue.add(new Node(row + arroundCurrent[i], col, depth + 1));
                        visited[row + arroundCurrent[i]][col] = true;
                    }
                } else {
                    if (isValidPosition(row, col + arroundCurrent[i - 2])) {
                        queue.add(new Node(row, col + arroundCurrent[i - 2], depth + 1));
                        visited[row][col + arroundCurrent[i - 2]] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isValidPosition(int row, int col) {
        return (row >= 0 && row < N) && (col >= 0 && col < M) && (!visited[row][col])
                && (miro[row][col] == 1);
    }

}


class Node {
    int row;
    int col;
    int depth;

    public Node(int row, int col, int depth) {
        this.row = row;
        this.col = col;
        this.depth = depth;
    }
}
