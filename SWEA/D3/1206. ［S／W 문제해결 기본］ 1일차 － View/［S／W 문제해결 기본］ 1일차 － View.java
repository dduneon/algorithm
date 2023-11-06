//package solving;

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> map = new ArrayList<>();
            while (st.hasMoreTokens())
                map.add(Integer.parseInt(st.nextToken()));
            System.out.println("#" + test_case + " " + solution(map, N));
        }
    }

    public static int solution(List<Integer> map, int N) {
        int result = 0;
        for (int i = 2; i < N - 2; i++) {
            int currentHeight = map.get(i);
            int leftHeight = map.get(i - 1);
            int leftTwoHeight = map.get(i - 2);
            int rightHeight = map.get(i + 1);
            int rightTwoHeight = map.get(i + 2);


            int minHeightLeft = Math.min(currentHeight - leftHeight, currentHeight - leftTwoHeight);
            int minHeightRight =
                    Math.min(currentHeight - rightHeight, currentHeight - rightTwoHeight);
            result += (Math.min(minHeightLeft, minHeightRight) > 0)
                    ? Math.min(minHeightLeft, minHeightRight)
                    : 0;
        }
        return result;
    }
}
