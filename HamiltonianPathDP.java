
// Reference: 
// - https://www.geeksforgeeks.org/hamiltonian-path-using-dynamic-programming/
// - https://www.hackerearth.com/practice/algorithms/graphs/hamiltonian-path/tutorial/
public class HamiltonianPathDP {
    private int V;
    private int[][] dp;
    private int[][] nextVertex;
    private int[][] graph;

    public HamiltonianPathDP(int graph[][]) {
        V = graph.length;
        this.graph = graph;
        dp = new int[(1 << V)][V];
        nextVertex = new int[(1 << V)][V];
        for (int i = 0; i < (1 << V); i++) {
            for (int j = 0; j < V; j++) {
                dp[i][j] = -1;
                nextVertex[i][j] = -1;
            }
        }
    }

    private int countHamiltonianPaths(int mask, int pos) {
        if (mask == (1 << V) - 1) {
            return 1;
        }
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int ans = 0;
        for (int v = 0; v < V; v++) {
            if ((mask & (1 << v)) == 0 && graph[pos][v] == 1) {
                int pathsFromVertex = countHamiltonianPaths(mask | (1 << v), v);
                if (pathsFromVertex > 0) {
                    nextVertex[mask][pos] = v;
                }
                ans += pathsFromVertex;
            }
        }

        dp[mask][pos] = ans;
        return ans;
    }

    private boolean printHamiltonianPath(int start) {
        int mask = 1 << start;
        int pos = start;
        System.out.print("Hamiltonian Path DP: " + start);

        while (true) {
            pos = nextVertex[mask][pos];
            if (pos == -1) {
                System.out.println();
                return false;
            }
            System.out.print(" -> " + pos);
            mask |= (1 << pos);
            if (mask == (1 << V) - 1) {
                System.out.println();
                return true;
            }
        }
    }

    public void findHamiltonianPaths() {
        for (int i = 0; i < V; i++) {
            if (countHamiltonianPaths(1 << i, i) > 0) {
                if (printHamiltonianPath(i)) {
                    return;
                }
            }
        }
        System.out.println("Solution does not exist");
    }

    public static void main(String args[]) {
        int graph[][] = { { 0, 1, 1, 1, 0 },
                { 1, 0, 1, 0, 0 },
                { 1, 1, 0, 0, 1 },
                { 1, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0 } };
        HamiltonianPathDP hamiltonian = new HamiltonianPathDP(graph);
        hamiltonian.findHamiltonianPaths();

    }
}
