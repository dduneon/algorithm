import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int[] TEST_CASES =
            {1, 60, 256, 325, 20_160, 3_456_789, 10_000_001, 1_234_567_890};

    private static final String[] TEST_CASES_RESULT = {"1 = 1", "2 * 2 * 3 * 5 = 60",
            "2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 = 256", "5 * 5 * 13 = 325",
            "2 * 2 * 2 * 2 * 2 * 2 * 3 * 3 * 5 * 7 = 20160", "3 * 7 * 97 * 1697 = 3456789",
            "11 * 909091 = 10000001", "2 * 3 * 3 * 5 * 3607 * 3803 = 1234567890"};

    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASES.length; i++) {
            System.out.println(
                    "Test Case " + (i + 1) + " = " + test(TEST_CASES[i], TEST_CASES_RESULT[i]));
        }

        System.out.printf("정답률 = %.3f%%", (correct / TEST_CASES.length * 100));
    }

    private static double correct = 0;

    private static boolean test(int input, String result) {
        if (solution(input).equals(result)) {
            correct++;
            return true;
        }

        return false;
    }

    public static String solution(int num) {
        int origin = num;
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean isDivide = false;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    num = num / i;
                    sb.append(i).append(" * ");
                    isDivide = true;
                    break;
                }
            }
            if (!isDivide) {
                sb.append(num).append(" = ").append(origin);
                break;
            }
        }
        return sb.toString();
    }

    public static String solution2(int num) {
        int origin = num;
        StringBuilder sb = new StringBuilder();
        boolean[] isNotPrime = new boolean[num + 1];
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        // 에라토스테네스의 체 알고리즘 활용 소수 구하기
        for (int i = 2; i < Math.sqrt(num) + 1; i++) {
            int j = 2;
            while (i * j <= num) {
                if (!isNotPrime[i * j])
                    isNotPrime[i * j] = true;
                j++;
            }
        }
        for (int i = 2; i < isNotPrime.length; i++) {
            if (!isNotPrime[i])
                primeNumbers.add(i);
        }

        // Source Code
        if (origin == 1)
            sb.append(origin);

        for (int prime : primeNumbers) {
            if (num == 0 || prime > num)
                break;
            while (num % prime == 0) {
                num /= prime;
                sb.append(prime);
                if (num == 1) {
                    break;
                }
                sb.append(" * ");
            }
        }
        sb.append(" = ").append(origin);

        System.out.println(sb.toString());
        return sb.toString();
    }
}
