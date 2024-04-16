import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) - 1;
        int[] distance = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] price = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] station = new int[N][2];
        int remain = 0;
        for(int i=N-1; i>=0; i--) {
            remain += distance[i];
            station[i][0] = remain;
            station[i][1] = price[i];
        }

        Arrays.sort(station, (o1, o2) -> o1[1]-o2[1]);

        long result = 0;
        int current = 0;
        for(int[] st: station) {
            if(st[0] > current) {
                int buy = st[0] - current;
                result = result + (long) buy * st[1];
                current += buy;
            }
        }
        System.out.println(result);
    }
}