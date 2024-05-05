import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean[] S = new boolean[21];

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    S[Integer.parseInt(st.nextToken())] = true;
                    break;
                case "remove":
                    S[Integer.parseInt(st.nextToken())] = false;
                    break;
                case "check":
                    sb.append(S[Integer.parseInt(st.nextToken())] ? 1 : 0).append("\n");
                    break;
                case "toggle":
                    int num = Integer.parseInt(st.nextToken());
                    S[num] = !S[num];
                    break;
                case "all":
                    Arrays.fill(S, true);
                    break;
                case "empty":
                    Arrays.fill(S, false);
                    break;
            }
        }
        System.out.println(sb);
    }
}
