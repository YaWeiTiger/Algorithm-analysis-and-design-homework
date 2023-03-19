package lab1;
public class bagProblem {
    private static final int[] weights = {7, 3, 4, 5};
    private static final int[] values = {42, 12, 40, 25};
    private static final int capacity = 10;

    public static void main(String[] args) {
        int n = weights.length;
        int maxValue = 0;
        int[] bestSet = new int[n];

        for (int i = 0; i < (1 << n); i++) {
            int[] currentSet = new int[n];
            int currentWeight = 0;
            int currentValue = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentSet[j] = 1;
                    currentWeight += weights[j];
                    currentValue += values[j];
                }
            }

            if (currentWeight <= capacity && currentValue > maxValue) {
                bestSet = currentSet;
                maxValue = currentValue;
            }
        }
        System.out.println("max value: " + maxValue);
    }
}

