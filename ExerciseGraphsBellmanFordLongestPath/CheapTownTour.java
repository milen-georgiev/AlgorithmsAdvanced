import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CheapTownTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split(" - "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = tokens[0];
            int destination = tokens[1];
            int weight = tokens[2];

            graph[source][destination] = weight;
        }
        int cost = 0;

        int[] parents = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            parents[i] = i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(n -> graph[n[0]][n[1]]));

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                if (graph[row][col] != 0) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] minEdge = queue.poll();

            int from = minEdge[0];
            int to = minEdge[1];
            int weight = graph[from][to];
            int firstRoot = findRoot(from, parents);
            int secondRoot = findRoot(to, parents);

            if (firstRoot != secondRoot) {
                parents[secondRoot] = firstRoot;
                cost += weight;
            }
        }


        System.out.println("Total cost: " + cost);
    }

    private static int findRoot(int from, int[] parents) {
        while (parents[from] != from) {
            from = parents[from];
        }
        return from;
    }
}
