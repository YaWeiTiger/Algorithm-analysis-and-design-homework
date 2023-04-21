package lab3;

import java.util.PriorityQueue;

public class KnapsackProblem {

    private static class Node implements Comparable<Node> {
        int level;
        int profit;
        int weight;
        double bound;

        public Node(int level, int profit, int weight, double bound) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.bound = bound;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(other.bound, this.bound);
        }
    }

    private static double bound(Node node, int[] values, int[] weights, int capacity) {
        int remainingCapacity = capacity - node.weight;
        double bound = node.profit;
        int i = node.level + 1;
        while (i < values.length && remainingCapacity > 0) {
            if (remainingCapacity >= weights[i]) {
                bound += values[i];
                remainingCapacity -= weights[i];
            } else {
                bound += (double) remainingCapacity / weights[i] * values[i];
                remainingCapacity = 0;
            }
            i++;
        }
        return bound;
    }

    public static int solve(int[] values, int[] weights, int capacity) {
        int n = values.length;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(-1, 0, 0, bound(new Node(-1, 0, 0, 0), values, weights, capacity)));
        int maxProfit = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.bound > maxProfit) {
                if (node.level == n - 1) {
                    maxProfit = node.profit;
                } else {
                    int level = node.level + 1;
                    int profitWith = node.profit + values[level];
                    int weightWith = node.weight + weights[level];
                    double boundWith = bound(new Node(level, profitWith, weightWith, 0), values, weights, capacity);
                    if (boundWith > maxProfit) {
                        queue.offer(new Node(level, profitWith, weightWith, boundWith));
                    }
                    double boundWithout = bound(new Node(level, node.profit, node.weight, 0), values, weights, capacity);
                    if (boundWithout > maxProfit) {
                        queue.offer(new Node(level, node.profit, node.weight, boundWithout));
                    }
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] values = {10, 40, 30, 50};
        int[] weights = {5, 4, 6, 3};
        int capacity = 10;
        int maxProfit = solve(values, weights, capacity);
        System.out.println("Maximum profit: " + maxProfit);
    }

}
