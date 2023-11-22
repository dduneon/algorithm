//package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int[][] map;
  static boolean[][] visited;
  static int numberOfRow;
  static int numberOfCol;
  static int[] moveToRow = {0, 0, 1, -1};
  static int[] moveToCol = {1, -1, 0, 0};

  public static void main(String[] args) throws Exception {
    // input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numberOfRow = Integer.parseInt(st.nextToken());
    numberOfCol = Integer.parseInt(st.nextToken());
    map = new int[numberOfRow][numberOfCol];

    int numberOfCheese = 0;
    for (int r = 0; r < numberOfRow; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < numberOfCol; c++) {
        int number = Integer.parseInt(st.nextToken());
        map[r][c] = number;
        if (number == 1) {
          numberOfCheese++;
        }
      }
    }
    // input end

    int time = 0;
    int currentMelt = 0;
    while (numberOfCheese > 0) {
      currentMelt = bfs();
      time += 1;
      numberOfCheese -= currentMelt;
    }

    System.out.println(time);
    System.out.println(currentMelt);
  }

  public static int bfs() {
    visited = new boolean[numberOfRow][numberOfCol];
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});
    int meltingCheese = 0;
    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      for (int i = 0; i < 4; i++) {
        int currentRow = current[0] + moveToRow[i];
        int currentCol = current[1] + moveToCol[i];

        if (currentRow < 0 || currentRow >= numberOfRow || currentCol < 0
            || currentCol >= numberOfCol || visited[currentRow][currentCol]) {
          continue;
        }

        if (map[currentRow][currentCol] == 0) {
          queue.add(new int[]{currentRow, currentCol});
        } else {
          map[currentRow][currentCol] = 0;
          meltingCheese++;
        }
        visited[currentRow][currentCol] = true;
      }
    }
    return meltingCheese;
  }
}