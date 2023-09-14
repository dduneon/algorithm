import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static class Fraction {
        private int numerator; // 분자
        private int denominator; // 분모

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;

            normalize();
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }


        public void normalize() {
            int gcd = gcd(numerator, denominator);
            this.numerator = this.numerator / gcd;
            this.denominator = this.denominator / gcd;
        }

        public static int gcd(int a, int b) {
            if (a % b == 0)
                return b;
            return gcd(b, a % b);
        }

        @Override
        public String toString() {
            return (denominator < 0) ? "-" + numerator + "/" + (denominator * -1)
                    : numerator + "/" + denominator;
        }
    }

    /**
     * 소수점 값에 대해 자리 수 계산 하는 메서드. <br/>
     * ex) <br/>
     * 0.01 -> 100을 곱하기 위해 2를 return <br/>
     * 0.1 -> 10을 곱하기 위해 1을 return
     *
     *
     * @param num 소수 값
     * @return 소수점 이하 자리 수 값
     */
    // -1 < n < 1
    public static int getDecimalNumber(BigDecimal num) {
        String str = num.toPlainString();

        return (int) Math.pow(10, str.split("\\.")[1].length());
    }

    public static String solution(String input) {
        BigDecimal number = new BigDecimal(input);

        int decimalNumber = getDecimalNumber(number);

        double denominator = decimalNumber;
        int numerator = (int) (decimalNumber * number.doubleValue());
        return new Fraction(numerator, (int) denominator).toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(solution(sc.nextLine()));
    }

}
