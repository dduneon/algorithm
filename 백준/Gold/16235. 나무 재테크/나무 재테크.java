import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 초기 상태
 * 양분 : 모든 칸에 5만큼 들어 있음
 * <p>
 * M개의 나무를 구매, 같은 1x1 크기의 칸에 여러개의 나무 심을 수 있음
 * <p>
 * 봄
 * 자신의 나이만큼 양분을 먹고, 나이가 1 증가 (각 1x1 크기의 칸 안에 있는 양분)
 * 여러 나무가 있으면, 나이가 어린 나무부터 양분을 먹음
 * 양분을 먹지 못하면 바로 죽음
 * <p>
 * 여름
 * 봄에 죽은 나무가 양분으로 변함, 각 죽은 나무의 나이를 2로 나눈 값이 칸에 양분으로 추가됨
 * <p>
 * 가을
 * 나무가 번식함. 번식하는 나무의 나이는 5의 배수여야 함
 * 인접한 칸 8개에 나이가 1인 나무가 생김
 * <p>
 * 겨울
 * 로봇이 땅에 돌아다니며 땅에 양분을 추가함. 각 칸에 추가되는 양분의 양 A[r][c]
 * <p>
 * 입력
 * N -> A배열의 값의 개수이자 땅의 크기
 * M -> 구매한 나무의 개수
 * K -> K년 이후 살아있는 나무의 개수를 구함
 */
public class Main {

    private static int N;
    private static int M;
    private static int K;
    private static int[][] A;
    private static List<List<PriorityQueue<Integer>>> map;
    private static int[][] food;
    private static int[][] numberOfTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 겨울마다 추가될 양분에 대한 정보
        A = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 위치에 있는 나무의 나이를 저장하는 2차원 리스트 선언
        // 각 좌표의 내부에는 큐가 존재하고, 나이 순서대로 정렬시킴
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            List<PriorityQueue<Integer>> list = new ArrayList<>();
            for (int j = 0; j <= N; j++) {
                list.add(new PriorityQueue<>());
            }
            map.add(list);
        }

        // 각 위치의 남은 양분을 저장
        food = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(food[i], 5);
        }

        // 나무의 개수를 저장하는 2차원 배열
        numberOfTree = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            map.get(y).get(x).add(z);
            numberOfTree[y][x]++;
        }

        // K년이 지난 후 살아남은 나무를 구하기 위하여 K번 반복
        for (int k = 0; k < K; k++) {
            // 번식하여 새로 생길 나이가 1인 나무들의 좌표(y,x)를 저장
            List<int[]> breeding = new ArrayList<>();

            // 각 좌표별로 나이를 증가시키거나, 죽는 나무들은 양분을 추가
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= N; x++) {
                    PriorityQueue<Integer> currentQueue = map.get(y).get(x);
                    // 비어있으면 실행하지 않음
//                    if (currentQueue.isEmpty()) {
//                        continue;
//                    }
                    boolean lack = false;
                    // 나이가 증가한 나무들을 저장
                    List<Integer> tmp = new ArrayList<>();

                    while (!currentQueue.isEmpty()) {
                        int cur = currentQueue.poll();
                        // 양분이 부족하면, 죽고 양분을 추가시켜줌, 이후에 나오는 모든 나무들은 죽고, 양분이 추가됨
                        if (food[y][x] < cur || lack) {
                            food[y][x] += cur / 2;
                            lack = true;
                        } else {
                            food[y][x] -= cur;
                            tmp.add(cur + 1);
                            // 만약 현재 나무의 나이가 5의 배수가 되면
                            if ((cur + 1) % 5 == 0) {
                                // 가을이 왔다. 인접 나무에 나이가 1인 나무를 심는다
                                for (int[] a : around) {
                                    int nextY = y + a[0];
                                    int nextX = x + a[1];
                                    if (isValid(nextY, nextX)) {
                                        breeding.add(new int[] {nextY, nextX});
                                    }
                                }
                            }
                        }
                    }
                    // 5 5 -> 2 2 -> 4 6 -> 0 2
                    // 양분을 먹고 한살 자라난 나무들을 다시 추가
                    currentQueue.addAll(tmp);

                    // 겨울이 왔다. 양분을 추가해준다.
                    food[y][x] += A[y][x];
                }
            }
            // 가을에 추가한 나이 1짜리 나무들을 추가해준다
            for (int[] b : breeding) {
                map.get(b[0]).get(b[1]).add(1);
            }

        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += map.get(i).get(j).size();
            }
        }
        System.out.println(result);
    }

    private static int[][] around = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static boolean isValid(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }
}