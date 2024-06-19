import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 샘터의 개수
        int N = Integer.parseInt(st.nextToken());
        // 짓고자 하는 집의 개수
        int K = Integer.parseInt(st.nextToken());

        // 각 쉼터의 왼쪽 오른쪽 공백을 저장
        int[][] arr = new int[N][2];

        // 시작 위치, 끝 위치
        int startPos = -200_000_000;
        int endPos = 200_000_000;

        st = new StringTokenizer(br.readLine());
        int[] spot = new int[N];
        for (int i = 0; i < N; i++) {
            spot[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(spot);

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            arr[i][0] = spot[i] - startPos - 1;
            if (i == N - 1) {
                arr[i][1] = endPos - spot[i] - 1;
            } else {
                arr[i][1] = spot[i + 1] - spot[i] - 1;
            }
            if (arr[i][0] > 0) {
                queue.add(new int[] {i, 0, 1});
            }
            if (arr[i][1] > 0) {
                queue.add(new int[] {i, 1, 1});
            }
            startPos = spot[i];
        }

        int count = 0;
        long result = 0;
        while (count < K && !queue.isEmpty()) {
            int[] current = queue.poll();
            int currentIdx = current[0];
            int subIdx = current[1];
            int depth = current[2];

            if (arr[currentIdx][subIdx] > 0) {
                count += 1;
                result += depth;
                arr[currentIdx][subIdx] -= 1;
                if (!((currentIdx == 0 && subIdx == 0) || (currentIdx == N - 1 && subIdx == 1))) {
                    if (subIdx == 0) {
                        arr[currentIdx - 1][1] -= 1;
                    } else {
                        arr[currentIdx + 1][0] -= 1;
                    }
                }
                if (arr[currentIdx][subIdx] > 0) {
                    queue.add(new int[] {currentIdx, subIdx, depth + 1});
                }
            }
        }

        System.out.println(result);

        /**
         * 각 샘터를 기준으로 왼쪽과의 oooooo | oo | ooooooooo
         * 100,000,000 | 2 | 99,999,997
         *
         * 각 쉼터를 기준으로 왼쪽 오른쪽의 개수를 구한다?
         * 첫 쉼터 -> 100,000,000 , 2
         * 두번째 쉼터 -> 2, 99,999,997
         *
         * 첫 쉼터 왼쪽[0], 마지막 쉼터 오른쪽[1] 선택시를 제외하면 [0] 선택시 이전거 [1] , [1] 선택시 다음꺼 [0] 을 같이 빼줘야함
         */


    }
}