
// Reference:
// - https://www.geeksforgeeks.org/hamiltonian-cycle/
public class HamiltonianPathBacktracking {
    private int V;
    private int path[];

    public HamiltonianPathBacktracking(int V) {
        this.V = V;
        this.path = new int[V];
    }

    boolean isSafe(int v, int graph[][], int path[], int pos) {
        if (graph[path[pos - 1]][v] == 0)
            return false;

        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    boolean hamiltonianPathUtil(int graph[][], int path[], int pos) {
        if (pos == V) {
            return true;
        }

        for (int v = 0; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                if (hamiltonianPathUtil(graph, path, pos + 1))
                    return true;
                path[pos] = -1; // Backtracking
            }
        }

        return false;
    }

    int[] hamiltonianPath(int graph[][]) {
        for (int start = 0; start < V; start++) {
            for (int i = 0; i < V; i++)
                path[i] = -1;

            path[0] = start;

            if (hamiltonianPathUtil(graph, path, 1)) {
                return path;
            }
        }

        System.out.println("Solution does not exist");
        return null;
    }

    public static void main(String args[]) {
        HamiltonianPathBacktracking hamiltonian = new HamiltonianPathBacktracking(5);
        int graph1[][] = { { 0, 1, 1, 1, 0 },
                { 1, 0, 1, 0, 0 },
                { 1, 1, 0, 0, 1 },
                { 1, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0 } };

        int[] path = hamiltonian.hamiltonianPath(graph1);
        if (path != null) {
            for (int v : path) {
                if (v != path.length - 1)
                    System.out.print(v + " -> ");
                else
                    System.out.print(v);
            }
            System.out.println();
        }
    }
}
