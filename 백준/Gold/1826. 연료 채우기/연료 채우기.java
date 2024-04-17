import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Queue<Station> stations = new PriorityQueue<>((o1, o2) -> o1.distance-o2.distance);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            stations.add(new Station(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int currentDistance=P;
        int result = 0;
        Queue<Station> pastStations = new PriorityQueue<>(((o1, o2) -> o2.fuel - o1.fuel));
        while(!stations.isEmpty() && currentDistance < L) {
            while(!stations.isEmpty() && stations.peek().distance <= currentDistance) {
                pastStations.add(stations.poll());
            }
            if(pastStations.isEmpty()) {
                break;
            }
            currentDistance += pastStations.poll().fuel;
            result++;
        }
        System.out.println(currentDistance >= L ? result : -1);
    }
}
class Station {
    public int distance;
    public int fuel;

    public Station(int distance, int fuel) {
        this.distance = distance;
        this.fuel = fuel;
    }
}