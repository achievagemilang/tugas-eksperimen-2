public class Main {
    public static void main(String args[]) {
        int[] sizes = { 16, 18, 20 };
        for (int size : sizes) {
            System.out.println();

            int[][] graph = DataManipulation.generateGraph(size);

            DataManipulation.measureBacktracking(size, graph);
            DataManipulation.measureDP(size, graph);
        }
    }

}
