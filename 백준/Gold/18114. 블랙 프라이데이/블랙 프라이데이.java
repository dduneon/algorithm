import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            // 만약 1개의 선택으로 C를 만들수 있는지 체크
            if (weight[i] == C) {
                System.out.println(1);
                return;
            }
        }

        // 일단 정렬
        Arrays.sort(weight);

        int start = 0;
        int end = N - 1;

        while (start < end) {
            int one = weight[start];
            int two = weight[end];

            if (one + two == C) {
                System.out.println(1);
                return;
            } else if (one + two > C) {
                end--;
            } else {
                int rest = C - (one + two);
                if (one != rest && two != rest && binary_search(0, N - 1, rest)) {
                    System.out.println(1);
                    return;
                }
                start++;
            }
        }
        System.out.println(0);
    }

    private static boolean binary_search(int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;

            if (weight[mid] == target) {
                return true;
            } else if (weight[mid] > target) {
                end--;
            } else {
                start++;
            }
        }
        return false;
    }
}