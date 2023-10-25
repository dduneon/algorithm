import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> map = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfComputers = Integer.parseInt(br.readLine());
        for (int i = 0; i <= numberOfComputers; i++) {
            map.add(new LinkedList<>());
        }
        boolean[] visited = new boolean[numberOfComputers + 1];
        int numberOfEdges = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfEdges; i++) {
            String[] splitStr = br.readLine().split(" ");
            int computer1 = Integer.parseInt(splitStr[0]);
            int computer2 = Integer.parseInt(splitStr[1]);
            map.get(computer1).add(computer2);
            map.get(computer2).add(computer1);
        }
        dfs(1, map, visited);
        System.out.println(result);
    }

    static int result = 0;

    public static void dfs(int currentNode, List<List<Integer>> map, boolean[] visited) {
        if (visited[currentNode])
            return;
        List<Integer> linkedNodes = map.get(currentNode);
        visited[currentNode] = true;
        result = currentNode == 1 ? result : result + 1;

        while (!linkedNodes.isEmpty()) {
            int linkedNode = linkedNodes.remove(0);
            if (!visited[linkedNode])
                dfs(linkedNode, map, visited);
        }
    }
}
