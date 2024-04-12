import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        for(int i=0; i<testcase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] applicant = new int[M][2];
            Set<Integer> p = new HashSet<>();
            for(int j=0; j<M; j++) {
                st = new StringTokenizer(br.readLine());
                applicant[j][0] = Integer.parseInt(st.nextToken());
                applicant[j][1] = Integer.parseInt(st.nextToken());
                p.add(j);
            }

            Arrays.sort(applicant, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return (o1[1]==o2[1]) ? o2[0]-o1[0] : o1[1]-o2[1];
                }
            });

            for(int n=1; n<=N; n++) {
                for(int m=0; m<M; m++) {
                    if(p.contains(m)) {
                        int start = applicant[m][0];
                        int end = applicant[m][1];
                        if(start <= n && end >= n) {
                            p.remove(m);
                            break;
                        }
                    }
                }
            }
            System.out.println(M-p.size());
        }
    }
}