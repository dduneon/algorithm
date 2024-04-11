import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> maxQueue = new PriorityQueue<>(((o1, o2) -> o2-o1));
        Queue<Integer> minQueue = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(maxQueue.size() < minQueue.size() + 1) {
                maxQueue.add(input);
            } else {
                minQueue.add(input);
            }
            if(minQueue.size() > 0 && maxQueue.peek() > minQueue.peek()) {
                int minTop = minQueue.poll();
                int maxTop = maxQueue.poll();
                minQueue.add(maxTop);
                maxQueue.add(minTop);
            }
            sb.append(maxQueue.peek()).append('\n');
        }
        System.out.println(sb);
    }
}