import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] lamp;
    private static int N;
    private static int M;
    private static List<List<Integer>> info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 스위치의 수 N
        N = Integer.parseInt(st.nextToken());
        // 램프의 수 M
        M = Integer.parseInt(st.nextToken());
        lamp = new int[M + 1];

        info = new ArrayList<>();
        info.add(new ArrayList<>());
        // 0번째 리스트는 비어있어야 함
        for (int i = 1; i <= N; i++) {
            // 각 스위치에서 연결된 램프
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            List<Integer> s = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int l = Integer.parseInt(st.nextToken());
                lamp[l] += 1;
                s.add(l);
            }
            info.add(s);
        }

        for (int i = 1; i <= N; i++) {
            List<Integer> current = info.get(i);
            boolean can = true;
            for (int c : current) {
                if (lamp[c] - 1 == 0) {
                    can = false;
                    break;
                }
            }
            if (can) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}