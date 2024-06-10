import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;
    private static int result = 0;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                result = Math.max(result, board[i][j]);
            }
        }

        dfs(0, board);
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(t[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(result);
    }

    private static void dfs(int depth, int[][] arr) {
        // 5번까지만 이동할 수 있음
        if (depth == 5) {
            return;
        }

        dfs(depth + 1, moveLeft(arr));
        dfs(depth + 1, moveRight(arr));
        dfs(depth + 1, moveTop(arr));
        dfs(depth + 1, moveBottom(arr));
    }

    private static int[][] moveLeft(int[][] arr) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            boolean check = false;
            for (int current = 0; current < N; current++) {
                if (arr[i][current] != 0) {
                    if (!stack.isEmpty() && stack.peek() == arr[i][current] && !check) {
                        stack.push(stack.pop() * 2);
                        result = Math.max(result, stack.peek());
                        check = true;
                    } else {
                        stack.push(arr[i][current]);
                        check = false;
                    }
                }
            }
            int target = stack.size() - 1;
            while (!stack.isEmpty()) {
                tmp[i][target] = stack.pop();
                target--;
            }
            stack.clear();
        }
        return tmp;
    }

    private static int[][] moveRight(int[][] arr) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            boolean check = false;
            for (int current = N - 1; current >= 0; current--) {
                if (arr[i][current] != 0) {
                    if (!stack.isEmpty() && stack.peek() == arr[i][current] && !check) {
                        stack.push(stack.pop() * 2);
                        result = Math.max(result, stack.peek());
                        check = true;
                    } else {
                        stack.push(arr[i][current]);
                        check = false;
                    }
                }
            }
            int target = N - stack.size();
            while (!stack.isEmpty()) {
                tmp[i][target] = stack.pop();
                target++;
            }
            stack.clear();
        }
        return tmp;
    }

    private static int[][] moveTop(int[][] arr) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            boolean check = false;
            for (int current = 0; current < N; current++) {
                if (arr[current][i] != 0) {
                    if (!stack.isEmpty() && stack.peek() == arr[current][i] && !check) {
                        stack.push(stack.pop() * 2);
                        result = Math.max(result, stack.peek());
                        check = true;
                    } else {
                        stack.push(arr[current][i]);
                        check = false;
                    }
                }
            }
            int target = stack.size() - 1;
            while (!stack.isEmpty()) {
                tmp[i][target] = stack.pop();
                target--;
            }
            stack.clear();
        }
        return tmp;
    }

    private static int[][] moveBottom(int[][] arr) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            boolean check = false;
            for (int current = N - 1; current >= 0; current--) {
                if (arr[current][i] != 0) {
                    if (!stack.isEmpty() && stack.peek() == arr[current][i] && !check) {
                        stack.push(stack.pop() * 2);
                        result = Math.max(result, stack.peek());
                        check = true;
                    } else {
                        stack.push(arr[current][i]);
                        check = false;
                    }
                }
            }
            int target = N - stack.size();
            while (!stack.isEmpty()) {
                tmp[i][target] = stack.pop();
                target++;
            }
            stack.clear();
        }
        return tmp;
    }

}
