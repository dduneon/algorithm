//package solving;

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        solution(N);
    }

    public static void solution(int N) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            String numToString = String.valueOf(i);
            boolean isChanged = false;
            for (int index = 0; index < numToString.length(); index++) {
                char indexedChar = numToString.charAt(index);
                if (indexedChar == '3' || indexedChar == '6' || indexedChar == '9') {
                    result.append('-');
                    isChanged = true;
                }
            }
            if (!isChanged)
                result.append(i);
            result.append(' ');
        }
        System.out.println(result.toString());
    }
}
