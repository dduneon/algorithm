import java.util.Arrays;

public class Main {

    private static final int[] TEST_CASES = {4, 5, 10};

    private static final int[][][] TEST_CASES_RESULT =
            {{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}},
                    {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8},
                            {13, 12, 11, 10, 9}},
                    {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {36, 37, 38, 39, 40, 41, 42, 43, 44, 11},
                            {35, 64, 65, 66, 67, 68, 69, 70, 45, 12},
                            {34, 63, 84, 85, 86, 87, 88, 71, 46, 13},
                            {33, 62, 83, 96, 97, 98, 89, 72, 47, 14},
                            {32, 61, 82, 95, 100, 99, 90, 73, 48, 15},
                            {31, 60, 81, 94, 93, 92, 91, 74, 49, 16},
                            {30, 59, 80, 79, 78, 77, 76, 75, 50, 17},
                            {29, 58, 57, 56, 55, 54, 53, 52, 51, 18},
                            {28, 27, 26, 25, 24, 23, 22, 21, 20, 19}}};


    public static void main(String[] args) {
        for (int i = 0; i < TEST_CASES.length; i++) {
            System.out.println(
                    "Test Case " + (i + 1) + " = " + test(TEST_CASES[i], TEST_CASES_RESULT[i]));
        }

        System.out.printf("정답률 = %.3f%%", (correct / TEST_CASES.length * 100));
    }

    private static double correct = 0;

    private static boolean test(int input, int[][] result) {
        if (Arrays.deepEquals(solution(input), result)) {
            correct++;
            return true;
        }

        return false;
    }

    /*
     * <Answer> public static int[][] solution(int n) { int[][] answer = new int[n][n]; int count =
     * 1; int x = 0; int y = -1; int direction = 1;
     * 
     * while (n > 0) { for (int i = 0; i < n; i++) { y += direction; answer[x][y] = count++; } n--;
     * for (int i = 0; i < n; i++) { x += direction; answer[x][y] = count++; } direction *= -1; }
     * 
     * return answer; }
     */
    public static int[][] solution(int n) {
        int[][] answer = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];
        boolean isTurnWidth = true;
        int curRow = 0;
        int curCol = 0;
        int count = 1;
        boolean isPlus = true;

        while (true) {
            if (isTurnWidth) {
                if (!isVisited[curRow][curCol]) {
                    isVisited[curRow][curCol] = true;
                    answer[curRow][curCol] = count++;
                    curCol = isPlus ? curCol + 1 : curCol - 1;
                    if (isPlus) {
                        if (curCol >= n || isVisited[curRow][curCol]) {
                            isTurnWidth = !isTurnWidth;
                            curCol -= 1;
                            curRow += 1;
                        }
                    } else {
                        if (curCol < 0 || isVisited[curRow][curCol]) {
                            isTurnWidth = !isTurnWidth;
                            curCol += 1;
                            curRow -= 1;
                        }
                    }
                }
            } else {
                if (!isVisited[curRow][curCol]) {
                    isVisited[curRow][curCol] = true;
                    answer[curRow][curCol] = count++;
                    curRow = isPlus ? curRow + 1 : curRow - 1;
                    if (isPlus) {
                        if (curRow >= n || isVisited[curRow][curCol]) {
                            isTurnWidth = !isTurnWidth;
                            curRow -= 1;
                            curCol -= 1;
                            isPlus = !isPlus;
                        }
                    } else {
                        if (curRow < 0 || isVisited[curRow][curCol]) {
                            isTurnWidth = !isTurnWidth;
                            curRow += 1;
                            curCol += 1;
                            isPlus = !isPlus;
                        }
                    }
                }
            }
            if (count == n * n + 1)
                break;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

        return answer;
    }
}
