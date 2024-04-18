import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // o2 o1의 길이가 서로 같은 경우
                if(o1.length() == o2.length()) {
                    return Integer.parseInt(o2) - Integer.parseInt(o1);
                }
                // o2 o1의 길이가 서로 다른 경우
                int minLength = Math.min(o1.length(), o2.length());
                for(int i=0; i<minLength; i++) {
                    if(o1.charAt(i) == o2.charAt(i)) {
                        continue;
                    }
                    return Character.getNumericValue(o2.charAt(i)) - Character.getNumericValue(o1.charAt(i));
                }
                // 길이가 서로 다르고, 2개의 수 중 작은 길이의 수만큼이 동일한 경우
                String a = o1 + o2;
                String b = o2 + o1;
                return Long.parseLong(a) > Long.parseLong(b) ? -1 : 1;
            }
        });
        int max = 0;
        for(int i=0; i<K; i++) {
            String input = br.readLine();
            queue.add(input);
            max = Math.max(max, Integer.parseInt(input));
        }
        for(int i=0; i<N-K; i++) {
            queue.add(String.valueOf(max));
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(queue.poll());
        }

        System.out.println(sb);

    }
}