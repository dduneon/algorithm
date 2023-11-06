package solving;

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= N; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String numberPaneString = st.nextToken();
            List<Integer> numberPane = new ArrayList<>();
            for(int i=0; i<numberPaneString; i++)
                numberPane.add(Character.getNumericValue(numberPaneString.charAt(i)));
            int changeNumber = Integer.parseInt(st.nextToken());
            System.out.println("#" + test_case + " " + solution(numberPane, changeNumber);
        }
    }

    public static int solution(List<Integer> numberPane, int changeNumber) {
        /**
         * 1. 가장 작은 수를 맨 뒤로 보낸다 (N-1) 1-1. 이미 그 위치에 있다면 다음 작은 수를 선택해야 한다 2. 모두 제자리에 존재한다면 2-1. 짝수이면
         * 그냥 리턴 2-2. 홀수이면 맨 마지막 두자리 바꿔서 리턴
         */
        List<Integer> sortedNumber = new ArrayList<>();
        sortedNumber = numberPane.sort(Comparator.reverseOrder());

        int  i=changeN u mber; i> 0 ; i++) {


            }
        }
}}
