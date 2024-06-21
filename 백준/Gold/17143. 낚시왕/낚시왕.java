import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int R;
    private static int C;
    private static int M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        Queue<Shark> queue1 = new LinkedList<>();
        Queue<Shark> queue2 = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 상어의 위치 (r, c)
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 상어의 속력 s
            int s = Integer.parseInt(st.nextToken());
            // 상어의 이동 방향(1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽)
            int d = Integer.parseInt(st.nextToken());
            // 상어의 크기 (두 상어가 같은 크기를 갖는 경우는 없음)
            int z = Integer.parseInt(st.nextToken());
            queue1.add(new Shark(r, c, s, d, z));
            // 맵에 크기 표시 (크기는 중복 없음)
            map[r][c] = z;
        }

        // 현재 낚시왕이 서있는 위치 (0부터 C+1까지 도달)
        int count = 1;
        // 낚시왕이 잡은 상어를 보관한다
        Set<Integer> diedShark = new HashSet<>();
        // 잡은 상어의 크기의 합
        int result = 0;
        while (count <= C) {
            Queue<Shark> currentQueue;
            Queue<Shark> otherQueue;
            if (queue1.isEmpty()) {
                currentQueue = queue2;
                otherQueue = queue1;
            } else {
                currentQueue = queue1;
                otherQueue = queue2;
            }

            // 현재 낚시왕이 잡을 수 있는 상어를 잡는다
            for (int i = 1; i <= R; i++) {
                // 만약 0보다 크다면, 잡는다
                if (map[i][count] > 0) {
                    int size = map[i][count];
                    // 해당 상어는 죽음
                    diedShark.add(size);
                    result += size;
                    map[i][count] = 0;

                    break;
                }
            }

            // 만약 낚시왕이 마지막 열에 도달했다면, 더이상 상어를 움직이지 않아도 된다
            if (count == C) {
                break;
            }

            int[][] moved = new int[R + 1][C + 1];
            // 현재 있는 상어들의 위치를 변경해준다!
            while (!currentQueue.isEmpty()) {
                Shark current = currentQueue.poll();
                if (diedShark.contains(current.size)) {
                    continue;
                }

                if (current.direction == 1) {
                    // 위로 움직이는 상어일 경우
                    int position = current.r - current.speed;
                    // 정상 범위를 벗어난 경우
                    if (position < 1) {
                        position *= -1;
                        position += 1;
                        if ((position / (R - 1)) % 2 == 0) {
                            current.direction = 2;
                            current.r = position % (R - 1) + 1;
                        } else {
                            current.r = R - (position % (R - 1));
                        }
                    } else {
                        current.r = position;
                    }
                } else if (current.direction == 2) {
                    // 아래로 움직이는 상어일 경우
                    int position = current.r + current.speed;
                    // 정상 범위를 벗어난 경우
                    if (position > R) {
                        position -= R;
                        if ((position / (R - 1)) % 2 == 0) {
                            current.direction = 1;
                            current.r = R - position % (R - 1);
                        } else {
                            current.r = (position % (R - 1)) + 1;
                        }
                    } else {
                        current.r = position;
                    }
                } else if (current.direction == 3) {
                    // 오른쪽으로 움직이는 상어일 경우
                    int position = current.c + current.speed;
                    // 정상 범위를 벗어난 경우
                    if (position > C) {
                        position -= C;
                        if ((position / (C - 1)) % 2 == 0) {
                            current.direction = 4;
                            current.c = C - position % (C - 1);
                        } else {
                            current.c = (position % (C - 1)) + 1;
                        }
                    } else {
                        current.c = position;
                    }
                } else {
                    // 왼쪽으로 움직이는 상어일 경우
                    int position = current.c - current.speed;
                    // 정상 범위를 벗어난 경우
                    if (position < 1) {
                        position *= -1;
                        position += 1;
                        if ((position / (C - 1)) % 2 == 0) {
                            current.direction = 3;
                            current.c = position % (C - 1) + 1;
                        } else {
                            current.c = C - (position % (C - 1));
                        }
                    } else {
                        current.c = position;
                    }
                }
                // 만약 옮긴 자리에 상어가 존재하면
                if (moved[current.r][current.c] > 0) {
                    if (moved[current.r][current.c] > current.size) {
                        // 만약 해당 위치에 있는 상어가 현재 크기보다 크다면 잡아먹힘
                        diedShark.add(current.size);
                        // 사실 큐에 다시 안넣기 때문에 상관은 없을 듯
                        continue;
                    } else {
                        // 기존 상어는 주금
                        diedShark.add(moved[current.r][current.c]);
                    }
                }
                moved[current.r][current.c] = current.size;
                otherQueue.add(current);
            }

            map = moved;
            // 1초를 올려준다 (0초에는 아무것도 잡을 수 없다, C는 1부터 시작)
            count++;
        }
        System.out.println(result);
    }
}

class Shark {
    int r;
    int c;
    int speed;
    int direction;
    int size;

    public Shark(int r, int c, int speed, int direction, int size) {
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }
}