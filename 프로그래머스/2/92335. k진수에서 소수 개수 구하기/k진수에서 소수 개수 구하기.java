import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        int divided = n;
        StringBuilder sb = new StringBuilder();
        while(divided != 0) {
            int rest = divided % k;
            sb.append(rest);
            divided = divided / k;
        }
        String parseDigit = sb.reverse().toString();
        String[] splitDigit = parseDigit.split("0");
        
        for(String split: splitDigit) {
            if(split.equals(""))
                continue;
            if(isPrime(Long.parseLong(split)))
                answer++;
        }
        return answer;
    }
    public boolean isPrime(long num) {
        if (num <= 1)	return false;
        for(int i=2; i<=Math.sqrt(num); i++) {
        	if (num % i == 0)
                return false;
        }
        return true;
    }
}