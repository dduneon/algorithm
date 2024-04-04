import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] cables = new int[K];
        long min = 0;
        long max = 0;
        for(int i=0; i<K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cables[i]);
        }
        max++;
        while(min < max) {
            long mid = (min+max) / 2;

            int divided = 0;
            for(int i=0; i<K; i++) {
                divided += (cables[i] / mid);
            }

            if(divided < N) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min-1);
    }
}