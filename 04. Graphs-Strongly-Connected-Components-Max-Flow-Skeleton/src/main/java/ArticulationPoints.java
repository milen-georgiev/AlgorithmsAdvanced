import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    public static List<Integer>[] graph;
    public static List<Integer> points;
    public static boolean[] visited;
    public static int[] parent;
    public static int[] depths;
    public static int[] lowPoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        points = new ArrayList<>();
        visited = new boolean[graph.length];
        parent = new int[graph.length];
        depths = new int[graph.length];
        lowPoints = new int[graph.length];


        Arrays.fill(parent, -1);

        discoverArticulationPoints(0, 1);


        return points;
    }

    private static void discoverArticulationPoints(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowPoints[node] = depth;

        int children = 0;

        boolean isArticulationPoint = false;

        for (int child : graph[node]) {
            if (!visited[child]) {
                parent[child] = node;
                children++;
                discoverArticulationPoints(child, depth + 1);
                if (lowPoints[child] >= depth) {
                    isArticulationPoint = true;
                }
                lowPoints[node] = Math.min(lowPoints[node], lowPoints[child]);
            } else if (parent[node] != child) {
                lowPoints[node] = Math.min(lowPoints[node], depths[child]);
            }
        }

        if ((parent[node] == -1 && children > 1) ||
                (parent[node] != -1 && isArticulationPoint)) {
            points.add(node);
        }

    }
}
