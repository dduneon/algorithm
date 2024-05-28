import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Student {
        int min;
        int max;

        public Student(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(input.readLine());
        StringTokenizer line;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            line = new StringTokenizer(input.readLine());
            int bookNum = Integer.parseInt(line.nextToken());
            int studentNum = Integer.parseInt(line.nextToken());
            PriorityQueue<Student> students = new PriorityQueue<>(
                    (s1, s2) -> s1.max == s2.max
                            ? s1.min - s2.min
                            : s1.max - s2.max);
            for (int j = 0; j < studentNum; j++) {
                line = new StringTokenizer(input.readLine());
                int min = Integer.parseInt(line.nextToken());
                int max = Integer.parseInt(line.nextToken());
                students.add(new Student(min, max));
            }

            int count = 0;

            boolean[] books = new boolean[bookNum + 1];
            while (!students.isEmpty()) {
                Student current = students.poll();
                for (int s = current.min; s <= current.max; s++) {
                    if (!books[s]) {
                        count++;
                        books[s] = true;
                        break;
                    }
                }
            }

            result.append(count).append("\n");
        }

        System.out.print(result);
    }
}