import java.util.*;

public class VampireLabyrinth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());


        int start = scanner.nextInt();
        int end = scanner.nextInt();

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

        distance[start] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Comparator.comparingInt(n -> distance[n])
        );
        queue.offer(start);

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
        Deque<Integer> path = new ArrayDeque<>();

        path.push(end);
        int parent = prev[end];

        while (parent != -1) {
            path.push(parent);
            parent = prev[parent];
        }

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
        System.out.println();

        System.out.println(distance[end]);
    }
}
