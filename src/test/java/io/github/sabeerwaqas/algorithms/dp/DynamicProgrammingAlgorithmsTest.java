package io.github.sabeerwaqas.algorithms.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingAlgorithmsTest {
    @Test
    void fibonacciUsesExpectedBaseCasesAndValues() {
        assertEquals(0, DynamicProgrammingAlgorithms.fibonacci(0));
        assertEquals(1, DynamicProgrammingAlgorithms.fibonacci(1));
        assertEquals(55, DynamicProgrammingAlgorithms.fibonacci(10));
    }

    @Test
    void editDistanceHandlesEqualAndDifferentStrings() {
        assertEquals(0, DynamicProgrammingAlgorithms.editDistance("same", "same"));
        assertEquals(1, DynamicProgrammingAlgorithms.editDistance("cat", "cut"));
        assertEquals(3, DynamicProgrammingAlgorithms.editDistance("", "abc"));
    }

    @Test
    void matrixChainFindsCheaperParenthesization() {
        assertEquals(4500, DynamicProgrammingAlgorithms.matrixChainMinimumCost(new int[]{10, 30, 5, 60}));
    }

    @Test
    void knapsackDoesNotReuseAnItem() {
        assertEquals(9, DynamicProgrammingAlgorithms.knapsack01(
                new int[]{2, 3, 4}, new int[]{4, 5, 6}, 5));
    }
}
