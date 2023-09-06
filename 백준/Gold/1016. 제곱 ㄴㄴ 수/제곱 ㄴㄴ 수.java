import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1016 제곱 ㄴㄴ수 에라토스테네스의 체? -> 저 개수만큼의 배열 생성 불가함 그럼 어떻게 풀이할까? 제곱수로 나누어 떨어지는지?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        // 1, 2, 3 까지 제곱 ㄴㄴ수 -> false
        // 1, 2, 3, 5, 6, 7, 9, 10, 11, 13, 14, 15, 17 ...
        // 4, 8, 12, 16 ... (4의 배수들)
        // 제곱수로 나누어 떨어지는지 ?
        // 4(2^2) 9(3^2) 16(4^2) 25(5^2) 의 배수들을 파악하면 된다
        long size = max - min;
        boolean[] isDivide = new boolean[(int) size + 1];
        // 각 인덱스와 숫자의 관계는? index + min = number

        for (long i = 2; i < Math.pow(max, 0.5) + 1; i++) {
            // long j = min / i + 1;
            long j = min / (i * i);
            while (i * i * j <= max) {
                long tmp;
                tmp = i * i * j;
                if (tmp >= min && tmp <= max) {
                    tmp -= min;
                    if (!isDivide[(int) tmp])
                        isDivide[(int) tmp] = true;
                }
                j += 1;
            }
        }
        int count = 0;
        for (int i = 0; i < isDivide.length; i++) {
            if (!isDivide[i])
                count++;
        }
        System.out.println(count);
    }
}
