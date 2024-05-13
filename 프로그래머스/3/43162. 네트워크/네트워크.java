class Solution {
    static boolean[] visited;
    static int count = 0;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i, computers);
                count++;   
            }
        }
        return count;
    }
    
    public void dfs(int current, int[][] computers) {
        for(int i=0; i<computers.length; i++) {
            if(!visited[i] && computers[current][i] == 1) {
        		System.out.println(current + ", " + i);
                visited[i] = true;
                dfs(i, computers);
            }
        }
    }
}