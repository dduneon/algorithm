import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * N=3, M=21
     * P0 = 6, P1 = 7, P2 = 8 이면
     * 각각 하나씩 사서 210 (21원으로) 최대
     * <p>
     * dp[6] = 1 0 0 ..
     * dp[7] = 0 1 0 ..
     * dp[8] = 0 0 1 ..
     * dp[11] = 0 0 1 ..
     * dp[12] = 0 0 1 or 2 0 0
     * dp[13] = 2 or 1 or 0 or 10
     * dp[14] = 11 20 ....
     */

    private static int N;
    private static int[] P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 방 번호 N 개 (0~N-1)
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        // P[i] = 각 숫자의 가격
        P = new int[N];
        // 가장 최소 가격, 그 전까지는 0원
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 구매하기 위해 준비한 금액 M원
        int M = Integer.parseInt(br.readLine());

        int[][] dp = new int[M + 1][N];
        // i원을 가질 때 만들 수 있는 최대 방 번호의 배열
        for (int i = 1; i <= M; i++) {
            // 0~N-1 까지의 방 번호, 각 방을 살 수 있는지 검사
            for (int j = 0; j < N; j++) {
                // 살수 있다면? (현재 가진 금액으로)
                if (i >= P[j]) {
                    // 전체 금액 - 현재 고른 방 번호 로 살수 있는 최대 경우를 복사
                    int[] make = Arrays.copyOf(dp[i - P[j]], N);
                    make[j]++;
                    dp[i] = compare(dp[i], make);
                }
            }
        }

        System.out.println(arrToStr(dp[M]));
    }

    private static int[] compare(int[] a, int[] b) {
        StringBuilder sbA = new StringBuilder();
        StringBuilder sbB = new StringBuilder();

        for (int i = N - 1; i >= 0; i--) {
            if (a[i] > 0) {
                for (int j = 0; j < a[i]; j++) {
                    sbA.append(i);
                }
            }
            if (b[i] > 0) {
                for (int j = 0; j < b[i]; j++) {
                    sbB.append(i);
                }
            }
        }
        String strA = sbA.toString();
        String strB = sbB.toString();


        if (strA.isEmpty()) {
            return b;
        }
        strA = strA.charAt(0) == '0' ? "0" : strA;
        strB = strB.charAt(0) == '0' ? "0" : strB;

        if (strA.length() == strB.length()) {
            if (strA.compareTo(strB) >= 0) {
                return a;
            } else {
                return b;
            }
        } else {
            return strA.length() > strB.length() ? a : b;
        }
    }

    private static String arrToStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i]; j++) {
                    sb.append(i);
                }
            }
        }
        String result = sb.toString();
        return result.charAt(0) == '0' ? "0" : result;
    }
}