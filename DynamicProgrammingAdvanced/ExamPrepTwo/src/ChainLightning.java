import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChainLightning {

    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static List<Edge> graph;
    public static boolean[] visited;
    public static int[] damage;
    public static Map<Integer,List<Integer>> forest = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(scanner.readLine());
        int edges = Integer.parseInt(scanner.readLine());
        int[] hitsCount = Arrays.stream(scanner.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        graph = new ArrayList<>(nodes);
        visited = new boolean[nodes];
        damage = new int[nodes];



        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            Edge edge = new Edge(from, to,weight);

            graph.add(edge);
        }


        for (int i = 0; i < nodes; i++) {
            msf(i);
        }



    }



    private static void msf(int node) {
        PriorityQueue<Edge> queue =  new PriorityQueue<>();
                visitNodes(node, queue);

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();

            int from = minEdge.from;
            int to = minEdge.to;

            if (visited[from] && visited[to]) {
                continue;
            }

            forest.putIfAbsent(from, new ArrayList<>());
            forest.putIfAbsent(to, new ArrayList<>());

            forest.get(from).add(to);
            forest.get(to).add(from);

            if (!visited[from]) {
                visitNodes(from,queue);
            } else {
                visitNodes(to, queue);
            }


        }

    }

    private static void visitNodes(int node, PriorityQueue<Edge> queue) {
        visited[node] = true;

        for (Edge edge : graph) {
            int nextNode = node == edge.from ? edge.to : edge.from;
            if(!visited[nextNode]) {
                queue.offer(edge);
            }
        }
    }
}
