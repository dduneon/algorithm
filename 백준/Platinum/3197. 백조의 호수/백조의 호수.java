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

        boolean isFirst = false;
        // 맵을 입력받고, 물과 백조의 위치를 큐에 추가
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == '.') {
                    water.add(new Pos(i, j));
                } else if(map[i][j] == 'L') {
                    // 백조는 한마리를 기준으로 진행하며 처음 위치를 기록
                    if(!isFirst) {
                        swans.add(new Pos(i, j));
                        visited[i][j] = true;
                        isFirst = true;
                    }
                    // 백조가 있는 위치 또한 물이므로, 추가
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
                    // 방문하지 않은 위치를 방문, 물인 경우 오늘 하루동안 계속 진행할 수 있고, 얼음인 경우 내일까지 기다려야 하므로 다른 Queue에 삽입
                    visited[row][col] = true;
                    if(map[row][col] == '.') {
                        swans.add(new Pos(row, col));
                    } else if(map[row][col] == 'X') {
                        swanstmp.add(new Pos(row, col));
                    } else if(map[row][col] == 'L') {
                        // 백조의 위치에 도착했다면 성공, true 반환
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

                // 주변 얼음들을 찾으며, 얼음들을 큐에 삽입함
                // 만약 그냥 물이라면, 파악할 필요가 없고 방문 여부를 기록해두지 않는 이유는 방문 여부가 없어도 얼음인지 물인지를 판단하는 과정을 통해
                // 중복된 삽입을 하지 않을 수 있기 때문이다
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