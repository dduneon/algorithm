//package solving;

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= N; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> array = new ArrayList<>();
            while (st.hasMoreTokens())
                array.add(Integer.parseInt(st.nextToken()));
            System.out.print("#" + test_case + " ");
            System.out.printf("%.1f", solution(array));
            System.out.println();
        }
    }

    public static float solution(List<Integer> array) {
        float x = array.get(0);
        float y = array.get(1);
        float z = array.get(2);

        float caseOne = Math.abs(x - (2 * y - z));
        // z - y = y - x , x = 2y-z

        float caseTwo = Math.abs(y - ((x + z) / 2));
        // 2y = x+z , y = (x+z) / 2

        float caseThree = Math.abs(z - (2 * y - x));
        // y-x = z-y , z = 2y - x

        return Float.min(caseOne, Float.min(caseTwo, caseThree));
    }
}
