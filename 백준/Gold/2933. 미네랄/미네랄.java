import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int R;
    static int C;
    static int minerals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        minerals = 0;
        for (int i = R - 1; i >= 0; i--) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'x') {
                    minerals++;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            int[] hited = findHit(height - 1, i % 2 == 0);
            if (Objects.nonNull(hited)) {
                // 클러스터가 분리된 경우
                if (minerals != findClusterFromBottom()) {
                    // 분리된 한개의 클러스터를 찾는다
                    List<int[]> cluster = findSeperatedCluster();
                    int moveSize = findMoveSizeToBottom(cluster);
                    if (moveSize > 0) {
                        moveCluster(cluster, moveSize);
                    }
                }
            }
        }
        print();
    }

    public static void moveCluster(List<int[]> cluster, int moveSize) {
        for (int[] current : cluster) {
            map[current[0] - moveSize][current[1]] = 'x';
            map[current[0]][current[1]] = '.';
        }
    }

    public static int findMoveSizeToBottom(List<int[]> cluster) {
        int[] clusterBottoms = new int[C];
        // 클러스터를 아래쪽으로 이동시키기 위해 가장 바닥 칸의 위치를 구함
        for (int i = 0; i < C; i++) {
            clusterBottoms[i] = R;
        }

        for (int[] current : cluster) {
            clusterBottoms[current[1]] = Math.min(clusterBottoms[current[1]], current[0]);
        }
        int moveTo = R;
        for (int i = 0; i < C; i++) {
            if (clusterBottoms[i] != R) {
                int count = 0;
                for (int j = clusterBottoms[i] - 1; j >= 0; j--) {
                    if (map[j][i] == 'x') {
                        break;
                    }
                    count++;
                }
                moveTo = Math.min(moveTo, count);
            }
        }
        return moveTo;
    }

    public static List<int[]> findSeperatedCluster() {
        List<int[]> seperated = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && !visited[i][j]) {
                    seperated.add(new int[] {i, j});
                }
            }
        }
        return seperated;
    }

    /**
     * 막대를 height 높이로 던졌을 때, 맞는 미네랄의 좌표를 리턴하는 메서드
     *
     * @param height 막대를 던진 높이
     * @param isLeft 왼쪽에서 던졌으면 true, 오른쪽에서 던졌다면 false
     * @return 미네랄의 좌표, 아무것도 안맞았다면 null 반환
     */
    public static int[] findHit(int height, boolean isLeft) {
        if (isLeft) {
            for (int i = 0; i < C; i++) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    minerals--;
                    return new int[] {height, i};
                }
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    minerals--;
                    return new int[] {height, i};
                }
            }
        }
        // 아무것도 맞지 않았을 경우에는 null 반환
        return null;
    }

    public static void print() {
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited;

    public static int findClusterFromBottom() {
        visited = new boolean[R][C];
        int blocks = 0;
        for (int i = 0; i < C; i++) {
            // 바닥의 미네랄 위치에서 탐색한다
            if (map[0][i] != 'x') {
                continue;
            }

            Queue<int[]> adjacent = new LinkedList<>();

            adjacent.add(new int[] {0, i});
            while (!adjacent.isEmpty()) {
                int[] current = adjacent.poll();
                for (int[] a : around) {
                    int aroundRow = current[0] + a[0];
                    int aroundCol = current[1] + a[1];

                    if (isValid(aroundRow, aroundCol) && !visited[aroundRow][aroundCol]
                            && map[aroundRow][aroundCol] == 'x') {
                        visited[aroundRow][aroundCol] = true;
                        blocks++;
                        adjacent.add(new int[] {aroundRow, aroundCol});
                    }
                }
            }
        }
        return blocks;
    }

    public static boolean isValid(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }
}