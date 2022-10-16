import java.util.*;

public class TrainsPartThree {
    public static int[][] graph;
    public static int[] parent;
    public static Queue<Integer> queue = new LinkedList<>();
    public static int numberOfVertices;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());
        String[] path = scanner.nextLine().split("\\s+");
        int source = Integer.parseInt(path[0]);
        int destination = Integer.parseInt(path[1]);

        graph = new int[nodes][nodes];
        visited = new boolean[nodes];
        parent = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();

            graph[from][to] = weight;
            graph[to][from] = weight;
        }

        int result = findMaxFlow(graph, source, destination);

        System.out.println(result);


    }

    private static int findMaxFlow(int[][] graph, int source, int destination) {
        int u, v;

        int maxFlow = 0;

        int pathFlow;


        int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];

        for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++) {

            for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++) {

                residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex][destinationVertex];

            }

        }


        while (bfs(source, destination, residualGraph)) {

            pathFlow = Integer.MAX_VALUE;

            for (v = destination; v != source; v = parent[v]) {

                u = parent[v];

                pathFlow = Math.min(pathFlow, residualGraph[u][v]);

            }

            for (v = destination; v != source; v = parent[v]) {

                u = parent[v];

                residualGraph[u][v] -= pathFlow;

                residualGraph[v][u] += pathFlow;

            }

            maxFlow += pathFlow;

        }


        return maxFlow;

    }


    public static boolean bfs(int source, int goal, int graph[][]) {

        boolean pathFound = false;

        int destination, element;


        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {

            parent[vertex] = -1;

            visited[vertex] = false;

        }


        queue.add(source);

        parent[source] = -1;

        visited[source] = true;


        while (!queue.isEmpty()) {

            element = queue.remove();

            destination = 1;


            while (destination <= numberOfVertices) {

                if (graph[element][destination] > 0 && !visited[destination]) {

                    parent[destination] = element;

                    queue.add(destination);

                    visited[destination] = true;

                }

                destination++;

            }

        }

        if (visited[goal]) {

            pathFound = true;

        }

        return pathFound;

    }


}
