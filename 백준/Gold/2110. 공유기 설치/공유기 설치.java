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
        int C = Integer.parseInt(st.nextToken());
        int[] houses = new int[N];

        for(int i=0; i<N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int min = 1;
        int max = houses[N-1] - houses[0] + 1;

        int result = 0;
        while(min < max) {
            int mid = (min + max) / 2;
            // mid : 가장 인접한 공유기 사이의 거리 최소값

            int prev = houses[0];
            int count = 1;
            for(int i=1; i<N; i++) {
                int current = houses[i];
                if(current-prev >= mid) {
                    count++;
                    prev = current;
                }
            }

            if(count < C) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min-1);
    }
}