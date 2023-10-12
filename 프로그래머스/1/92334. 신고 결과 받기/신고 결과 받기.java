import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        Map<String, Integer> userInfo = new HashMap<>();
        Map<Integer, Set<Integer>> userReport = new HashMap<Integer, Set<Integer>>();
        for(int i=0; i<id_list.length; i++) {
            userInfo.put(id_list[i], i);
            userReport.put(i, new HashSet<Integer>());
        }
        
        for(String rep: report) {
            String[] splitWords = rep.split(" ");
            userReport.get(userInfo.get(splitWords[0])).add(userInfo.get(splitWords[1]));
        }
        
        int[] repNumber = new int[id_list.length];
        for(int i=0; i<id_list.length; i++) {
            for(int repUserNumber: userReport.get(i)) {
                repNumber[repUserNumber]++;
            }
        }
        
        Set<Integer> closedUser = new HashSet<>();
        for(int i=0; i<repNumber.length; i++) {
            if (repNumber[i] >= k)
                closedUser.add(i);
        }
        
        answer = new int[id_list.length];
        for(int i=0; i<id_list.length; i++) {
            for(int rep: userReport.get(i)) {
        		if(closedUser.contains(rep))
            		answer[i]++;
            }
        }
        
        return answer;
    }
}