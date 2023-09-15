public class Main {

    public static void main(String[] args) {
        System.out.println(factorial(0));
        System.out.println(factorial(5));
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(10));
    }

    public static long factorial(int number) {
        if (number < 0)
            throw new IllegalArgumentException("Input Negative Number Error");

        switch (number) {
            case 0:
                return 1;
            default:
                return number * factorial(number - 1);
        }
    }

    public static long fibonacci(int number) {
        switch (number) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 1;
            default:
                return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }
}
