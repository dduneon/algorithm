import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder maxResult = new StringBuilder();
        StringBuilder minResult = new StringBuilder();
        Set<Integer> maxRest = new HashSet<>();
        Set<Integer> minRest = new HashSet<>();
        for(int i=0; i<10; i++) {
            maxRest.add(i);
            minRest.add(i);
        }

        int N = Integer.parseInt(br.readLine());
        String[] sign = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            int currentMax = Collections.max(maxRest);
            int currentMin = Collections.min(minRest);
            if(sign[i].equals("<")) {
                int count = 0;
                for(int j=i; j<N; j++) {
                    if(!sign[j].equals("<"))
                        break;
                    count++;
                }
                maxResult.append(currentMax-count);
                maxRest.remove(currentMax-count);
                minResult.append(currentMin);
                minRest.remove(currentMin);
            } else {
                int count = 0;
                for(int j=i; j<N; j++) {
                    if(!sign[j].equals(">"))
                        break;
                    count++;
                }
                maxResult.append(currentMax);
                maxRest.remove(currentMax);
                minResult.append(currentMin+count);
                minRest.remove(currentMin+count);
            }
        }

        maxResult.append(Collections.max(maxRest));
        minResult.append(Collections.min(minRest));
        System.out.println(maxResult.toString());
        System.out.println(minResult.toString());
    }
}