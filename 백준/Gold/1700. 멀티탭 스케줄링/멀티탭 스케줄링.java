import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 멀티탭 구멍의 개수
        int N = Integer.parseInt(st.nextToken());
        // 전기 용품의 총 사용 개수
        int K = Integer.parseInt(st.nextToken());
        // 1-K 까지의 자연수를 가지는 전기용품

        st = new StringTokenizer(br.readLine());
        // 전기 용품의 사용 순서
        int[] order = new int[K];
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        // 멀티탭에 꽂힌 제품들
        Set<Integer> choose = new HashSet<>();
        for (int i = 0; i < K; i++) {
            if (choose.contains(order[i])) {
                continue;
            } else if (choose.size() < N) {
                choose.add(order[i]);
                continue;
            }

            // 빼는 순서
            // 1. 이후에 사용하지 않는 제품
            // 2. 이후에 사용하지만, 가장 나중에 사용하는 제품
            List<Integer> record = new ArrayList<>();
            for (int j = i + 1; j < K; j++) {
                if (choose.contains(order[j]) && !record.contains(order[j])) {
                    record.add(order[j]);
                }
            }

            boolean used = false;
            int selected = -1;
            for (int c : choose) {
                if (!record.contains(c)) {
                    selected = c;
                    used = true;
                    break;
                }
            }

            if (!used) {
                selected = record.get(record.size() - 1);
            }

            choose.remove(selected);
            choose.add(order[i]);
            result++;
        }

        System.out.println(result);
    }
}