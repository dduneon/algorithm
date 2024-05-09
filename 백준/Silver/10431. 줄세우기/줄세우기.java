import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int test_cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < test_cases; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            int[] num = new int[20];
            for (int n = 0; n < 20; n++) {
                num[n] = Integer.parseInt(st.nextToken());
            }

            int swap = 0;
            for (int n = 1; n < 20; n++) {
                for (int m = n; m > 0; m--) {
                    // num[m] = current number
                    // num[m-1] = target number
                    if (num[m] >= num[m - 1]) {
                        break;
                    }
                    // swap
                    int tmp = num[m];
                    num[m] = num[m - 1];
                    num[m - 1] = tmp;
                    swap++;
                }
            }
            sb.append(i + 1).append(" ").append(swap).append("\n");
        }
        System.out.println(sb);
    }
}
