import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) - 1;
        StringTokenizer distanceTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer priceTokenizer = new StringTokenizer(br.readLine());

        int[] distance = new int[N];
        int[] price = new int[N];
        for(int i=0; i<N; i++) {
            distance[i] = Integer.parseInt(distanceTokenizer.nextToken());
            price[i] = Integer.parseInt(priceTokenizer.nextToken());
        }

        int[][] station = new int[N][2];
        int remain = 0;
        for(int i=N-1; i>=0; i--) {
            remain += distance[i];
            station[i][0] = remain;
            station[i][1] = price[i];
        }

        Arrays.sort(station, (o1, o2) -> o1[1]-o2[1]);

        int result = 0;
        int current = 0;
        for(int[] st: station) {
            if(st[0] > current) {
                int buy = st[0] - current;
                result = result + buy * st[1];
                current += st[0];
            }
        }
        System.out.println(result);
    }
}