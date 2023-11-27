//package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static int[] select;
  static boolean[] selected;
  static Set<Integer> set;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test_cases = Integer.parseInt(br.readLine());
    for (int i = 0; i < test_cases; i++) {
      int n = Integer.parseInt(br.readLine());
      select = new int[n];
      String oneLine = br.readLine();
      StringTokenizer st = new StringTokenizer(oneLine);
      for (int s = 0; s < n; s++) {
        select[s] = Integer.parseInt(st.nextToken());
      }
      selected = new boolean[n];
      set = new HashSet<>();
      int result = n;
      for (int s = 0; s < n; s++) {
        if (!selected[s]) {
          set.clear();
          result -= dfs(s, s);
        }
      }
      System.out.println(result);
    }
  }

  public static int dfs(int currentPos, int startPos) {
    selected[currentPos] = true;
    set.add(currentPos);
    int nextPos = select[currentPos] - 1;

    if (!selected[nextPos]) {
      return dfs(nextPos, startPos);
    } else {
      if (set.contains(nextPos)) {
        int count = 1;
        int start = nextPos;
        int current = select[start] - 1;
        while (start != current) {
          current = select[current] - 1;
          count++;
        }
        return count;
      }
    }
    return 0;
  }
}