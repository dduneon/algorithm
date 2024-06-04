import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean isAsc = Integer.parseInt(st.nextToken()) == 1;

        if (isAsc) {
            for (int i = 2; i <= 8; i++) {
                if (i != Integer.parseInt(st.nextToken())) {
                    System.out.println("mixed");
                    return;
                }
            }
            System.out.println("ascending");
        } else {
            for (int i = 7; i >= 1; i--) {
                if (i != Integer.parseInt(st.nextToken())) {
                    System.out.println("mixed");
                    return;
                }
            }
            System.out.println("descending");
        }
    }
}