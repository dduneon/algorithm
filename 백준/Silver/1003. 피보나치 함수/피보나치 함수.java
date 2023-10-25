import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(br.readLine());
        List<Fibo> dp = new ArrayList<>();
        dp.add(new Fibo(1, 0));
        dp.add(new Fibo(0, 1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTestCases; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n < dp.size())
                sb.append(dp.get(n).numberOfZero).append(" ").append(dp.get(n).numberOfOne)
                        .append('\n');
            else {
                int currentSize = dp.size();
                for (int index = currentSize; index <= n; index++) {
                    Fibo past2Node = dp.get(index - 2);
                    Fibo past1Node = dp.get(index - 1);
                    dp.add(new Fibo(past2Node.numberOfZero + past1Node.numberOfZero,
                            past2Node.numberOfOne + past1Node.numberOfOne));
                }
                sb.append(dp.get(n).numberOfZero).append(" ").append(dp.get(n).numberOfOne)
                        .append('\n');
            }
        }
        System.out.println(sb.toString());
    }
}


class Fibo {
    int numberOfZero;
    int numberOfOne;

    public Fibo(int numberOfZero, int numberOfOne) {
        this.numberOfZero = numberOfZero;
        this.numberOfOne = numberOfOne;
    }
}
