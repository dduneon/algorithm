import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new PriorityQueue();
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int card = Integer.parseInt(br.readLine());
            queue.add(card);
        }
        int sum = 0;
        while(queue.size() > 1) {
            int A = queue.poll();
            int B = queue.poll();

            sum += (A + B);
            queue.add(A+B);
        }
        System.out.println(sum);
    }
}