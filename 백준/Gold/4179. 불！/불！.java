import java.util.*;
import java.io.*;

public class Main {
    static Deque<Node> queue;
    static char map[][];

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        queue = new LinkedList<>();
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'J') {
                    // 지훈의 위치를 .으로 변경
                    map[i][j] = '.';
                    queue.addFirst(new Node(i, j, 0, true));
                    visited[i][j] = true;
                } else if(map[i][j] == 'F') {
                    queue.addLast(new Node(i, j, 0, false));
                }
            }
        }

        int result = bfs(R, C);
        if(result == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result + 1);
        }
    }

    static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited;
    public static int bfs(int R, int C) {
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            // 현재 위치에 불이 도달해 버린 경우 더이상 갈 수 없음
            if(current.isPerson && map[current.i][current.j] == 'F') {
                continue;
            }
            if(current.isPerson && (current.i == R-1 || current.j == C-1 || current.i == 0 || current.j == 0)) {
                return current.day;
            }

            for(int[] a: around) {
                int nextI = current.i + a[0];
                int nextJ = current.j + a[1];
                // 지훈이인 경우
                if (nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C) {
                    continue;
                }
                if(current.isPerson) {
                    if(!visited[nextI][nextJ] && map[nextI][nextJ] == '.') {
                        queue.add(new Node(nextI, nextJ, current.day+1, true));
                        visited[nextI][nextJ] = true;
                    }
                } else {
                    if(map[nextI][nextJ] == '.') {
                        map[nextI][nextJ] = 'F';
                        queue.add(new Node(nextI, nextJ, current.day+1, false));
                    }
                }
            }
        }
        return -1;
    }
}

class Node {
    int i;
    int j;
    int day;
    boolean isPerson;

    public Node(int i, int j, int day, boolean isPerson) {
        this.i = i;
        this.j = j;
        this.day = day;
        this.isPerson = isPerson;
    }
}