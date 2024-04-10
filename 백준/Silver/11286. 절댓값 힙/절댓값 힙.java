import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) - Math.abs(o2) == 0)
                    return o1 - o2;
                return Math.abs(o1) - Math.abs(o2);
            }
        });
        for(int i=0; i<N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input == 0) {
                if(queue.size() == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.add(input);
            }
        }
    }
}