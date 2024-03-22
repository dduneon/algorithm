import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] meeting = new int[n][2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken());
            meeting[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(meeting, (o1, o2) -> o1[1]!=o2[1] ? o1[1]-o2[1] : o1[0]-o2[0]);

        int meetingEndTime = 0;
        int count = 0;
        for(int i=0; i<n; i++) {
            if(meeting[i][0] >= meetingEndTime) {
                meetingEndTime = meeting[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}