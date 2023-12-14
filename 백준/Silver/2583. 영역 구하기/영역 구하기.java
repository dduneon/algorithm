//package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Main {

  static boolean[][] visited;
  static int M;
  static int N;
  static int local = 0;
  static int[][] moveTo = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    boolean[][] map = new boolean[M][N];
    visited = new boolean[M][N];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int startX = Integer.parseInt(st.nextToken());
      int startY = Integer.parseInt(st.nextToken());
      int endX = Integer.parseInt(st.nextToken());
      int endY = Integer.parseInt(st.nextToken());

      fill(startX, startY, endX, endY, map);
    }

    List<Integer> result = new ArrayList<>();
    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        if (!map[y][x] && !visited[y][x]) {
          visited[y][x] = true;
          dfs(x, y, map);
          result.add(local);
          local = 0;
        }
      }
    }
    System.out.println(result.size());
    System.out.println(
        result.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
  }

  public static void fill(int startX, int startY, int endX, int endY, boolean[][] map) {
    for (int y = startY; y < endY; y++) {
      for (int x = startX; x < endX; x++) {
        map[y][x] = true;
      }
    }
  }

  public static void dfs(int curX, int curY, boolean[][] map) {
    local += 1;
    for (int[] move : moveTo) {
      int nextX = curX + move[0];
      int nextY = curY + move[1];

      if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
        continue;
      }
      if (!visited[nextY][nextX] && !map[nextY][nextX]) {
        visited[nextY][nextX] = true;
        dfs(nextX, nextY, map);
      }
    }
  }
}