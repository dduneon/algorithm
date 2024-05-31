import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st1 = br.readLine();
        String st2 = br.readLine();

        int[][] map = new int[st1.length()][st2.length()];

        int max = 0;
        for (int i = 0; i < st1.length(); i++) {
            for (int j = 0; j < st2.length(); j++) {
                if (st1.charAt(i) == st2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = map[i - 1][j - 1] + 1;
                        max = Math.max(max, map[i][j]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}