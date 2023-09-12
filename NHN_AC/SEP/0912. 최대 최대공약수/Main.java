public class Main {

    public static void main(String[] args) {
        final int[][] TEST_CASE = {{10, 20, 30, 40}, {5, 7, 12}};
        final int[] TEST_CASE_RESULT = {20, 1};

        for (int i = 0; i < TEST_CASE_RESULT.length; i++) {
            if (solution(TEST_CASE[i]) == TEST_CASE_RESULT[i]) {
                System.out.println("CASE " + i + " Complete");
            }
        }

    }

    public static int solution(int[] input) {
        int max = 0;
        // if input length is 4?
        // 0 1, 0 2, 0 3, 1 2, 1 3, 2 3
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (gcd(Math.max(input[i], input[j]), Math.min(input[i], input[j])) > max) {
                    max = gcd(Math.max(input[i], input[j]), Math.min(input[i], input[j]));
                }
            }
        }
        return max;
    }

    // a > b
    public static int gcd(int a, int b) {
        int r = a % b;
        if (r == 0)
            return b;
        return gcd(b, r);
    }
}
