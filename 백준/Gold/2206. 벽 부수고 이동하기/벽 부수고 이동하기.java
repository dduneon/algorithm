//package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

  static boolean[][][] visited;
  static int[][] plusNumber = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][M];
    visited = new boolean[N][M][2];
    for (int row = 0; row < N; row++) {
      String[] oneLine = br.readLine().split("");
      for (int col = 0; col < M; col++) {
        map[row][col] = Integer.parseInt(oneLine[col]);
      }
    }
    System.out.println(bfs(map, N, M));
  }

  public static int bfs(int[][] map, int N, int M) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(0, 0, 1, false));

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.row == N - 1 && current.col == M - 1) {
        return current.depth;
      }
      for (int[] ints : plusNumber) {
        int nextRow = current.row + ints[0];
        int nextCol = current.col + ints[1];
        // Invariant Set
        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
          continue;
        }

        // 먼저, 두 경우로 나눈다, 지금껏 벽을 깬적이 있는 경우와 없는 경우
        if (current.isBroke) {
          // 있다면? 더이상 벽을 부술 수 없으므로, 벽이 없는 경우와 방문하지 않은 경우만 따라가면 된다
          if (map[nextRow][nextCol] == 0 && !visited[nextRow][nextCol][1]) {
            visited[nextRow][nextCol][1] = true;
            queue.add(new Node(nextRow, nextCol, current.depth + 1, true));
          }
        } else {
          // 없다면? 하나의 벽을 부술 수 있으므로, 벽인 경우와 벽이 아닌 경우로 나누어 준다
          // 일단, 방문하지 않은 곳만 방문할 수 있으므로 먼저 조건을 처리해 준다
          if (!visited[nextRow][nextCol][0]) {
            // 벽이 아닌 경우라면? 방문 처리를 하고, 큐에 집어 넣는다
            if (map[nextRow][nextCol] == 0) {
              queue.add(new Node(nextRow, nextCol, current.depth + 1, false));
              visited[nextRow][nextCol][0] = true;
            } else {
              // 벽인 경우라면, 벽을 하나 부수고 갈 수 있으므로 벽을 부수고 부쉈다고 표시한 후, 부순 벽의 visited배열을 방문 처리 해준다
              queue.add(new Node(nextRow, nextCol, current.depth + 1, true));
              visited[nextRow][nextCol][1] = true;
            }
          }
        }
      }
    }
    return -1;
  }
}

class Node {

  int row;
  int col;
  int depth;
  boolean isBroke;

  Node(int row, int col, int depth, boolean isBroke) {
    this.row = row;
    this.col = col;
    this.depth = depth;
    this.isBroke = isBroke;
  }
}
