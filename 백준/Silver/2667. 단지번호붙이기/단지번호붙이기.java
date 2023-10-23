import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sizeOfMap = Integer.parseInt(br.readLine());
        map = new int[sizeOfMap][sizeOfMap];
        visited = new boolean[sizeOfMap][sizeOfMap];
        List<Integer> countList = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            String input = br.readLine();
            for (int j = 0; j < sizeOfMap; j++) {
                map[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                count = 0;
                if (!visited[i][j] && map[i][j] != 0) {
                    dfs(i, j);
                    countList.add(count);
                }
            }
        }

        System.out.println(countList.size());
        Collections.sort(countList);
        for (int count : countList) {
            System.out.println(count);
        }
    }

    static void dfs(int row, int col) {
        if (!isValidPosition(row, col, map.length)) {
            return;
        }
        if (visited[row][col] || map[row][col] == 0) {
            return;
        }
        visited[row][col] = true;
        count++;
        dfs(row - 1, col);
        dfs(row + 1, col);
        dfs(row, col + 1);
        dfs(row, col - 1);
    }

    static boolean isValidPosition(int row, int col, int size) {
        return (row >= 0 && row < size && col >= 0 && col < size);
    }
}
