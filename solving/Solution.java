package solving;

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> array = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                array.add(Integer.parseInt(st.nextToken()));
            }

            System.out.println("#" + test_case + " " + solution(N, array));
        }
    }

    public static long solution(int N, List<Integer> array) {
        long result = 0;
        int max_sell = array.get(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            int indexedItem = array.get(i);
            if (indexedItem >= max_sell) {
                max_sell = indexedItem;
            } else {
                result += max_sell - indexedItem;
            }
        }
        return result;
    }
}
