import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] medal = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            medal[i][0] = Integer.parseInt(st.nextToken());
            medal[i][1] = Integer.parseInt(st.nextToken());
            medal[i][2] = Integer.parseInt(st.nextToken());
            medal[i][3] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(medal, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1] == o1[1]) {
                    if (o2[2] == o1[2]) {
                        return o2[3] - o1[3];
                    } else {
                        return o2[2] - o1[2];
                    }
                } else {
                    return o2[1] - o1[1];
                }
            }
        });

        int result = 1;
        for (int i = 1; i < N; i++) {
            // 동점인 경우
            if (!(medal[i][1] == medal[i - 1][1] && medal[i][2] == medal[i - 1][2] && medal[i][3] == medal[i - 1][3])) {
                result = i + 1;
            }
            if (medal[i][0] == K) {
                System.out.println(result);
                return;
            }
        }
    }
}
