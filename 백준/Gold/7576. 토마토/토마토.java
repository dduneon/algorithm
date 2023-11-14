//package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

  static boolean[][] visited;
  static Queue<Node> queue;
  static int[][] plusNumber = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][M];
    visited = new boolean[N][M];
    queue = new LinkedList<>();
    int numberOfZero = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int number = Integer.parseInt(st.nextToken());
        map[i][j] = number;
        if (number == 0) {
          numberOfZero++;
        } else if (number == 1) {
          queue.add(new Node(i, j, 0));
        }
      }
    }
    System.out.println(bfs(map, numberOfZero, N, M));


  }

  public static int bfs(int[][] map, int numberOfZero, int N, int M) {
    while (!queue.isEmpty()) {
      Node current = queue.poll();

      // 하루가 지나는 동안 각 토마토에게 전이되는 주변 토마토들
      for (int[] number : plusNumber) {
        int nextRow = current.row + number[0];
        int nextCol = current.col + number[1];
        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
          continue;
        }
        if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
          visited[nextRow][nextCol] = true;
          map[nextRow][nextCol] = 1;
          numberOfZero--;
          if (numberOfZero == 0) {
            return current.depth + 1;
          }
          queue.add(new Node(nextRow, nextCol, current.depth + 1));
        }
      }

    }
    return numberOfZero == 0 ? 0 : -1;
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
