package io.github.sabeerwaqas.algorithms.foundations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Direct O(n^2) solution to the 2-D Maxima problem. */
public final class BruteForceMaxima {
    private BruteForceMaxima() {}

    /** Duplicate coordinates are collapsed in the returned result. */
    public static List<Point2D> findMaxima(List<Point2D> points) {
        Objects.requireNonNull(points, "points must not be null");
        List<Point2D> unique = points.stream()
                .map(point -> Objects.requireNonNull(point, "point must not be null"))
                .distinct()
                .toList();

        List<Point2D> maxima = new ArrayList<>();
        for (Point2D candidate : unique) {
            boolean dominated = false;
            for (Point2D other : unique) {
                if (dominates(other, candidate)) {
                    dominated = true;
                    break;
                }
            }
            if (!dominated) maxima.add(candidate);
        }
        return List.copyOf(maxima);
    }

    static boolean dominates(Point2D q, Point2D p) {
        return q.x() >= p.x() && q.y() >= p.y()
                && (q.x() > p.x() || q.y() > p.y());
    }
}
