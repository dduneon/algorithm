import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 응시자들의 수
        int[] part = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            part[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 필요한 감독관으로 총 감독관 N명은 반드시 있어야 함
        long result = N;

        for (int i = 0; i < N; i++) {
            part[i] -= B;
            if (part[i] <= 0) {
                continue;
            }

            result += (part[i] % C != 0) ? (part[i] / C) + 1 : part[i] / C;
        }

        System.out.println(result);
    }
}