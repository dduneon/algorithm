import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requests = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int min = 0;
        int max = 0;
        for(int i=0; i<N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, requests[i]);
        }

        max++;
        while(min < max) {
            int mid = (min + max) / 2;

            int sum = 0;
            for(int i=0; i<N; i++) {
                sum += Math.min(mid, requests[i]);
            }

            if(M < sum) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min - 1);
    }
}