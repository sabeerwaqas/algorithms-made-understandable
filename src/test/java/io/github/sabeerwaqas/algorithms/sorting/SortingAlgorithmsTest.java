package io.github.sabeerwaqas.algorithms.sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmsTest {
    @Test
    void mergeSortHandlesDuplicatesAndNegativeValues() {
        int[] values = {5, -1, 3, 3, 0, -8};
        SortingAlgorithms.mergeSort(values);
        assertArrayEquals(new int[]{-8, -1, 0, 3, 3, 5}, values);
    }

    @Test
    void quickSortHandlesOrderedInputs() {
        int[] sorted = {1, 2, 3, 4, 5};
        int[] reversed = {5, 4, 3, 2, 1};
        SortingAlgorithms.quickSort(sorted);
        SortingAlgorithms.quickSort(reversed);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, reversed);
    }

    @Test
    void heapSortHandlesEmptyAndSingleElementArrays() {
        int[] empty = {};
        int[] single = {7};
        SortingAlgorithms.heapSort(empty);
        SortingAlgorithms.heapSort(single);
        assertArrayEquals(new int[]{}, empty);
        assertArrayEquals(new int[]{7}, single);
    }

    @Test
    void countingSortHandlesNegativeValues() {
        assertArrayEquals(new int[]{-5, -1, 0, 2, 2, 8},
                SortingAlgorithms.countingSort(new int[]{2, -1, 8, 0, -5, 2}));
    }
}
