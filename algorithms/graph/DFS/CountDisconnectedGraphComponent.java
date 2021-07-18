import java.util.*;

class FindDisconnectedGraphComponents {
    int vertics;
    List<List<Integer>> edges = new ArrayList<>();

    public void solve() {
        boolean[] visited = new boolean[vertics];
        int count = 0;

        int[] component = new int[vertics]; // vertics as index and group number as value

        for (int i=0; i<vertics; i++) {
            if (!visited[i]) {
                dfs(visited, count, i, component);
                count++;
            }
        }
    }

    public void dfs(boolean visited[], int count, int startNode, int[] component) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while(!stack.isEmpty()) {
            int node = stack.pop();
            visited[node] = true;
            component[node] = count;

            for (int child : edges.get(node)) {
                if (!visited[child]) {
                    stack.push(child);
                }
            }
        }
    }
}
