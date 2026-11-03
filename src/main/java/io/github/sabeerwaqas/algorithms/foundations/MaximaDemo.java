package io.github.sabeerwaqas.algorithms.foundations;

import java.util.List;

public final class MaximaDemo {
    private MaximaDemo() {}

    public static void main(String[] args) {
        List<Point2D> points = List.of(
                new Point2D(2, 5), new Point2D(3, 13), new Point2D(4, 11),
                new Point2D(5, 1), new Point2D(7, 7), new Point2D(9, 10),
                new Point2D(10, 5), new Point2D(12, 12), new Point2D(14, 10),
                new Point2D(15, 7)
        );
        System.out.println("Brute force: " + BruteForceMaxima.findMaxima(points));
        System.out.println("Plane sweep: " + PlaneSweepMaxima.findMaxima(points));
    }
}
