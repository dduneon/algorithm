import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 가로로 몇명이 앉을 수 있을까?
        // 사람이 하나, 빈공간이 N개, 사람, 빈공간 ...
        int result = ((H / (1 + N)) + (H % (1 + N) != 0 ? 1 : 0))
                * ((W / (1 + M)) + (W % (1 + M) != 0 ? 1 : 0));

        System.out.println(result);
    }
}
