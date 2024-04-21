import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static char[][] map;
    static boolean[][] visited;
    static Queue<Pos> water = new LinkedList<>();
    static Queue<Pos> watertmp = new LinkedList<>();
    static Queue<Pos> swans = new LinkedList<>();
    static Queue<Pos> swanstmp = new LinkedList<>();
    static int[] swansFirstPos = null;

    public static int R, C;

    public static void main(String[] args) throws IOException{
        // 맵을 입력받는 부분
        input();

        int day = 0;
        while(!moveSwan()) {
            meltIce();
            water = watertmp;
            swans = swanstmp;

            watertmp = new LinkedList<>();
            swanstmp = new LinkedList<>();
            day++;
        }
        System.out.println(day);
    }
    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 전체적인 맵 및 방문 여부 기록
        map = new char[R][C];
        visited = new boolean[R][C];

        // 맵을 입력받고, 물과 백조의 위치를 큐에 추가
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == '.') {
                    water.add(new Pos(i, j));
                } else if(map[i][j] == 'L') {
                    if(swansFirstPos == null) {
                        swans.add(new Pos(i, j));
                        swansFirstPos = new int[]{i, j};
                        visited[i][j] = true;
                    }
                    water.add(new Pos(i, j));
                }
            }
        }
        br.close();
    }

    public static boolean moveSwan() {
        while(!swans.isEmpty()) {
            Pos current = swans.poll();
            for(int[] move: moves) {
                int row = current.row + move[0];
                int col = current.col + move[1];

                if(Pos.isValidPos(row, col) && !visited[row][col]) {
                    visited[row][col] = true;
                    if(map[row][col] == '.') {
                        swans.add(new Pos(row, col));
                    } else if(map[row][col] == 'X') {
                        swanstmp.add(new Pos(row, col));
                    } else if(map[row][col] == 'L' && (swansFirstPos[0] != row || swansFirstPos[1] != col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void meltIce() {
        while(!water.isEmpty()) {
            Pos current = water.poll();
            for(int[] move: moves) {
                int row = current.row + move[0];
                int col = current.col + move[1];

                if(Pos.isValidPos(row, col) && map[row][col] == 'X') {
                    watertmp.add(new Pos(row, col));
                    map[row][col] = '.';
                }
            }
        }
    }
}

class Pos {
    int row;
    int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static boolean isValidPos(int row, int col) {
        return row >= 0 && row < Main.R && col >= 0 && col < Main.C;
    }
}