import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] numbers;
    private static int N;
    private static int[] operators;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operators = new int[4];
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[N];
        result[0] = numbers[0];
        solve(1);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n').append(min);
        System.out.println(sb);
    }

    private static int[] result;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static void solve(int depth) {
        if (depth == N) {
            max = Math.max(max, result[depth - 1]);
            min = Math.min(min, result[depth - 1]);
            return;
        }

        // + - * /
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i] -= 1;
                switch (i) {
                    case 0:
                        result[depth] = result[depth - 1] + numbers[depth];
                        break;
                    case 1:
                        result[depth] = result[depth - 1] - numbers[depth];
                        break;
                    case 2:
                        result[depth] = result[depth - 1] * numbers[depth];
                        break;
                    case 3:
                        result[depth] = result[depth - 1] / numbers[depth];
                        break;
                }
                solve(depth + 1);
                operators[i] += 1;
            }
        }
    }
}