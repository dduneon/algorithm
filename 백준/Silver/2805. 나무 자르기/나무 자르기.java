import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int min = 0;
        int max = Arrays.stream(trees).max().getAsInt();

        while(min < max) {
            int mid = (min + max) / 2;
            long sum = 0;
            for(int i=0; i<N; i++) {
                if(trees[i] - mid > 0)
                    sum += (trees[i] - mid);
            }

            if(sum < M) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min-1);
    }
}