import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int test_cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < test_cases; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            System.out.println(solution(h, w));
        }
    }

    static int[][][] minWeight;
    static int MAX_DOOR = 10000;

    public static int solution(int h, int w) throws IOException {
        char[][] map = new char[h + 2][w + 2];
        minWeight = new int[h + 2][w + 2][3];
        for (int i = 0; i < h + 2; i++) {
            for (int j = 0; j < w + 2; j++) {
                minWeight[i][j][0] = MAX_DOOR;
                minWeight[i][j][1] = MAX_DOOR;
                minWeight[i][j][2] = MAX_DOOR;
            }
        }
        // 세 사람의 시작하는 지점을 기록
        Queue<int[]> startPos = new LinkedList<>();

        for (int i = 1; i <= h; i++) {
            String line = br.readLine();
            for (int j = 1; j <= w; j++) {
                map[i][j] = line.charAt(j - 1);
                // 죄수의 위치를 기록
                if (map[i][j] == '$') {
                    startPos.add(new int[] {i, j});
                }
            }
        }
        // 나머지 부분(외곽 지역)을 빈 공간으로 채우기
        for (int i = 0; i < w + 2; i++) {
            map[0][i] = '.';
            map[h + 1][i] = '.';
        }
        for (int i = 0; i < h + 2; i++) {
            map[i][0] = '.';
            map[i][w + 1] = '.';
        }
        // 상근이의 위치는 외곽 어디서든 시작할 수 있음
        startPos.add(new int[] {0, 0});

        // 세 사람을 기준으로 BFS
        for (int i = 0; i < 3; i++) {
            int[] start = startPos.poll();
            minWeight[start[0]][start[1]][i] = 0;
            bfs(map, start, i);
            minWeight[start[0]][start[1]][i] = MAX_DOOR;
        }


        int[][] sumMap = new int[h + 2][w + 2];
        int minValue = MAX_DOOR;
        int[] minPos = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sumMap[i][j] += (minWeight[i][j][0] + minWeight[i][j][1] + minWeight[i][j][2]);
                if (map[i][j] == '#') {
                    sumMap[i][j] -= 2;
                }
                minValue = Math.min(minValue, sumMap[i][j]);
                if (minValue == sumMap[i][j]) {
                    minPos[0] = i;
                    minPos[1] = j;
                }
            }
        }

        return minValue;
    }

    static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void bfs(char[][] map, int[] started, int num) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(started);

        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            for (int[] a : around) {
                int nextH = current[0] + a[0];
                int nextW = current[1] + a[1];
                if (isValid(nextH, nextW, map) && minWeight[nextH][nextW][num] == MAX_DOOR) {
                    if (map[nextH][nextW] == '.' || map[nextH][nextW] == '$') {
                        deque.addFirst(new int[] {nextH, nextW});
                        minWeight[nextH][nextW][num] = minWeight[current[0]][current[1]][num];
                    } else if (map[nextH][nextW] == '#') {
                        deque.addLast(new int[] {nextH, nextW});
                        minWeight[nextH][nextW][num] = minWeight[current[0]][current[1]][num] + 1;
                    }
                }
            }
        }


    }

    public static boolean isValid(int h, int w, char[][] map) {
        return h >= 0 && h < map.length && w >= 0 && w < map[0].length;
    }
}
