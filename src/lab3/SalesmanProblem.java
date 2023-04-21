package lab3;

import java.util.*;

import java.util.*;

import java.util.*;

public class SalesmanProblem {
    private static int[][] distance; // 存储城市间距离矩阵
    private static int[] path; // 存储当前路径
    private static boolean[] visited; // 存储每个城市是否被访问过
    private static int n; // 城市数量
    private static int bestDistance; // 存储最短路程
    private static int currentDistance; // 存储当前路程

    public static void main(String[] args) {
        // 读入城市间距离矩阵
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        // 初始化变量
        path = new int[n];
        visited = new boolean[n];
        path[0] = 0;
        visited[0] = true;
        bestDistance = Integer.MAX_VALUE;
        currentDistance = 0;

        // 开始搜索
        dfs(0);

        // 输出最短路程
        System.out.println(bestDistance);
    }

    // 深度优先搜索
    private static void dfs(int depth) {
        if (depth == n - 1) { // 如果已经走完了所有城市，回到起点
            currentDistance += distance[path[n - 1]][0];
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
            }
            currentDistance -= distance[path[n - 1]][0];
            return;
        }

        // 如果还有城市没有走过
        for (int i = 1; i < n; i++) {
            if (!visited[i]) {
                path[depth + 1] = i;
                visited[i] = true;
                currentDistance += distance[path[depth]][i];
                if (currentDistance < bestDistance) {
                    dfs(depth + 1);
                }
                currentDistance -= distance[path[depth]][i];
                visited[i] = false;
                path[depth + 1] = 0;
            }
        }
    }
}



