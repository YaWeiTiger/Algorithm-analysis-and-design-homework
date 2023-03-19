package lab1;
public class TSPProblem {

    // 代价矩阵
    static int[][] costMatrix = {{Integer.MAX_VALUE, 3, 6, 7},
            {12, Integer.MAX_VALUE, 2, 8},
            {8, 6, Integer.MAX_VALUE, 2},
            {3, 7, 6, Integer.MAX_VALUE}};
    // 记录已经遍历过的城市
    static boolean[] visited;
    // 当前已经遍历的路径
    static int[] path;
    // 当前已经遍历的路径长度
    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) {
        visited = new boolean[costMatrix.length];
        path = new int[costMatrix.length];
        path[0] = 0;
        visited[0] = true;
        tsp(1, 0, costMatrix.length);
        System.out.println("minimum cost：" + minCost);
    }
    public static void tsp(int depth, int cost, int length) {
        if (depth == length) {
            // 如果已经遍历了所有城市，更新最小路径长度
            minCost = Math.min(minCost, cost + costMatrix[path[length - 1]][0]);
            return;
        }
        for (int i = 1; i < length; i++) {
            if (!visited[i]) {
                // 如果城市还没有被遍历，尝试将其加入路径中
                visited[i] = true;
                path[depth] = i;
                tsp(depth + 1, cost + costMatrix[path[depth - 1]][i], length);
                // 回溯，撤销该城市的遍历状态
                visited[i] = false;
            }
        }
    }
}

