import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sizeOfMap = Integer.parseInt(br.readLine());
        map = new int[sizeOfMap][sizeOfMap];
        visited = new boolean[sizeOfMap][sizeOfMap];
        StringTokenizer st;

        for (int i = 0; i < sizeOfMap; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeOfMap; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (!visited[i][j])
                    dfs(i, j);
            }
        }
    }

    static void dfs(int row, int col) {

    }
}
