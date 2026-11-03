package io.github.sabeerwaqas.algorithms.dp;

import java.util.Objects;

/** Selected dynamic-programming examples from CS502. */
public final class DynamicProgrammingAlgorithms {
    private DynamicProgrammingAlgorithms() {}

    public static long fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n < 2) return n;
        long previous = 0, current = 1;
        for (int i = 2; i <= n; i++) {
            long next = Math.addExact(previous, current);
            previous = current;
            current = next;
        }
        return current;
    }

    public static int editDistance(String first, String second) {
        Objects.requireNonNull(first, "first must not be null");
        Objects.requireNonNull(second, "second must not be null");
        int m = first.length(), n = second.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int substitution = first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1,
                        Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + substitution));
            }
        }
        return dp[m][n];
    }

    /** dimensions[i - 1] x dimensions[i] is matrix i. */
    public static long matrixChainMinimumCost(int[] dimensions) {
        Objects.requireNonNull(dimensions, "dimensions must not be null");
        if (dimensions.length < 2) throw new IllegalArgumentException("At least one matrix is required");
        for (int dimension : dimensions) {
            if (dimension <= 0) throw new IllegalArgumentException("Dimensions must be positive");
        }
        int count = dimensions.length - 1;
        long[][] cost = new long[count + 1][count + 1];
        for (int length = 2; length <= count; length++) {
            for (int i = 1; i <= count - length + 1; i++) {
                int j = i + length - 1;
                cost[i][j] = Long.MAX_VALUE;
                for (int split = i; split < j; split++) {
                    long multiplication = (long) dimensions[i - 1] * dimensions[split] * dimensions[j];
                    long candidate = cost[i][split] + cost[split + 1][j] + multiplication;
                    cost[i][j] = Math.min(cost[i][j], candidate);
                }
            }
        }
        return cost[1][count];
    }

    /** Returns the optimal value. Descending capacity prevents reusing an item. */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        Objects.requireNonNull(weights, "weights must not be null");
        Objects.requireNonNull(values, "values must not be null");
        if (weights.length != values.length) throw new IllegalArgumentException("Array lengths must match");
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be non-negative");
        int[] best = new int[capacity + 1];
        for (int item = 0; item < weights.length; item++) {
            if (weights[item] <= 0) throw new IllegalArgumentException("Weights must be positive");
            for (int current = capacity; current >= weights[item]; current--) {
                best[current] = Math.max(best[current], values[item] + best[current - weights[item]]);
            }
        }
        return best[capacity];
    }
}
