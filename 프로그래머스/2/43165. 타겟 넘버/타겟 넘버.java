class Solution {
    static boolean[] isPlus;
    static int length;
    static int count = 0;
    
    public int solution(int[] numbers, int target) {
       isPlus = new boolean[numbers.length];
       dfs(0, numbers, target);
       return count;
    }
    
    public void dfs(int current, int[] numbers, int target) {
        if(current == numbers.length) {
            int result = 0;
            for(int i=0; i<numbers.length; i++) {
                result += numbers[i] * (isPlus[i] ? 1 : -1);
            }
            if(result == target) {
                count++;
            }
            return;
        }
        isPlus[current] = true;
        dfs(current+1, numbers, target);
        isPlus[current] = false;
        dfs(current+1, numbers, target);
    }
}