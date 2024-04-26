import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        if (N <= 1) {
            System.out.println(N);
            return;
        }

        long[][] result = fibonacci(N - 1);
        System.out.println(result[0][0]);
    }

    static long[][] matrics = {{1, 1}, {1, 0}};

    public static long[][] fibonacci(long n) {
        if (n == 1) {
            return matrics;
        }

        long[][] tmp = fibonacci(n / 2);
        if (n % 2 == 0) {
            return multiply(tmp, tmp);
        } else {
            return multiply(multiply(tmp, tmp), fibonacci(1));
        }

    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] tmp = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    tmp[i][j] += (a[i][k] * b[k][j]);
                }
                tmp[i][j] %= 1000000;
            }
        }
        return tmp;
    }
}