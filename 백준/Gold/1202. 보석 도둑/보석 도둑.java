import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Item> queue = new PriorityQueue<>(((o1, o2) -> o1.weight - o2.weight));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] bag = new int[K];
        for(int i=0; i<K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);

        Queue<Item> steals = new PriorityQueue<>(((o1, o2) -> o2.price - o1.price));
        long result = 0;
        for(int i=0; i<K; i++) {
            int weight = bag[i];

            while(!queue.isEmpty() && queue.peek().weight <= weight) {
                steals.add(queue.poll());
            }
            if(!steals.isEmpty()) {
                result += steals.poll().price;
            }
        }
        System.out.println(result);
    }
}

class Item {
    public final int weight;
    public final int price;

    public Item(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}