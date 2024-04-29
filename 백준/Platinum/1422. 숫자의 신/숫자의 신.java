import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int max = 0;
        String[] arr = new String[n];
        for (int i = 0; i < k; i++) {
            arr[i] = br.readLine();
            max = Math.max(max, Integer.parseInt(arr[i]));
        }
        String maxStr = String.valueOf(max);
        for (int i = k; i < n; i++) {
            arr[i] = maxStr;
        }
        Arrays.sort(arr, (s1, s2) -> {
            if (s1.length() == s2.length()) {
                int a = Integer.parseInt(s1);
                int b = Integer.parseInt(s2);
                return b - a;
            } else {
                String a = s1 + s2;
                String b = s2 + s1;
                return b.compareTo(a);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}
