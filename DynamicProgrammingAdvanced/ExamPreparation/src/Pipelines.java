import java.util.*;

public class Pipelines {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int agents = Integer.parseInt(scanner.nextLine());
        int pipelines = Integer.parseInt(scanner.nextLine());

        String[] agentName = new String[agents];
        String[] pipelinesName = new String[pipelines];

        Map<String, Integer> agentMap = new HashMap<>();
        Map<String, Integer> pipelinesMap = new HashMap<>();

        for (int i = 0; i < agents; i++) {
            String agent = scanner.nextLine();
            agentName[i] = agent;
            agentMap.put(agent,i);
        }

        for (int i = 0; i < pipelines; i++) {
            String pipeline = scanner.nextLine();
            pipelinesName[i] = pipeline;
            pipelinesMap.put(pipeline,i);
        }

        int nodes = agents + pipelines + 2;

        int[][] graph = new int[nodes][nodes];


        int start = agents + pipelines;

        for (int i = 0; i < agents; i++) {
            int weight = 1;
            graph[start][i] = weight;
        }

        for (int i = 0; i < agents; i++) {
            String[] data = scanner.nextLine().split(", ");
            String code =data[0];
            int agent = agentMap.get(code);
            for (int j = 1; j < data.length; j++) {
                String pipelineName = data[j];
                int pipeline = pipelinesMap.get(pipelineName);
                graph[agent][agents + pipeline] = 1;
            }
        }

        for (int i = 0; i < pipelines; i++) {
            graph[agents + i][graph.length - 1] = 1;
        }

        int[] path = new int[graph.length];

        while (bfs(graph,path,start)) {
            int endNode = graph.length - 1;
            while (path[endNode] != -1) {

                int source = path[endNode];

                graph[source][endNode] = 0;
                graph[endNode][source] = 1;

                endNode = path[endNode];
            }
        }

        for (int i = 0; i < agents; i++) {
            for (int j = 0; j < pipelines; j++) {
                if (graph[agents+j][i] !=0){
                    System.out.println(agentName[i] + " - " + pipelinesName[j]);
                }
            }
        }

    }

    private static boolean bfs(int[][] graph, int[] path, int start) {
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(path, -1);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(start);
        visited[start] = true;

        while (!deque.isEmpty()) {
            int node = deque.poll();

            for (int i = 0; i < graph.length; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    deque.offer(i);
                    visited[i] = true;
                    path[i] = node;
                }
            }
        }

        return visited[graph.length -1];
    }
}
