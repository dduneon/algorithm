import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = backtracking(B);
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static int[][] backtracking(long n) {
        if(n==1) {
            return arr;
        }

        int[][] tmp = backtracking(n/2);

        if(n%2==0) {
            return calculate(tmp, tmp);
        } else {
            return calculate(calculate(tmp, tmp), arr);
        }
    }

    public static int[][] calculate(int[][] a, int[][] b) {
        int[][] result = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int current = 0;
                for(int k=0; k<N; k++) {
                    current += a[i][k] * b[k][j];
                }
                result[i][j] = current % 1000;
            }
        }
        return result;
    }
}