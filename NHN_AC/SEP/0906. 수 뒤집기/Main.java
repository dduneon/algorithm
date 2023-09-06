public class Main {

    private static double correctCount = 0;

    private static final String[][] TESTCASES =
            new String[][] {{"123", "123", "246"}, {"1000", "1", "2"}, {"456", "789", "1461"},
                    {"5", "5", "1"}, {"11112", "54321", "65433"}, {"3829", "1300", "4139"}};

    public static void main(String[] args) {
        for (int i = 0; i < TESTCASES.length; i++)
            System.out.println("Testcase " + i + " = "
                    + test(TESTCASES[i][0], TESTCASES[i][1], TESTCASES[i][2]));

        System.out.println("정답률 = " + (int) (correctCount / TESTCASES.length * 100) + "%");
    }

    private static boolean test(String input, String input2, String answer) {
        int first = Integer.parseInt(input);
        int second = Integer.parseInt(input2);

        boolean res = String.valueOf(solution(first, second)).equals(answer);
        if (res)
            correctCount++;
        return res;
    }

    // solution
    public static int solution(int a, int b) {
        String num1 = String.valueOf(a);
        String num2 = String.valueOf(b);
        String resultStr;
        int result;


        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            sb.append(num1.charAt(i));
        }
        result = Integer.parseInt(sb.toString());

        sb = new StringBuilder();
        for (int i = num2.length() - 1; i >= 0; i--) {
            sb.append(num2.charAt(i));
        }
        result += Integer.parseInt(sb.toString());

        resultStr = String.valueOf(result);
        sb = new StringBuilder();
        for (int i = resultStr.length() - 1; i >= 0; i--) {
            sb.append(resultStr.charAt(i));
        }

        result = Integer.parseInt(sb.toString());
        return result;
    }
}
