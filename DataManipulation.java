import java.util.Random;

public class DataManipulation {
    private static Random random = new Random();

    public static int[][] generateGraph(int V) {
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                graph[i][j] = graph[j][i] = random.nextInt(2); // 0 or 1
            }
        }
        return graph;
    }

    public static void measureBacktracking(int size, int[][] graph) {
        long startTime = System.currentTimeMillis();
        long beforeUsedMem = memoryUsage();

        HamiltonianPathBacktracking hamiltonianBT = new HamiltonianPathBacktracking(size);
        System.out.println();
        int[] path = hamiltonianBT.hamiltonianPath(graph);
        if (path != null) {
            int i = 0;
            System.out.print("Hamiltonian Path BT: ");
            for (int v : path) {
                if (i != path.length - 1)
                    System.out.print(v + " -> ");
                else
                    System.out.print(v);
                i++;
            }
            System.out.println();
        }

        long afterUsedMem = memoryUsage();
        long endTime = System.currentTimeMillis();

        long memoryUsage = Math.abs(afterUsedMem - beforeUsedMem);
        double timeBT = (endTime - startTime) / 1000.0;

        System.out.println(
                "Backtracking - Size: " + size + ", Time: " + String.format("%.6f", timeBT) + " s, Memory: "
                        + memoryUsage + " bytes");
    }

    public static void measureDP(int size, int[][] graph) {
        long startTime = System.currentTimeMillis();
        long beforeUsedMem = memoryUsage();

        HamiltonianPathDP hamiltonianDP = new HamiltonianPathDP(graph);
        hamiltonianDP.findHamiltonianPaths();

        long afterUsedMem = memoryUsage();
        long endTime = System.currentTimeMillis();

        long memoryUsage = Math.abs(afterUsedMem - beforeUsedMem);
        double timeDP = (endTime - startTime) / 1000.0;
        System.out.print(
                "DP - Size: " + size + ", Time: " + String.format("%.6f", timeDP) + " s, Memory: " + memoryUsage
                        + " bytes");
    }

    private static long memoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
