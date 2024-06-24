import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_cases = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for(int t=0; t<test_cases; t++) {
            N = Integer.parseInt(br.readLine());
            operators = new char[N-1];
            solve(0);

            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static char[] operators;
    private static StringBuilder sb;
    private static Deque<Integer> tmp = new ArrayDeque<>();
    private static void solve(int depth) {
        if(depth == N-1) {
            tmp.add(1);
            for (int i = 0; i < N-1; i++) {
                if (operators[i] == ' ') {
                    int num = tmp.pollLast() * 10 + (i+2);
                    tmp.add(num);
                } else {
                    tmp.add(i+2);
                }
            }

            int idx = 0;
            // 1 버림
            int result = tmp.poll();
            while(!tmp.isEmpty()) {
                if(operators[idx] == '+') {
                    result += tmp.poll();
                } else if (operators[idx] == '-') {
                    result -= tmp.poll();
                }
                idx++;
            }
            if(result == 0) {
                sb.append(1);
                for(int i=0; i<N-1; i++) {
                    sb.append(operators[i]).append(i+2);
                }
                sb.append('\n');
            }

            return;
        }

        operators[depth] = ' ';
        solve(depth+1);
        operators[depth] = '+';
        solve(depth+1);
        operators[depth] = '-';
        solve(depth+1);
    }
}