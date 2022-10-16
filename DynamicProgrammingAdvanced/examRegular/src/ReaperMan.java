import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ReaperMan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());
        String[] path = scanner.nextLine().split("\\s+");
        int source = Integer.parseInt(path[0]);
        int destination = Integer.parseInt(path[1]);

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();

            graph[from][to] = weight;
            graph[to][from] = weight;
        }

        int[] distance = new int[nodes];
        int[] prev = new int[nodes];
        boolean[] visited = new boolean[nodes];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        distance[source] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Comparator.comparingInt(n -> distance[n])
        );
        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;
            int[] nextNode = graph[parent];

            for (int child = 0; child < nextNode.length; child++) {
                if (nextNode[child] != 0 && !visited[child]) {
                    queue.offer(child);

                    int newDistance = distance[parent] + nextNode[child];

                    if (newDistance < distance[child]) {
                        distance[child] = newDistance;
                        prev[child] = parent;

                    }

                }
            }

        }
        Deque<Integer> paths = new ArrayDeque<>();

        paths.push(destination);
        int parent = prev[destination];

        while (parent != -1) {
            paths.push(parent);
            parent = prev[parent];
        }

        while (!paths.isEmpty()) {
            System.out.print(paths.pop() + " ");
        }
        System.out.println();

        System.out.println(distance[destination]);
    }
}
