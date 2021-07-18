import java.util.*;

public class DFS {


    public void dfs(int v, int startNode) {
        int vertics = v;
        List<List<Integer>> edges = new ArrayList<>();
        
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        
        stack.push(startNode);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            visited[node] = true;
            System.out.println(node);
            for (int child : edges.get(node)) {
                if (!visited[child]) {
                    stack.push(child);
                }
            }

        }
    }

}

class BFS {
    public void bfs(int v, int startNode) {
        int vertics = v;
        List<List<Integer>> edges = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];

        queue.add(startNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.println(node);
            visited[node] = true;
            for (int child : edges.get(node)) {
                if (!visited[child]) {
                    queue.add(child);
                }
            }
        }
    }
}

