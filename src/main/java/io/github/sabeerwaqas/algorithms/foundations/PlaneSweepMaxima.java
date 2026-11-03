package io.github.sabeerwaqas.algorithms.foundations;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * O(n log n) plane-sweep solution using a monotonic stack.
 * Sort x ascending, then y ascending; collapse duplicate coordinates.
 */
public final class PlaneSweepMaxima {
    private PlaneSweepMaxima() {}

    public static List<Point2D> findMaxima(List<Point2D> points) {
        Objects.requireNonNull(points, "points must not be null");
        List<Point2D> sorted = points.stream()
                .map(point -> Objects.requireNonNull(point, "point must not be null"))
                .distinct()
                .sorted(Comparator.comparingInt(Point2D::x)
                        .thenComparingInt(Point2D::y))
                .toList();

        Deque<Point2D> candidates = new ArrayDeque<>();
        for (Point2D point : sorted) {
            while (!candidates.isEmpty()
                    && candidates.peekLast().y() <= point.y()) {
                candidates.removeLast();
            }
            candidates.addLast(point);
        }
        return List.copyOf(candidates);
    }
}
