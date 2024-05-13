import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    public int[][] around = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int bfs(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        
        int[] endPos = {N-1, M-1};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        // start pos
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            if(current[0] == N-1 && current[1] == M-1) {
                return current[2];
            }
			
            for(int[] a: around) {
                int nextN = current[0] + a[0];
                int nextM = current[1] + a[1];
                
                if(nextN >= 0 && nextN < N && nextM >= 0 && nextM < M && !visited[nextN][nextM] && maps[nextN][nextM] == 1) {
                    queue.add(new int[]{nextN, nextM, current[2] + 1});
                    visited[nextN][nextM] = true;
                }
            }
        }
        
        return -1;
    }
}