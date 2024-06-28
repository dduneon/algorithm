import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] lis = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];
        int currentLISLength = 1;

        for (int i = 1; i < N; i++) {
            int current = arr[i];
            if (lis[currentLISLength - 1] < current) {
                lis[currentLISLength] = current;
                currentLISLength++;
                continue;
            }

            int low = 0;
            int high = currentLISLength;

            while (low < high) {
                int mid = (low + high) / 2;

                if (lis[mid] < current) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            lis[low] = current;
        }

        System.out.println(currentLISLength);
    }
}