package io.github.sabeerwaqas.algorithms.sorting;

import java.util.Arrays;
import java.util.Objects;

/** Selected comparison and non-comparison sorting examples from CS502. */
public final class SortingAlgorithms {
    private SortingAlgorithms() {}

    public static void mergeSort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        if (values.length < 2) return;
        mergeSort(values, new int[values.length], 0, values.length - 1);
    }

    private static void mergeSort(int[] a, int[] buffer, int low, int high) {
        if (low >= high) return;
        int mid = low + (high - low) / 2;
        mergeSort(a, buffer, low, mid);
        mergeSort(a, buffer, mid + 1, high);
        int i = low, j = mid + 1, out = low;
        while (i <= mid && j <= high) {
            buffer[out++] = a[i] <= a[j] ? a[i++] : a[j++];
        }
        while (i <= mid) buffer[out++] = a[i++];
        while (j <= high) buffer[out++] = a[j++];
        System.arraycopy(buffer, low, a, low, high - low + 1);
    }

    /** Last-element pivot is intentionally simple; ordered inputs can yield O(n^2). */
    public static void quickSort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        quickSort(values, 0, values.length - 1);
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low >= high) return;
        int pivot = partition(a, low, high);
        quickSort(a, low, pivot - 1);
        quickSort(a, pivot + 1, high);
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high], boundary = low;
        for (int current = low; current < high; current++) {
            if (a[current] <= pivot) swap(a, boundary++, current);
        }
        swap(a, boundary, high);
        return boundary;
    }

    public static void heapSort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        int n = values.length;
        for (int parent = n / 2 - 1; parent >= 0; parent--) siftDown(values, n, parent);
        for (int end = n - 1; end > 0; end--) {
            swap(values, 0, end);
            siftDown(values, end, 0);
        }
    }

    private static void siftDown(int[] a, int size, int root) {
        int largest = root;
        while (true) {
            int left = 2 * largest + 1, right = left + 1, next = largest;
            if (left < size && a[left] > a[next]) next = left;
            if (right < size && a[right] > a[next]) next = right;
            if (next == largest) return;
            swap(a, largest, next);
            largest = next;
        }
    }

    /** Counting Sort for integers, including negative values. */
    public static int[] countingSort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        if (values.length == 0) return new int[0];
        int min = Arrays.stream(values).min().orElseThrow();
        int max = Arrays.stream(values).max().orElseThrow();
        long range = (long) max - min + 1;
        if (range > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Key range is too large for Counting Sort");
        }
        int[] counts = new int[(int) range];
        for (int value : values) counts[value - min]++;
        for (int i = 1; i < counts.length; i++) counts[i] += counts[i - 1];
        int[] output = new int[values.length];
        for (int i = values.length - 1; i >= 0; i--) {
            int value = values[i];
            output[--counts[value - min]] = value;
        }
        return output;
    }

    private static void swap(int[] a, int first, int second) {
        int temporary = a[first];
        a[first] = a[second];
        a[second] = temporary;
    }
}
