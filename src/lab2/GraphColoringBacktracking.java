package lab2;
import java.util.*;

public class GraphColoringBacktracking {
    int[][] graph; // 存储图的邻接矩阵
    int numVertices; // 图的顶点数
    int numColors; // 颜色数
    int[] color; // 存储每个顶点的颜色

    // 构造函数，初始化图、颜色数和颜色数组
    public GraphColoringBacktracking(int[][] graph, int numColors) {
        this.graph = graph;
        this.numVertices = graph.length;
        this.numColors = numColors;
        this.color = new int[numVertices];
    }

    // 检查给定的颜色是否可以在指定的顶点上使用
    public boolean isColorValid(int vertex, int color) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && this.color[i] == color) {
                return false;
            }
        }
        return true;
    }

    // 使用回溯法递归地搜索所有可能的颜色方案
    public boolean solve(int vertex) {
        // 如果所有顶点都已经着色，返回 true 表示找到了一种可行的颜色方案
        if (vertex == numVertices) {
            return true;
        }

        // 尝试为当前顶点 vertex 选择颜色
        for (int i = 1; i <= numColors; i++) {
            if (isColorValid(vertex, i)) {
                color[vertex] = i;

                // 递归搜索下一个顶点
                if (solve(vertex + 1)) {
                    return true;
                }

                // 如果无法找到可行方案，则回溯到上一个顶点重新选择颜色
                color[vertex] = 0;
            }
        }

        // 如果所有颜色都尝试过了，但是无法找到可行方案，返回 false
        return false;
    }

    // 打印图的着色方案
    public void printSolution() {
        System.out.print("图的着色方案为：");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(color[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };
        int numColors = 3;

        GraphColoringBacktracking gcb = new GraphColoringBacktracking(graph, numColors);

        if (gcb.solve(0)) {
            gcb.printSolution();
        } else {
            System.out.println("无法找到可行的着色方案！");
        }
    }
}
