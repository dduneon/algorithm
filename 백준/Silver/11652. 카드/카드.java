//package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {
    List<Long> list = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      list.add(Long.parseLong(br.readLine()));
    }

    Collections.sort(list);
    long ansCount = 0;
    long ans = 0;

    int index = 0;
    while (index < list.size()) {
      long n = list.get(index);
      int tmpCount = 0;
      while (index < list.size() && n == list.get(index)) {
        tmpCount++;
        index++;
      }
      if (tmpCount > ansCount) {
        ansCount = tmpCount;
        ans = n;
      }
    }
    System.out.println(ans);
  }
}