import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] arr = new int[101][101];
    private static int currentR;
    private static int currentC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // (r, c) 에 들어있는 값이 k가 되기 위한 연산의 최소 시간
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 100초가 지나도 k가 되지 않으면 -1을 출력하기
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        currentR = 3;
        currentC = 3;
        int second = 0;

        while (second <= 100) {
            if (arr[r][c] == k) {
                System.out.println(second);
                return;
            }

            if (currentR >= currentC) {
                operationR();
            } else {
                operationC();
            }

            second++;
        }
        System.out.println(-1);

//        for (int i = 1; i <= 10; i++) {
//            for (int j = 1; j <= 10; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

    }

    private static int[] counting;
    private static PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        }
    });

    private static void operationR() {
        int[][] newarr = new int[101][101];
        for (int r = 1; r <= currentR; r++) {
            counting = new int[101];
            // 해당 행의 숫자들을 카운팅
            for (int c = 1; c <= currentC; c++) {
                if (arr[r][c] != 0) {
                    counting[arr[r][c]]++;
                }
            }
            // 카운팅된 정보를 우선순위 큐에 넣어서 정렬시킴
            for (int i = 1; i <= 100; i++) {
                if (counting[i] > 0) {
                    queue.add(new int[] {i, counting[i]});
                }
            }

            // 정렬된 정보를 큐에 삽입

            // 행의 길이는 큐의 크기의 2배임 (숫자, 개수)
            currentC = Math.max(currentC, queue.size() * 2);
            int curIdx = 1;
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                newarr[r][curIdx] = current[0];
                newarr[r][curIdx + 1] = current[1];

                curIdx += 2;
            }
        }
        arr = newarr;
    }

    private static void operationC() {
        int[][] newarr = new int[101][101];
        for (int c = 1; c <= currentC; c++) {
            counting = new int[101];
            // 해당 행의 숫자들을 카운팅
            for (int r = 1; r <= currentR; r++) {
                if (arr[r][c] != 0) {
                    counting[arr[r][c]]++;
                }
            }
            // 카운팅된 정보를 우선순위 큐에 넣어서 정렬시킴
            for (int i = 1; i <= 100; i++) {
                if (counting[i] > 0) {
                    queue.add(new int[] {i, counting[i]});
                }
            }

            // 정렬된 정보를 큐에 삽입

            // 행의 길이는 큐의 크기의 2배임 (숫자, 개수)
            currentR = Math.max(currentR, queue.size() * 2);
            int curIdx = 1;
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                newarr[curIdx][c] = current[0];
                newarr[curIdx + 1][c] = current[1];

                curIdx += 2;
            }
        }
        arr = newarr;
    }
}