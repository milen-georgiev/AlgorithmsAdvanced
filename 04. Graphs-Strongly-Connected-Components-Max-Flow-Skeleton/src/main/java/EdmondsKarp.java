import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class EdmondsKarp {

    public static int[][] graph;
    public static int[] parent;
    public static int findMaxFlow(int[][] targetGraph) {
        graph = targetGraph;
        parent = new int[graph.length];
        Arrays.fill(parent, -1);

        int maxFlow = 0;

        while (bfs()) {
            int node = graph.length - 1;
            int flow = Integer.MAX_VALUE;

            while (node != 0) {
                flow = Math.min(flow, graph[parent[node]][node]);
                node = parent[node];
            }

            maxFlow += flow;

            node = graph.length - 1;
            while (node != 0) {
                graph[parent[node]][node] -= flow;
                graph[node][parent[node]] += flow;
                node = parent[node];
            }
        };

        return maxFlow;
    }

    private static boolean bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];

        Arrays.fill(parent, -1);

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    visited[child] = true;
                    parent[child] = node;
                    queue.offer(child);
                }
            }
        }

        return visited[visited.length - 1];
    }
}
