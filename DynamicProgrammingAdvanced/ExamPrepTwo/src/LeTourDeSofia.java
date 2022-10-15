import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class LeTourDeSofia {

    public static boolean[][] graph;
    public static boolean[] visited;
    public static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(scanner.readLine());
        int edges = Integer.parseInt(scanner.readLine());
        int source = Integer.parseInt(scanner.readLine());

        graph = new boolean[nodes][nodes];
        visited = new boolean[nodes];
        distances = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.readLine() .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph[tokens[0]][tokens[1]] = true;
        }

        bfs(source);

        if (distances[source] != 0){
            System.out.println(distances[source]);
        } else {
            int visitedNodes = 0;
            for (boolean node : visited) {
                if (node){
                    visitedNodes++;
                }
            }
            System.out.println(visitedNodes);
        }
    }

    private static void bfs(int source) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;
        distances[source] = 0;
        while (!queue.isEmpty()) {
            int parrent = queue.poll();
            for (int child = 0; child < graph[parrent].length; child++) {
                if (!visited[child] && graph[parrent][child]) {
                    visited[child] = true;
                    queue.offer(child);
                    distances[child] = distances[parrent] + 1;
                } else if (graph[parrent][child] && child == source && distances[child] == 0) {
                    distances[child] = distances[parrent] + 1;
                    return;
                }
            }
        }
    }

}
