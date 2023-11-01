import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int j = 0;
            while (st.hasMoreTokens()) {
                table[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                table[i][j] += solve(i, j, table);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, table[N - 1][i]);
        }
        System.out.println(max);
    }

    public static int solve(int curI, int curJ, int[][] table) {
        int pastI = curI - 1;

        int pastJ1 = curJ - 1;
        int pastJ2 = curJ;

        int result1 = 0;
        int result2 = 0;

        if (pastJ1 >= 0 && pastJ1 <= pastI) {
            result1 = table[pastI][pastJ1];

        }
        if (pastJ2 >= 0 && pastJ2 <= pastI) {
            result2 = table[pastI][pastJ2];
        }
        return Math.max(result1, result2);
    }
}
