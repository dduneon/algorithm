public class Main {

    public static void main(String[] args) {
        final int[][] TEST_CASE = {{1, 1}, {98, 49}, {2, 81}, {3, 7}, {19, 87}, {1, 40}};
        final int[] TEST_CASE_RESULT = {1, 4606, 162, 21, 1653, 40};

        for (int i = 0; i < TEST_CASE_RESULT.length; i++) {
            if (solution(TEST_CASE[i][0], TEST_CASE[i][1]) == TEST_CASE_RESULT[i]) {
                System.out.println("CASE " + i + " Complete");
            }
        }
    }

    // solution
    private static int solution(int first, int second) {
        return first * second / gcd(Math.max(first, second), Math.min(first, second));
    }

    public static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }


}
