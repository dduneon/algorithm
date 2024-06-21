import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방의 개수 N
        int N = Integer.parseInt(st.nextToken());

        // 용사의 초기 공격력 Ha
        long Ha = Integer.parseInt(st.nextToken());

        // 용사가 누적 피해량
        long attacked = 0;
        // 결과
        long maxHp = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // t == 1이면 몬스터가 있음, t==2이면 포션이 있음
            int t = Integer.parseInt(st.nextToken());
            // 몬스터의 공격력 혹은 포션의 공격력 증가량
            int a = Integer.parseInt(st.nextToken());
            // 몬스터의 생명력 혹은 포션의 생명력 회복량
            int h = Integer.parseInt(st.nextToken());

            if (t == 1) {
                // 해당 몬스터를 몇번 때려야 죽는지?
                long count = (h / Ha) + (h % Ha > 0 ? 1 : 0);

                // 용사는 count-1 번 몬스터에게 맞음
                long damage = a * (count - 1);
                attacked += damage;
                maxHp = Math.max(maxHp, attacked + 1);
            } else {
                Ha += a;
                attacked -= h;
                if (attacked < 0) {
                    attacked = 0;
                }
            }
        }

        System.out.println(maxHp);
    }
}