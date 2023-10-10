import java.util.stream.*;
import java.util.*;

class Solution {
    // return 형식은 번호의 배열, 1~
    // @param terms 공백 하나로 구분한 문자열, "약관종류 유효기간"
    // @param privacies 공백 하나로 구분, "날짜 약관 종류"
    //        약관 종류, "."으로 구분 YYYY.MM.DD
    private final int DAYSINMONTH = 28;
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        String[][] splitTerms = splitArray(terms);
        String[][] splitPrivacies = splitArray(privacies);
        
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=0; i<splitPrivacies.length; i++) {
            for(int j=0; j<splitTerms.length; j++) {
            	if(splitPrivacies[i][1].equals(splitTerms[j][0])) {
                    if((daysFromFirst(splitPrivacies[i][0]) + Integer.parseInt(splitTerms[j][1]) * 28) <= daysFromFirst(today))
                    	tmp.add(i+1);
                }
            }
        }
        return tmp.stream()
            .mapToInt(i -> i)
            .toArray();
    }
    
    public String[][] splitArray(String[] arr) {
        String[][] result = new String[arr.length][2];
        for(int i=0; i<arr.length; i++) {
            result[i] = arr[i].split(" ");
        }
        return result;
    }
    
    public int daysFromFirst(String day) {
        int[] yearMonthDay = Stream.of(day.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int MM = (yearMonthDay[0] - 2000) * 12 + yearMonthDay[1];
        int DD = (MM - 1) * DAYSINMONTH + yearMonthDay[2];
        
        return DD;
    }
}