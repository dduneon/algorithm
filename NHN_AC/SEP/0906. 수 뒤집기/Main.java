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
        // Solve by StringBuilder
        // return reverse(reverse(a) + reverse(b));

        // Solve by Recursive
        // int num1 = Integer.parseInt(recurReverse(a, 0));
        // int num2 = Integer.parseInt(recurReverse(b, 0));
        // return Integer.parseInt(recurReverse(num1 + num2, 0));

        // Solve by Recursive Int
        int n1 = recurReverseInt(a, digit(a), 1);
        int n2 = recurReverseInt(b, digit(b), 1);
        int tmp = n1 + n2;
        // System.out.println(n1 + " " + n2);
        // System.out.println(tmp);
        return recurReverseInt(tmp, digit(tmp), 1);
    }

    // Using StringBuilder
    public static int reverse(int num) {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(num);
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return Integer.parseInt(sb.toString());
    }

    // Using Recursive Function
    public static String recurReverse(int num, int at) {
        String str = String.valueOf(num);
        if (at == str.length() - 1)
            return Character.toString(str.charAt(at));
        return recurReverse(num, at + 1) + Character.toString(str.charAt(at));
    }

    // Using Recursive Function for int
    public static int recurReverseInt(int num, int digit1, int digit2) {
        if (digit1 == 1) {
            return ((num / digit1) % 10) * digit2;
        }
        return (((num / digit1) % 10) * digit2) + recurReverseInt(num, digit1 / 10, digit2 * 10);
    }

    public static int digit(int num) {
        int digit = 1;
        while (num / 10 >= 1) {
            digit++;
            num = num / 10;
        }
        return (int) Math.pow(10, digit - 1);
    }

}
