import java.util.*;

public class MostReliablePath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());
        String[] path = scanner.nextLine().split("\\s+");
        int source = Integer.parseInt(path[0]);
        int destination = Integer.parseInt(path[1]);

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            graph[tokens[1]][tokens[0]] = tokens[2];
        }

        int[] distance = new int[nodes];
        boolean[] visited = new boolean[nodes];
        int[] prev = new int[nodes];
        Arrays.fill(prev, -1);

        distance[source] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((f,s) -> Double.compare(distance[s],distance[f]));
        queue.offer(source);

        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;

            for (int i = 0; i < graph[minNode].length; i++) {
                int weight = graph[minNode][i];
                if (weight != 0 && !visited[i]) {
                    int newDistance = distance[minNode] + weight;
                    if (newDistance > distance[i]) {
                        distance[i] = newDistance;
                        prev[i] = minNode;
                    }
                    queue.offer(i);
                }
            }
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(destination);
        int node = prev[destination];

        while (node != -1) {
            stack.push(node);
            node = prev[node];
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (stack.size() > 0) {
                System.out.print(" ");
            }
        }
        System.out.println(distance[destination]);
    }
}
